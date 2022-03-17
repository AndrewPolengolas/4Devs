package com.dev.service;

import java.util.List;

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
		try {
			Usuario usuario = usuarioRepository.getById(id);
			return usuario;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public Usuario inserir(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
