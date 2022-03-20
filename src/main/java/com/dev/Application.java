package com.dev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{

	/*
	 * @Autowired private UsuarioRepository usuarioRepository;
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * Usuario usuario = new Usuario(null, "teste", 123456789L, "teste", new Date(),
		 * TipoUsuario.ADMINISTRADOR, "teste", "teste"); Usuario usuario2 = new
		 * Usuario(null, "teste", 123456789L, "teste", new Date(),
		 * TipoUsuario.ADMINISTRADOR, "teste", "teste");
		 * 
		 * usuarioRepository.saveAll(Arrays.asList(usuario, usuario2));
		 */
	}

}
