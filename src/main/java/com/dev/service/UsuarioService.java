package com.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entidades.Login;
import com.dev.entidades.Usuario;
import com.dev.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private LoginService loginService;

	public List<Usuario> buscarTodosUsuarios() {

		return usuarioRepository.findAll();
	}

	public Usuario buscarUsuarioID(Integer id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		return usuario.get();
	}
	
	public List<Usuario> findByNome(String nome){
		
		List<Usuario> usuarios = usuarioRepository.findByNomeContains(nome);
		
		return usuarios;
	}

	public Usuario inserir(Usuario usuario) {
		
		List<Usuario> buscaCpf = usuarioRepository.findByCpf(usuario.getCpf());
		
		List<Usuario> buscaEmail = usuarioRepository.findByEmail(usuario.getEmail());
		
		if(!(buscaCpf.isEmpty()) || !(buscaEmail.isEmpty()) || usuario.getId() != null) {
			return null;
		}

		Login login = loginService.inserir(usuario);
		
		usuario.setLogin(login);
		
		usuarioRepository.save(usuario);

		return usuario;
	}

	public void delete(Integer id) {
		usuarioRepository.deleteById(id);
	}

	public Usuario update(Integer id, Usuario usuario) {
		Usuario entity = buscarUsuarioID(id);

		Login login = new Login(entity.getLogin().getId(), entity.getEmail(), usuario.getSenha());
		 
		Login loginBusca = entity.getLogin();
		 
		Login loginEntity = loginService.buscarLogin(loginBusca);
		 
		loginService.update(loginEntity, login);

		entity.setLogin(login);
		
		updateData(entity, usuario);
		 
		return usuarioRepository.save(entity);
	}

	private void updateData(Usuario entity, Usuario usuario) {
		entity.setNome(usuario.getNome());
		entity.setEndereco(usuario.getEndereco());
		entity.setTelefone(usuario.getTelefone());
	}
}
