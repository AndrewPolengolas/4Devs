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
	
	public Produto insert(Produto prod) {
		produtoRepository.save(prod);
		return prod;
	}
	
	public Produto update(Integer id, Produto prod) {
		Produto entity = buscarProdutoID(id);
		
		updateData(entity, prod);
		 
		return produtoRepository.save(entity);
	}
	
	private void updateData(Produto entity, Produto prod) {
		entity.setDescricao(prod.getDescricao());
		entity.setNome(prod.getNome());
		entity.setPreco(prod.getPreco());
		entity.setQuantidade(prod.getQuantidade());
		entity.setStatus(prod.getStatus());
	}
}
