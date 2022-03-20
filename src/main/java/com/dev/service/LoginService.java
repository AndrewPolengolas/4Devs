package com.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entidades.Login;
import com.dev.entidades.Usuario;
import com.dev.repositorios.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	public void inserir(Usuario usuario) {
		
		Login login = new Login(usuario.getEmail(), usuario.getSenha());
		loginRepository.save(login);
	}
	
}
