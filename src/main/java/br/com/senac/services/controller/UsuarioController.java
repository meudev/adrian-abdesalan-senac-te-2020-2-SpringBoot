package br.com.senac.services.controller;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.services.dao.UsuarioDAO;
import br.com.senac.services.i18n.Messages;
import br.com.senac.services.i18n.MessagesProperties;
import br.com.senac.services.model.Usuario;
import br.com.senac.services.rest.ApiResponse;
import br.com.senac.services.rest.ResponseEntityUtil;

@RestController
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	Messages message;
	
	@Autowired
	UsuarioDAO dao;

	@PostMapping(value = "/saveUser", produces = "application/json", consumes = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> saveUser(@RequestBody Usuario usuario) {
		Usuario novoUsuario = null;
		
		try {
			Usuario usuarioNovo = usuario;
			
			if(usuario==null) {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), usuario);
			} else {
				if(usuarioNovo.getNome().equalsIgnoreCase("") || usuarioNovo.getSenha().equals("")) {
					return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), usuario);
				} else {
					novoUsuario = dao.saveUser(usuario);
					
					if(novoUsuario != null) {
						return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_SUCESS), usuario);
					} else {
						return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), usuarioNovo);
					}

				}
			}
		
		} catch (Exception e){
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), usuario);
		}

	}
	
}
