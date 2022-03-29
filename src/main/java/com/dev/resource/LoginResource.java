package com.dev.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entidades.Login;
import com.dev.service.LoginService;

@CrossOrigin
@RestController
@RequestMapping(value="/login")
public class LoginResource {

	@Autowired
	LoginService loginService;
	
	
	@PostMapping
	public ResponseEntity<Login> buscarLogin(@RequestBody Login obj){
		
		Login login = loginService.buscarLogin(obj);
		
		if(login == null) {
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok().body(login);
	}
}
