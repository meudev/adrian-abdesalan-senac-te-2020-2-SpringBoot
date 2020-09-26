package br.com.senac.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.services.dao.ListaUsuariosDAO;
import br.com.senac.services.i18n.Messages;
import br.com.senac.services.i18n.MessagesProperties;
import br.com.senac.services.model.Usuario;
import br.com.senac.services.rest.ApiResponse;
import br.com.senac.services.rest.ResponseEntityUtil;

@RestController
public class ListaUsuariosController {
	
	@Autowired
	Messages message;
	
	@Autowired
	ListaUsuariosDAO dao;
	
	@RequestMapping(value = "/listUsers", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> listUsers() {
			
		List<Usuario> retornoBusca;
		
		try {
			
			retornoBusca = dao.findAll();
			
			if(retornoBusca != null && !retornoBusca.isEmpty()) {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.API_SUCESS), retornoBusca);
			} else {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), "");
			}

		
		} catch (Exception e){
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), "");
		}
		
	}

}
