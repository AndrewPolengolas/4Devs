package com.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entidades.Login;
import com.dev.entidades.Usuario;
import com.dev.repositorios.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	public Login inserir(Usuario usuario) {
		Login login = new Login(null, usuario.getEmail(), usuario.getSenha());
		return loginRepository.save(login);
	}
	
	public Login buscarLogin(Login login) {
		
		List<Login> log = loginRepository.findAll();
		
		/*
		 * if(log.get().getLogin().equals(login.getLogin())) {
		 * if(log.get().getSenha().equals(login.getSenha())) { return log.get(); } }
		 */
		
		 for (Login itens : log) { 
			 if(itens.getLogin().equals(login.getLogin())) {
				 if(itens.getSenha().equals(login.getSenha())) {
					 return itens; 
					 } 
				 } 
			 }
		
		return null;
	}
	
	public void update(Login loginEntity, Login login) {
		loginEntity.setLogin(login.getLogin());
		loginEntity.setSenha(login.getSenha());
		
		loginRepository.save(loginEntity);
	}
	
	
}
