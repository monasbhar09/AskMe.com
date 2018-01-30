package com.neu.askme.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.askme.pojo.Post;

public class PostValidator implements Validator {
	public boolean supports(Class aClass) {
		return aClass.equals(Post.class);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.title", "title required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullPost", "error.invalid.fullPost", "description required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tag", "error.invalid.tag", "tags required");
	}
}
