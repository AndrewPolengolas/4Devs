package com.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entidades.Login;
import com.dev.entidades.Usuario;
import com.dev.repositorios.LoginRepository;
import com.dev.utils.Criptografia;

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
				 Boolean verificaSenha = Criptografia.verificarSenha(login.getSenha(), itens.getSenha());
				 
				 Login retorno = verificaSenha ? itens : null;
				 
				 return retorno;
			 } 
		 }
		
		return null;
	}
	
	public Login buscarLoginId(Integer id) {
		Optional<Login> login = loginRepository.findById(id);
		
		return login.get();	
	}
	
	public Login update(Login login, String senha, String email) {
		
		login.setLogin(email);
		
		String senhaFechada = Criptografia.gerarHashSenha(senha);
		
		login.setSenha(senhaFechada);
		
		loginRepository.save(login);
		
		return login;
	}
	
	
}
