package com.aulas.restAPI.controllers;

import com.aulas.restAPI.entities.Usuario;
import com.aulas.restAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*") // --> Aceitar solicitações de quaisquer outros servidores (Front end está rodando em um diferente)
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> consultar(){
        return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(usuario));
    }

}
