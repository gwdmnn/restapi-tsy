package com.aulas.restAPI.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

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
    @ManyToOne
    private Categoria categoria;

    @Column(columnDefinition = "DATETIME")
    private Instant createdAt; // -->Dados para auditoria (PRODUTO QUANDO CRIADO)

    @PrePersist
    public void prePersist(){
        createdAt = Instant.now();
    }

    @Column(columnDefinition = "DATETIME")
    private Instant updatedAt;  // -->Dados para auditoria (PRODUTO QUANDO ATUALIZADO)

    @PreUpdate
    public void preUpdate(){
        updatedAt = Instant.now();
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
