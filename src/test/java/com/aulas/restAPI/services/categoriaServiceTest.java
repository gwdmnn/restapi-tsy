package com.aulas.restAPI.services;

import com.aulas.restAPI.entities.Categoria;
import com.aulas.restAPI.entities.enums.Status;
import com.aulas.restAPI.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class categoriaServiceTest {

    @InjectMocks // --> RECURSO A SER TESTADO
    private CategoryService service;

    @Mock // --> RECURSO SIMULADO A SER UTILIZADO NO TESTE
    private CategoryRepository respository;

    @Test
    public void retornaCategoriaAoSalvar(){
        Categoria categoria = new Categoria();
        categoria.setDescricao("Cereal");
        categoria.setStatus(Status.ATIVA);

        Mockito.when(respository.save(categoria)).thenReturn(categoria);
        Assertions.assertNotNull(service.salvar(categoria));

        Mockito.verify(respository,Mockito.times(1)).save(categoria);
    }

    @Test
    public void fazNadaQuandoExcluirCategoriaExistente(){
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setDescricao("Cereal");
        categoria.setStatus(Status.ATIVA);
        Mockito.when(respository.findById(categoria.getId())).thenReturn(Optional.of(categoria));

        Mockito.doNothing().when(respository).delete(categoria);
        Assertions.assertDoesNotThrow(()->service.excluir(categoria.getId()));
        Mockito.verify(respository, Mockito.times(1)).delete(categoria);
    }


    public void RetornaListaQuandoConsultaTodosSemStatus(){
        List<Categoria> lista = new ArrayList<>();
        Mockito.when(respository.findAll()).thenReturn(lista); // --> moca quando o método findAll no repositório encontra algo, então retorna uma lista
        Assertions.assertNotNull(service.consultar("")); // --> verifica se o service.consultar("") retorna um valor não nulo
    }

    public void RetornaListaQuandoConsultaTodosComStatus(){
        List<Categoria> lista = new ArrayList<>();
        Mockito.when(respository.findAll()).thenReturn(lista); // --> moca quando o método findAll no repositório encontra algo, então retorna uma lista
        Assertions.assertNotNull(service.consultar("")); //
    }

}
