package com.aulas.restAPI.dtos;

import com.aulas.restAPI.entities.Categoria;
import com.aulas.restAPI.entities.Produto;

public class ProductDTO {

    private long id;
    private String descricao;
    private Float preco;
    private Float estoque;
    private Categoria categoria;

    public ProductDTO() {
    }

    public ProductDTO(Produto produto){
        this.id = produto.getId();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.estoque = produto.getEstoque();
        this.categoria = produto.getCategoria();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Float getEstoque() {
        return estoque;
    }

    public void setEstoque(Float estoque) {
        this.estoque = estoque;
    }
}
