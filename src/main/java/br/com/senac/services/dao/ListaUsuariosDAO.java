package br.com.senac.services.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.services.model.Usuario;
import br.com.senac.services.repository.ListaUsuariosRepository;

@Service
public class ListaUsuariosDAO {
	
	@Autowired
	ListaUsuariosRepository repository;
	
	public List<Usuario> findAll() {

		List<Usuario> teste = null;

		try {
			return teste = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return teste;
		}
	}

}
