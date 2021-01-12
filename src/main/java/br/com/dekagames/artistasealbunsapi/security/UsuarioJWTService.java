package br.com.dekagames.artistasealbunsapi.security;

import br.com.dekagames.artistasealbunsapi.models.UsuarioJWT;
import br.com.dekagames.artistasealbunsapi.repository.UsuarioJWTRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UsuarioJWTService implements UserDetailsService {
    private UsuarioJWTRepository usuarioJWTRepository;

    public UsuarioJWTService(UsuarioJWTRepository usuarioJWTRepository) {
        this.usuarioJWTRepository = usuarioJWTRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var applicationUser = usuarioJWTRepository.findById(username);
        if (applicationUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        var user = applicationUser.get();
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }
}
