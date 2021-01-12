package br.com.dekagames.artistasealbunsapi.models;

import javax.persistence.*;

@Entity
@Table(name = "usuarioJWT")
public class UsuarioJWT
{
    @Id
    @Column(name = "usuario", nullable = false, unique = true)
    private String username;

    @Column(name = "senha", nullable = false)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
