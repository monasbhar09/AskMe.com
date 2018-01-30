package com.neu.askme.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.askme.dao.CommentDAO;
import com.neu.askme.dao.PostDAO;
import com.neu.askme.dao.TagDAO;
import com.neu.askme.dao.UserDAO;
import com.neu.askme.exception.UserException;
import com.neu.askme.pojo.User;
import com.neu.askme.validator.UserValidator;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("postDao")
	PostDAO postDao;

	@Autowired
	@Qualifier("tagDao")
	TagDAO tagDao;

	@Autowired
	@Qualifier("commentDao")
	CommentDAO commentDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	protected ModelAndView registerRedirect() throws Exception {
		System.out.print("registerUser");
		return new ModelAndView("register-user", "user", new User());
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	protected ModelAndView registerUser(HttpServletRequest request, @ModelAttribute("user") User user,
			BindingResult result) {
		validator.validate(user, result);
		if (!StringUtils.isEmpty(user.getUsername())) {
			try {
				if (userDao.usernameExists(user.getUsername())) {
					result.rejectValue("username", "error.invalid.username","Username already registered");
				}

				if (!StringUtils.isEmpty(user.getEmail())) {
					if (userDao.emailExists(user.getEmail())) {
						result.rejectValue("email", "error.invalid.email","Email already registered");
					}
				}
			} catch (UserException e) {
				e.printStackTrace();
			}
		}
		if (result.hasErrors()) {
			return new ModelAndView("register-user", "user", user);
		}

		try {
			User u = userDao.register(user);
			request.getSession().setAttribute("user", u);
			request.getSession().setAttribute("errorMessage", null);
			return new ModelAndView("redirect:/", "user", u);
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected String checkUser(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		try {
			User user = userDao.get(request.getParameter("username"), request.getParameter("password"));
			if (user == null) {
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "AskMe";
			}
			session.setAttribute("user", user);
			session.setAttribute("errorMessage", null);
			return "redirect:/";

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	protected String logout(HttpServletRequest request) throws UserException {
		HttpSession session = (HttpSession) request.getSession();
		session.setAttribute("user", null);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	protected String error(HttpServletRequest request) throws UserException {
		return "error";
	}
}
