package com.neu.askme.exception;

public class CommentException extends Exception{

	public CommentException(String message)
	{
		super("PostException-"+message);
	}
	
	public CommentException(String message, Throwable cause)
	{
		super("PostException-"+message,cause);
	}
}
