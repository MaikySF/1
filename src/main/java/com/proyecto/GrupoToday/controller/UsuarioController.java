package com.proyecto.GrupoToday.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.GrupoToday.models.UsuarioAdmin;
import com.proyecto.GrupoToday.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService service;



    @PostMapping("/registrar")
    public ResponseEntity<UsuarioAdmin> registrarUsuario(@RequestBody UsuarioAdmin usuario) {
        // Antes de guardar, asegúrate de que la contraseña esté encriptada
        UsuarioAdmin nuevoUsuario = service.registrarUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        UsuarioAdmin usuario = service.login(email, password);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Correo electrónico o contraseña incorrectos");
        }
    }


    
}
