package com.dev.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entidades.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

	Optional<Login> findById(Integer id);
	
}
