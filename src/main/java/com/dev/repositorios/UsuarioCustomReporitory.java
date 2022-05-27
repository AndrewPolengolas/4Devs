package com.dev.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.dev.entidades.Usuario;

@Repository
public class UsuarioCustomReporitory {

	private final EntityManager em;
	
	public UsuarioCustomReporitory(EntityManager em) {
		this.em = em;
	}

	public List<Usuario> find(Integer id, String nome){
		
		String query = "select U from Usuario as U ";
		
		String condicao = "where";
		
		if(id != null) {
			query += condicao + " U.id = :id";
			condicao = " and ";
		}
		
		if(nome != null) {
			query += condicao + " U.nome = :nome";
		}
		
		var q = em.createQuery(query, Usuario.class);
		
		if(id != null) {
			q.setParameter("id", id);
		}
		
		if(nome != null) {
			q.setParameter("nome", nome);
		}
		
		return q.getResultList();
	}
	
}
