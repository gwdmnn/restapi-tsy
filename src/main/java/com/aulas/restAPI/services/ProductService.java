package com.aulas.restAPI.services;

import com.aulas.restAPI.entities.Produto;
import com.aulas.restAPI.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProdutoRepository ProductRepository;

    public List<Produto> consultar(){
        return ProductRepository.findAll();
    }

    public Produto consultarById(Long id){
        Optional<Produto> obj = ProductRepository.findById(id);
        Produto prod = obj.orElseThrow(()-> new EntityNotFoundException("Produto não encontrado"));
        return prod;
    }

    @Transactional
    public Produto salvar(Produto produto){
        return ProductRepository.save(produto);
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
        ProductRepository.delete(p);

    }




}
