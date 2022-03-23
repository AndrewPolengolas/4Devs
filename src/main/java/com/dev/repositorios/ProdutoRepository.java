package com.dev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entidades.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	List<Produto> findByNome(String nome);
	
}
