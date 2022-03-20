package com.dev.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Login implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "id")
		private Integer id;
		private String login;
		private String senha;
		
		@OneToOne(mappedBy = "login")
		private Usuario usuario;
		
		public Login() {}
		
		public Login(Integer id, String login, String senha) {
			super();
			this.id = id;
			this.login = login;
			this.senha = senha;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}


		@Override
		public int hashCode() {
			return Objects.hash(login);
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Login other = (Login) obj;
			return Objects.equals(login, other.login);
		}
}
