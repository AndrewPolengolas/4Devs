package com.dev.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dev.entidades.Usuario;
import com.dev.service.UsuarioService;

@RestController
@RequestMapping(value="/teste")
public class TesteUser {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = "/findAll")
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
		List<Usuario> usuarios = usuarioService.findAll();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@GetMapping(value = "/find") 
	public ModelAndView buscarUsuarioID(){
		
		ModelAndView mv = new ModelAndView("testeDashboard");

		Usuario usuario = usuarioService.findById(3); 
		
		mv.addObject("nome", usuario.getNome());
		
		return mv;
	}
	
	@GetMapping(value = "/findName/{nome}")
	public ResponseEntity<List<Usuario>> buscarUsuarioNome(@PathVariable("nome") String nome){
		
		List<Usuario> usuario = usuarioService.findByNome(nome);
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	@PostMapping(value = "/postUser")
	public ResponseEntity<Usuario> insert(@Valid @RequestBody Usuario usuario){
		Usuario novoUsuario = usuarioService.inserir(usuario);
		
		return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/putUser/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario usuario){
		Usuario usuarioUpdate = usuarioService.update(id, usuario);
		return new ResponseEntity<>(usuarioUpdate, HttpStatus.OK);
	}
	
}
