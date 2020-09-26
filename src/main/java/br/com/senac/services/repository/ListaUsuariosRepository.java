package br.com.senac.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.services.model.Usuario;

@Repository
public interface ListaUsuariosRepository extends CrudRepository<Usuario, Long>, JpaRepository<Usuario, Long> {

}
