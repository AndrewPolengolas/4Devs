package com.dev.service.exceptions.loginException;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.service.exceptions.MessageExceptionHandler;

@ControllerAdvice(basePackages =  "com.dev.resource")
public class LoginControllerAdvice {

	@ResponseBody
	@ExceptionHandler(LoginInvalidException.class)
	public static ResponseEntity<MessageExceptionHandler> loginNotFound(LoginInvalidException loginInvalid){
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.FORBIDDEN.value(), "Login incorreto");
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
	@ResponseBody
	@ExceptionHandler(LoginNotFoundException.class)
	public static ResponseEntity<MessageExceptionHandler> loginNotFound(LoginNotFoundException loginNotFound){
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.NOT_FOUND.value(), "Login não encontrado");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(SenhaInvalidException.class)
	public static ResponseEntity<MessageExceptionHandler> senhaInvalida(SenhaInvalidException senhaInvalid){
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.FORBIDDEN.value(), "Senha incorreta");
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
}
