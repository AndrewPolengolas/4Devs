package com.dev.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Id
		private String login;
		private String senha;
		
		public Login() {}
		
		
		public Login(String login, String senha) {
			super();
			this.login = login;
			this.senha = senha;
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
