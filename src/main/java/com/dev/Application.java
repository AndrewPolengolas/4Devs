package com.dev;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dev.entidades.Usuario;
import com.dev.entidades.enums.TipoUsuario;
import com.dev.repositorios.UsuarioRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Usuario usuario = new Usuario(null, "teste", 123456789L, "teste", new Date(), TipoUsuario.ADMINISTRADOR, "teste", "teste");
		Usuario usuario2 = new Usuario(null, "teste", 123456789L, "teste", new Date(), TipoUsuario.ADMINISTRADOR, "teste", "teste");
		
		usuarioRepository.saveAll(Arrays.asList(usuario, usuario2));
	}

}
