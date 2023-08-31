package com.picpaysimplificado;

import com.picpaysimplificado.domain.usuarios.Usuario;
import com.picpaysimplificado.repositories.UsuariosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class PicpaysimplificadoApplicationTests {

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Test
	void contextLoads() {

		Optional<Usuario> user = usuariosRepository.findUserById(3L);

		System.out.println(user);

	}

}
