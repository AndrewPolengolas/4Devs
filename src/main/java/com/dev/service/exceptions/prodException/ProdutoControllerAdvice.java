package com.dev.service.exceptions.prodException;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.service.exceptions.MessageExceptionHandler;

@ControllerAdvice(basePackages =  "com.dev.resource")
public class ProdutoControllerAdvice {

	@ResponseBody
	@ExceptionHandler(ErrorImageException.class)
	public ResponseEntity<MessageExceptionHandler> objectNotFound(ErrorImageException errorImage){
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.BAD_REQUEST.value(), "Erro ao salvar imagem");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	
	}
}
