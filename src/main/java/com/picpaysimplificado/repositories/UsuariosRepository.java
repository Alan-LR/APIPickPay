package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

    //se nao funcionar com user, testar com usuario
    Optional<Usuario> findUserByDocumento(String documento);
    Optional<Usuario> findUserById(Long id);

}
