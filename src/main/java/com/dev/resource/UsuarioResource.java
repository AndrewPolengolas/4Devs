package com.dev.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.entidades.Usuario;
import com.dev.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping(value="/users")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
		List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@GetMapping("/id") 
	public ResponseEntity<Usuario> buscarUsuarioID(@RequestParam("id") Integer id){
		
		Usuario usuario = usuarioService.buscarUsuarioID(id); 
		
		return ResponseEntity.ok().body(usuario);
	}
	
	@GetMapping("/nome")
	public ResponseEntity<List<Usuario>> buscarUsuarioNome(@RequestParam("nome") String nome){
		
		List<Usuario> usuario = usuarioService.findByNome(nome);
		
		return ResponseEntity.ok().body(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario){
		usuario = usuarioService.inserir(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario usuario){
		Usuario user = usuarioService.update(id, usuario);
		return ResponseEntity.ok().body(user);
	}
	
}
