package com.dev.repositorios;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entidades.Imagens;

@Repository
public interface ImagensRepository extends JpaRepository<Imagens, Integer>{
	
	
	@SQLInsert(sql = "INSERT INTO public.imagens(image, produto_id) VALUES (:image, :id);")
	Imagens insertImage(byte[] image, Integer id);
}
