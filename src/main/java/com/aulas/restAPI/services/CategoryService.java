package com.aulas.restAPI.services;

import com.aulas.restAPI.repositories.ProdutoRepository;
import com.aulas.restAPI.entities.Categoria;
import com.aulas.restAPI.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    public List<Categoria> consultar(String status){
        if (status.equalsIgnoreCase("ATIVA")){
            return categoryRepository.findAllAtivo();
        }
        if (status.equalsIgnoreCase("INATIVA")){
            return categoryRepository.findAllInativo();

        }
        else{
            return categoryRepository.findAll();
        }
    }

    public Categoria salvar(Categoria c){
        return categoryRepository.save(c);
    }

    public Categoria consultarById(Long id){
        Optional<Categoria> obj = categoryRepository.findById(id);
        Categoria cat = obj.orElseThrow(()-> new EntityNotFoundException("Categoria não encontrada"));
        return cat;
    }

    public Categoria alterar(Long idCategoria, Categoria cat) {
        Categoria c = this.consultarById(idCategoria);

        c.setDescricao(cat.getDescricao());
        c.setStatus(cat.getStatus());

        return this.salvar(c);
    }

    @Transactional
    public void excluir(long idCategoria){
        Categoria cat = this.consultarById(idCategoria);
/*
        List<Produto> lista = produtoRepository.findByCategoria(cat);
        if(lista.size() > 0){
            cat.setStatus(Status.INATIVA);
            categoryRepository.save(cat);
        }else{
            categoryRepository.delete(cat);
        }

 */
        categoryRepository.delete(cat);
    }
}

