package com.aulas.restAPI.services;

import com.aulas.restAPI.dtos.ProductDTO;
import com.aulas.restAPI.entities.Categoria;
import com.aulas.restAPI.entities.Produto;
import com.aulas.restAPI.entities.enums.Status;
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
    @Autowired
    CategoryService serviceCategoria;


    public List<Produto> consultar(){
        return productRepository.findAll();
    }

    public Produto consultarById(Long id){
        Optional<Produto> obj = productRepository.findById(id);
        Produto prod = obj.orElseThrow(()-> new EntityNotFoundException("Produto não encontrado"));
        return prod;
    }

    @Transactional
    public ProductDTO salvar(ProductDTO produtoDTO){
        Categoria cat = categoryRepository.getReferenceById(produtoDTO.getCategoria().getId());
        if (cat.getStatus() == Status.INATIVA){
            throw new InativeCategoryException("Categoria inativa");
        }
        Produto prod = new Produto();
        prod.setDescricao(produtoDTO.getDescricao());
        prod.setPreco(produtoDTO.getPreco());
        prod.setEstoque(produtoDTO.getEstoque());
        prod.setCategoria(produtoDTO.getCategoria());

        Produto entidadeProduto = productRepository.save(prod);
        ProductDTO retornoDTO = new ProductDTO(entidadeProduto);

        return retornoDTO;
    }

    @Transactional
    public ProductDTO alterar(Long idProduto, ProductDTO produtoDTO){
        Categoria cat = serviceCategoria.consultarById(produtoDTO.getCategoria().getId());

        if(cat.getStatus() == Status.INATIVA){
            throw new InativeCategoryException("Categoria inativa");
        }
        Produto prod = new Produto();
        prod.setDescricao(produtoDTO.getDescricao());
        prod.setPreco(produtoDTO.getPreco());
        prod.setEstoque(produtoDTO.getEstoque());
        prod.setCategoria(produtoDTO.getCategoria());

        Produto entidadeProduto = productRepository.save(prod);
        ProductDTO retornoDTO = new ProductDTO(entidadeProduto);
        return retornoDTO;
    }

    @Transactional
    public void excluir(long idProduto){
        Produto p = this.consultarById(idProduto);
        productRepository.delete(p);

    }




}
