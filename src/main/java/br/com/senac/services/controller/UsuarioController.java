package br.com.senac.services.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value = "/findById", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findById(@RequestParam("id") Long id) {
		Usuario novoUsuario = null;
		
		try {
			novoUsuario = dao.findId(id);
			
			if( novoUsuario == null ) {
				return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), novoUsuario);
			}else {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_FINDED),novoUsuario);
			}

		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), novoUsuario);
		}
	}
	
	@GetMapping(value = "/findByName", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findByName(@RequestParam("nome") String nome) {
		List<Usuario> novoUsuario = null;
		
		try {
			novoUsuario = dao.findByName(nome);
			
			if( novoUsuario == null || novoUsuario.isEmpty() ) {
				return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), novoUsuario);
			}else {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_FINDED),novoUsuario);
			}

		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), novoUsuario);
		}
	}
	
	@GetMapping(value = "/findAll", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findAll() {
			
		List<Usuario> retornoBusca;
		
		try {
			
			retornoBusca = dao.findAll();
			
			if(retornoBusca != null && !retornoBusca.isEmpty()) {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_FINDED), retornoBusca);
			} else {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), "");
			}

		
		} catch (Exception e){
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), "");
		}
		
	}
	
	@PatchMapping(value = "/updateUser", produces = "application/json", consumes = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> updateUser(@RequestBody @Validated Usuario usuario) {
		try {
			Usuario updateUsuario = usuario;
			
			if(usuario==null) {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), usuario);
			} else {
				if(updateUsuario.getNome().equalsIgnoreCase("") || updateUsuario.getSenha().equals("")) {
					return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), usuario);
				} else {
					updateUsuario = dao.saveUser(usuario);
					
					if(updateUsuario != null) {
						return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_UPDATED), usuario);
					} else {
						return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), updateUsuario);
					}

				}
			}
		
		} catch (Exception e){
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), usuario);
		}

	}
	
	@DeleteMapping(value = "/delete", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> delete(@RequestParam("id") Long id) {
		boolean resultado = false;
		
		try {
			resultado = dao.delete(id);
			
			if( !resultado ) {
				return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), null);
			}else {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_DELETE), null);
			}

		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), null);
		}
	}
	
}
