package com.aulas.restAPI.controllers;

import com.aulas.restAPI.entities.Categoria;
import com.aulas.restAPI.entities.enums.Status;
import com.aulas.restAPI.repositories.CategoryRepository;
import com.aulas.restAPI.services.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTests {

    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository repository;

    @Test
    public void retornaCategoriaAoSalvar(){
        Categoria categoria = new Categoria();
        categoria.setDescricao("Cereal");
        categoria.setStatus(Status.ATIVA);

        Mockito.when(repository.save(categoria)).thenReturn(categoria);
        Assertions.assertNotNull(service.salvar(categoria));

        Mockito.verify(repository,Mockito.times(1)).save(categoria);
    }



}
