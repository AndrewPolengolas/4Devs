package com.dev.resource;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.entidades.Imagens;
import com.dev.entidades.Produto;
import com.dev.service.ProdutoService;

@CrossOrigin
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
	
	@PostMapping(value = "/postProd")
	public ResponseEntity<Produto> insert(@RequestBody Produto prod){
		
		produtoService.insert(prod);
		
		return ResponseEntity.ok().body(prod);
	}
	
	@PostMapping(value = "/postImgs/idProd/{id}")
	public ResponseEntity<Imagens> insertImgs(@RequestParam(value="img") MultipartFile img, 
			@PathVariable Integer id) throws IOException{
		
		Imagens imagens = produtoService.insertImg(img.getBytes(), id);
		
		return ResponseEntity.ok().body(imagens);
	}
	
	@PutMapping(value = "/putProd/{id}")
	public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody Produto prod){
		Produto product = produtoService.update(id, prod);
		return ResponseEntity.ok().body(product);
	}
	
}
