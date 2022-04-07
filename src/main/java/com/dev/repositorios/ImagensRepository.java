package com.dev.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entidades.Imagens;

@Repository
public interface ImagensRepository extends JpaRepository<Imagens, Integer>{

}
