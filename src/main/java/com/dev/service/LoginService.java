package com.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entidades.Login;
import com.dev.entidades.Usuario;
import com.dev.repositorios.LoginRepository;
import com.dev.service.exceptions.loginException.LoginInvalidException;
import com.dev.service.exceptions.loginException.LoginNotFoundException;
import com.dev.service.exceptions.loginException.SenhaInvalidException;
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
		
		Login retorno = null;
		
		for (Login itens : log) { 
			if(itens.getLogin().equals(login.getLogin())) {
				Boolean verificaSenha = Criptografia.verificarSenha(login.getSenha(), itens.getSenha());
			 
				if(verificaSenha) {
					return retorno = itens;
				}else {
					throw new SenhaInvalidException();
				}
			}else {
				throw new LoginInvalidException();
			}
		}
		return retorno;
	}
	
	public Login buscarLoginId(Integer id) {
		Optional<Login> login = loginRepository.findById(id);
		
		return login.orElseThrow(() -> new LoginNotFoundException());	
	}
	
	public Login update(Login login, String senha, String email) {
		
		login.setLogin(email);
		
		String senhaFechada = Criptografia.gerarHashSenha(senha);
		
		login.setSenha(senhaFechada);
		
		loginRepository.save(login);
		
		return login;
	}
	
	
}
