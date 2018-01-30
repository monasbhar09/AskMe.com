package com.neu.askme.exception;

public class TagException extends Exception{

	public TagException(String message)
	{
		super("TagException-"+message);
	}
	
	public TagException(String message, Throwable cause)
	{
		super("TagException-"+message,cause);
	}
}
