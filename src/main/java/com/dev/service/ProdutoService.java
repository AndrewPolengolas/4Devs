package com.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entidades.Produto;
import com.dev.repositorios.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> buscarProdutos(){
		return produtoRepository.findAll();
	}
	
	public Produto buscarProdutoID(Integer id) {

		Optional<Produto> produto = produtoRepository.findById(id);

		return produto.get();
	}
	
	public List<Produto> findByNome(String nome) {

		List<Produto> produtos = produtoRepository.findByNome(nome);

		return produtos;
	}
}
