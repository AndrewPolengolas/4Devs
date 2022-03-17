package com.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entidades.Usuario;
import com.dev.repositorios.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> buscarTodosUsuarios() {
		
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarUsuarioID(Integer id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		return usuario.get();	
	}
	
	public Usuario inserir(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void delete(Integer id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario update(Integer id, Usuario usuario) {
		Usuario entity = usuarioRepository.getById(id);
		updateData(entity, usuario);
		return usuarioRepository.save(entity);
	}

	private void updateData(Usuario entity, Usuario usuario) {
		entity.setNome(usuario.getNome());
		entity.setEmail(usuario.getEmail());
		entity.setEndereco(usuario.getEndereco());
		entity.setTelefone(usuario.getTelefone());
	}
}
