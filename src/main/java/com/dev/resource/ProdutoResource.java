package com.dev.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entidades.Produto;
import com.dev.service.ProdutoService;

@CrossOrigin(origins = "https://akira42.github.io/ProjetoIntegrador4Semestre/", maxAge = 3600)
@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> buscarProdutos() {
		List<Produto> produtos = produtoService.buscarProdutos();
		return ResponseEntity.ok().body(produtos);
	}
	
	@PostMapping
	public ResponseEntity<Produto> insert(@RequestBody Produto prod){
		
		produtoService.insert(prod);
		
		return ResponseEntity.ok().body(prod);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody Produto prod){
		Produto product = produtoService.update(id, prod);
		return ResponseEntity.ok().body(product);
	}
	
}
