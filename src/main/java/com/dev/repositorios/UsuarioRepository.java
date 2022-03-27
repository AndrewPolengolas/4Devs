package com.dev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	List<Usuario> findByNomeContains(String nome);
	
	List<Usuario> findByCpf(String cpf);
	
	List<Usuario> findByEmail(String email);
	
}
