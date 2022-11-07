package com.aulas.restAPI.services;

import com.aulas.restAPI.entities.Categoria;
import com.aulas.restAPI.entities.Produto;
import com.aulas.restAPI.enums.Status;
import com.aulas.restAPI.repositories.CategoryRepository;
import com.aulas.restAPI.repositories.ProdutoRepository;
import com.aulas.restAPI.services.exceptions.InativeCategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProdutoRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    public List<Produto> consultar(){
        return productRepository.findAll();
    }

    public Produto consultarById(Long id){
        Optional<Produto> obj = productRepository.findById(id);
        Produto prod = obj.orElseThrow(()-> new EntityNotFoundException("Produto não encontrado"));
        return prod;
    }

    @Transactional
    public Produto salvar(Produto produto){
        Categoria cat = categoryRepository.getReferenceById(produto.getCategoria().getId());
        if (cat.getStatus() == Status.INATIVA){
            throw new InativeCategoryException("Categoria inativa");
        }

        return productRepository.save(produto);
    }

    public Produto alterar(Long idProduto, Produto produto){
        Produto p = this.consultarById(idProduto);

        p.setDescricao(produto.getDescricao());
        p.setPreco(produto.getPreco());
        p.setEstoque(produto.getEstoque());
        return this.salvar(p);
    }

    @Transactional
    public void excluir(long idProduto){
        Produto p = this.consultarById(idProduto);
        productRepository.delete(p);

    }




}
