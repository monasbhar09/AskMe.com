package com.neu.askme.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.askme.dao.CommentDAO;
import com.neu.askme.dao.PostDAO;
import com.neu.askme.dao.PostLikesDAO;
import com.neu.askme.dao.TagDAO;
import com.neu.askme.dao.UserDAO;
import com.neu.askme.exception.CommentException;
import com.neu.askme.exception.PostException;
import com.neu.askme.exception.TagException;
import com.neu.askme.exception.VoteException;
import com.neu.askme.pojo.Comment;
import com.neu.askme.pojo.Likes;
import com.neu.askme.pojo.Post;
import com.neu.askme.pojo.PostLikes;
import com.neu.askme.pojo.Tag;
import com.neu.askme.pojo.User;
import com.neu.askme.utils.JsonUtils;
import com.neu.askme.validator.PostValidator;

@Controller
public class PostsController {

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
	@Qualifier("postLikesDao")
	PostLikesDAO postLikesDao;
	
	@Autowired
	@Qualifier("postValidator")
	PostValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreatePostForm(ModelMap model) {
		model.addAttribute("post", new Post());
		model.addAttribute("edit", false);
		return "createpost";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPost(HttpServletRequest request, @ModelAttribute("post") Post post, BindingResult result)
			throws TagException, PostException {
		validator.validate(post, result);
		if (result.hasErrors()) {
			return "redirect:/create";
		}
		User user = (User) request.getSession().getAttribute("user");
		Calendar calendar = Calendar.getInstance();
		post.setTimestamp(new Timestamp(calendar.getTime().getTime()));
		post.setFullPost(request.getParameter("fullPost"));
		post.setUser(user);
		post.setTag(new ArrayList<>());
		String tags = request.getParameter("tag");
		String tagsArray[] = tags.split(",");
		for (String tagname : tagsArray) {
			Tag tag = tagDao.findByNameIgnoreCase(tagname);

			if (tag == null) {
				tag = new Tag(tagname);
			}
			post.getTag().add(tag);
		}
		postDao.createPost(post);
		List<Post> postList = postDao.getPostList();
		request.getSession().setAttribute("postList", postList);
		return "redirect:/";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String loadPage(HttpServletRequest request) {
		try {
			List<Post> postList = postDao.getPostList();
			request.getSession().setAttribute("postList", postList);
		} catch (PostException e) {
			e.printStackTrace();
			return "error";
		}
		return "AskMe";
	}

	@RequestMapping(value = { "/comment/add" }, method = RequestMethod.POST)
	public @ResponseBody String commentPage(@RequestParam String comment, @RequestParam Long postId,
			HttpServletRequest request) throws PostException, CommentException {
		
		if("".equals(comment.trim())){
			return "error";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		Post post = postDao.getPostById(postId);
		Comment commentAdd = new Comment();
		commentAdd.setCommentText(comment);
		commentAdd.setDeleted(false);
		commentAdd.setPost(post);
		commentAdd.setUser(user);
		Calendar calendar = Calendar.getInstance();
		commentAdd.setTimestamp(new Timestamp(calendar.getTime().getTime()));
		commentDao.addComment(commentAdd);
		String result = "{" + JsonUtils.toJsonField("user", commentAdd.getUser().toString())
				+ (postId == null ? "" : (", " + JsonUtils.toJsonField("postId", postId.toString())))
				+ (commentAdd.getTimestamp() == null ? ""
						: (", " + JsonUtils.toJsonField("timestamp", commentAdd.getTimestamp().toString())))
				+ (commentAdd.getCommentText() == null ? ""
						: (", " + JsonUtils.toJsonField("commentText", commentAdd.getCommentText())))
				+ "}";
		return result;
	}

	@RequestMapping(value = { "/post/vote/like" }, method = RequestMethod.POST)
	public @ResponseBody int voteLike(@RequestParam Long postId, HttpServletRequest request)
			throws VoteException, PostException {
		User user = (User) request.getSession().getAttribute("user");
		Post post = postDao.getPostById(postId);
		PostLikes likes = postLikesDao.findUserLikes(post, user);
		if (likes != null) {
			postLikesDao.removeLike(likes);
		} else {
			likes = new PostLikes();
			likes.setUser(user);
			likes.setValue(Likes.LIKE_VALUE);
			likes.setPost(post);
			postLikesDao.create(likes);
		}
		int likesSum = post.getLikesSum();
		return likesSum;
	}


	@RequestMapping(value = { "/post/delete/{postID}" }, method = RequestMethod.GET)
	public String deletePost(@PathVariable("postID") Long postId, HttpServletRequest request)
			throws VoteException, PostException {
		Post post = postDao.getPostById(postId);
		postDao.removePost(post);
		return "redirect:/";
	}
}
