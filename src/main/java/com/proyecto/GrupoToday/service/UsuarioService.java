package com.proyecto.GrupoToday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.GrupoToday.models.UsuarioAdmin;
import com.proyecto.GrupoToday.repository.UsuarioRepository;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository RepoUsu;

     @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioAdmin registrarUsuario(UsuarioAdmin usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);
        return RepoUsu.save(usuario);
    }

    public UsuarioAdmin login(String email, String password) {
        UsuarioAdmin usuario = RepoUsu.findByEmail(email);
        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            return usuario;
        }
        return null;
    }
}
