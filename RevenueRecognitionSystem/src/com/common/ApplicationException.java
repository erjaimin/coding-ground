package com.common;

public class ApplicationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ApplicationException(String msg)   {
	       super(msg);
	   }

	   public ApplicationException(String msg, Throwable th){
	       super(msg, th);
	   }

}
