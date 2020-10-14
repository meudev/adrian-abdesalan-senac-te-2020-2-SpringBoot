package br.com.senac.services.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.services.model.Usuario;
import br.com.senac.services.repository.UsuarioRepository;

@Service
public class UsuarioDAO {
	
	@Autowired
	UsuarioRepository repository;

	//CADASTRO DE USUARIO
	public Usuario saveUser(Usuario usuario) {
		Usuario novoUsuario = null;
		
		try {
			return novoUsuario = repository.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return novoUsuario;
		}
		
	}

	//BUSCA USUARIO POR ID
	public Usuario findId(Long id) {
		Usuario usuario = null;

		try {
			return usuario = repository.findId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return usuario;
		}
	}
	
	//BUSCA USUARIO POR NOME
	public List<Usuario> findByName(String nome) {
		List<Usuario> usuario = null;

		try {
			return usuario = repository.findByName(nome);
		} catch (Exception e) {
			e.printStackTrace();
			return usuario;
		}
	}
	
	//BUSCA TODOS USUARIOS
	public List<Usuario> findAll() {

		List<Usuario> teste = null;

		try {
			return teste = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return teste;
		}
	}

	public boolean delete(Long id) {

		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
