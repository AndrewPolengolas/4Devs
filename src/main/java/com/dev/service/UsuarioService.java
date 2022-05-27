package com.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entidades.Login;
import com.dev.entidades.Usuario;
import com.dev.repositorios.UsuarioCustomReporitory;
import com.dev.repositorios.UsuarioRepository;
import com.dev.service.exceptions.userException.CampoExistenteException;
import com.dev.service.exceptions.userException.InvalidCpfException;
import com.dev.service.exceptions.userException.UsuarioNotFoundException;
import com.dev.utils.Criptografia;
import com.dev.utils.ValidaCPF;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioCustomReporitory usuarioCustomReporitory;

	@Autowired
	private LoginService loginService;

	public List<Usuario> findAll() {

		return usuarioRepository.findAll();
	}
	
	public List<Usuario> buscarUsuario(Integer id) {

		List<Usuario> usuarios = usuarioCustomReporitory.find(id, null);

		return usuarios;
	}

	public Usuario findById(Integer id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		return usuario.orElseThrow(() -> new UsuarioNotFoundException());
	}
	
	public List<Usuario> findByNome(String nome){
		
		List<Usuario> usuarios = usuarioRepository.findByNomeContains(nome);
		
		return usuarios;
	}

	public Usuario inserir(Usuario usuario) {
		
		List<Usuario> buscaCpf = usuarioRepository.findByCpf(usuario.getCpf());
		
		List<Usuario> buscaEmail = usuarioRepository.findByEmail(usuario.getEmail());
		
		if(!(buscaCpf.isEmpty()) || !(buscaEmail.isEmpty()) || usuario.getId() != null) {
			throw new CampoExistenteException();
		}
		
		if(ValidaCPF.isCPF(usuario.getCpf()) != true) {
			throw new InvalidCpfException();
		}else {
			usuario.setCpf(ValidaCPF.imprimeCPF(usuario.getCpf())); 
		}
		 
		
		String senhaFechada = Criptografia.gerarHashSenha(usuario.getSenha());
		
		usuario.setSenha(senhaFechada);

		Login login = loginService.inserir(usuario);
		
		usuario.setLogin(login);
		
		usuarioRepository.save(usuario);

		return usuario;
	}

	public Usuario update(Integer id, Usuario usuario) {
		
		Usuario usuarioUpdate;
		
		usuarioUpdate = findById(id);
	

		if(usuario.getSenha() != null){
			Login login = loginService.buscarLoginId(id);
			 
			Login loginAtualizado = loginService.update(login, usuario.getEmail(), usuario.getSenha());

			usuarioUpdate.setLogin(loginAtualizado);
		}
		
		updateData(usuarioUpdate, usuario);
		 
		return usuarioRepository.save(usuarioUpdate);
	}

	private void updateData(Usuario usuarioUpdate, Usuario usuario) {
		if(usuario.getStatus() != null) {
			usuarioUpdate.setStatus(usuario.getStatus());
		}
		if(usuario.getEndereco() != null) {
			usuarioUpdate.setEndereco(usuario.getEndereco());
		}
		usuarioUpdate.setSenha(usuarioUpdate.getLogin().getSenha());
		if(usuario.getTelefone() != null) {
			usuarioUpdate.setTelefone(usuario.getTelefone());
		}
		if(usuario.getTipoUsuario() != null) {
			usuarioUpdate.setTipoUsuario(usuario.getTipoUsuario());
		}
		if(usuario.getDataNascimento() != null) {
			usuarioUpdate.setDataNascimento(usuario.getDataNascimento());
		}
	}
}
