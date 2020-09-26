package br.com.senac.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.services.model.Usuario;
import br.com.senac.services.repository.UsuarioRepository;

@Service
public class UsuarioDAO {
	
	@Autowired
	UsuarioRepository repository;

	public Usuario saveUser(Usuario usuario) {
		Usuario novoUsuario = null;
		
		try {
			return novoUsuario = repository.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return novoUsuario;
		}
		
	}
	
}
