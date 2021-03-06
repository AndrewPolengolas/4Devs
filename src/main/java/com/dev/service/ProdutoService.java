package com.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entidades.Imagens;
import com.dev.entidades.Produto;
import com.dev.repositorios.ImagensRepository;
import com.dev.repositorios.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ImagensRepository imagensRepository;
	
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
	
	public Imagens insertImg(byte[] img, Integer id) {
		
		Imagens imagem = new Imagens();
		imagem.setImage(img);
		
		Produto prod = new Produto();
		prod.setId(id);
		imagem.setProduto(prod);
		
		imagensRepository.save(imagem);
		return imagem;
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
