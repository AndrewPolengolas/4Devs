package com.dev.service.exceptions.userException;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.service.exceptions.MessageExceptionHandler;

@ControllerAdvice(basePackages =  "com.dev.resource")
public class UsuarioControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<MessageExceptionHandler> objectNotFound(UsuarioNotFoundException objNotFound){
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.NOT_FOUND.value(), "Usuário não encontrado");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(InvalidCpfException.class)
	public ResponseEntity<MessageExceptionHandler> cpfInvalid(InvalidCpfException cpfInvalid){
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.BAD_REQUEST.value(), "Cpf inválido");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(CampoExistenteException.class)
	public ResponseEntity<MessageExceptionHandler> campoExistente(CampoExistenteException campoExistente){
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.FORBIDDEN.value(), "Há campos que correspondem a um usuario ja cadastrado");
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MessageExceptionHandler> argumentsNotValid(
			MethodArgumentNotValidException notValid){
		
		BindingResult result = notValid.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		
		StringBuilder sb = new StringBuilder("Os seguintes campos não podem ser nulos: ");
		for(FieldError fieldError : fieldErrors) {
			sb.append(" | ");
			sb.append(" > ");
			sb.append(fieldError.getField());
			sb.append(" < ");
		}
		
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.BAD_REQUEST.value(), sb.toString());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
