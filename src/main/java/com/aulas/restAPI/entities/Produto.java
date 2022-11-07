package com.aulas.restAPI.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Tb_produto")
public class Produto {
    @Id // --> chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // --> auto incremento para o id
    private long id;
    @NotBlank(message = "Informe a descrição")
    @Size(message = "O campo deve ter entre 3 e 10 caracteres", max = 10, min = 3)
    private String descricao;
    private Float preco;
    private Float estoque;

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
