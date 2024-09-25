package com.cts.gsd.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	@Autowired
	private MessageSource messagesource;
	
	@ExceptionHandler(value=UserException.class)
	public ResponseEntity<Object> userid(UserException exception,Locale locale){
		String errorMessage=messagesource.getMessage("useridnotfound.error.message", null, locale);
		 return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=RoleException.class)
		public ResponseEntity<Object> name(RoleException exception,Locale locale){
			String errorMessage=messagesource.getMessage("roleidnotfound.error.message", null, locale);
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
		}
	@ExceptionHandler(value=AddUserRoleException.class)
	public ResponseEntity<Object> name(AddUserRoleException exception,Locale locale){
		String errorMessage=messagesource.getMessage("adduserrole.error.message", null, locale);
		return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
	}
	
}
