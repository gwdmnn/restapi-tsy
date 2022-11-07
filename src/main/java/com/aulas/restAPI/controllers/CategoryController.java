package com.aulas.restAPI.controllers;

import com.aulas.restAPI.entities.Categoria;
import com.aulas.restAPI.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/categorias")
public class CategoryController {

    @Autowired
    CategoryService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> consultarCategorias(){
        List<Categoria> lista = service.consultar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);

    }
    @PostMapping
    public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria){
        this.service.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping("/{idcategoria}")
    public ResponseEntity<Categoria> consultarById(@PathVariable("idcategoria") Long idCategoria){
        return  ResponseEntity.ok().body(service.consultarById(idCategoria));
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<Object> alterar(@PathVariable("idCategoria") Long idCategoria,
                                          @RequestBody Categoria cat) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(idCategoria, cat));
    }

    @DeleteMapping ("/{idCategoria}")
    public ResponseEntity<Void> deletaCategoria(@PathVariable("idCategoria") Long idCategoria){
        service.excluir(idCategoria);
        return ResponseEntity.noContent().build();

    }


}
