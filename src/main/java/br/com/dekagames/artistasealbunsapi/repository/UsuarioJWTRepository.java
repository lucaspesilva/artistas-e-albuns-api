package br.com.dekagames.artistasealbunsapi.repository;

import br.com.dekagames.artistasealbunsapi.models.UsuarioJWT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJWTRepository extends JpaRepository<UsuarioJWT, String> {
}
