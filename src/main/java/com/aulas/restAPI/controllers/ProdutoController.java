package com.aulas.restAPI.controllers;

import com.aulas.restAPI.entities.Produto;
import com.aulas.restAPI.repositories.ProdutoRepository;
import com.aulas.restAPI.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos") // --> http://localhost:8080/produtos
public class ProdutoController {

    @Autowired
    ProductService service;

    @GetMapping
    public ResponseEntity<List<Produto>> consultarProdutos(){
        List<Produto> lista = service.consultar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
    @PostMapping
    public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto){
        Produto prod = service.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }
    @GetMapping("/{idproduto}")
    public ResponseEntity<Produto> consultarById(@PathVariable("idproduto") Long idproduto){
        return  ResponseEntity.ok().body(service.consultarById(idproduto));
    }

    @PutMapping("/{idproduto}")
    public ResponseEntity<Object> alterar(@PathVariable("idproduto") Long idproduto,
                                          @Valid @RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(idproduto,produto));
    }

    @DeleteMapping ("/{idProduto}")
    public ResponseEntity<Void> deletaProduto(@PathVariable("idProduto") Long idProduto){
        service.excluir(idProduto);
        return ResponseEntity.noContent().build();

    }

    /*
    @Autowired
    ProdutoRepository repo;

    @GetMapping
    public ResponseEntity<List<Produto>> consultarProdutos() {
        List<Produto> lista = repo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/categorias") // --> http://localhost:8080/produtos/categorias
    public String consultar(){
        return "Página de consulta de produtos";
    }

    @GetMapping("/categorias/{nomeCategoria}")
    public String consultarByCategoria(@PathVariable("nomeCategoria") String categoria){
        return "Você consultou pela categoria: " + categoria;
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        Produto prod = repo.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }

    @PutMapping("/{idproduto}")
    public ResponseEntity<Object> alterar(@PathVariable("idproduto") Long idproduto,
                                          @RequestBody Produto produto){
        //return ResponseEntity.ok().body(repo.save(produto));
        try{
            Produto prod = repo.findById(idproduto).get();
            if(prod != null){
               // prod.setDescricao(produto.getDescricao());
               // prod.setPreco(produto.getPreco());
               // prod.setEstoque(produto.getEstoque());
               // repo.save(prod);
                produto.setId(idproduto);
                repo.save(produto);
            }
            return ResponseEntity.ok().body(produto);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não existe.");
        }
    }

    @GetMapping ("/{idProduto}")
    public ResponseEntity<Produto> buscaProduto(@PathVariable("idProduto") Long idProduto){
        Produto p = repo.findById(idProduto).get();
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @DeleteMapping ("/{idProduto}")
    public ResponseEntity<String> deletaProduto(@PathVariable("idProduto") Long idProduto){
        try{
            Produto p = repo.findById(idProduto).get();
            if (p != null){
                repo.delete(p);
            }
            return ResponseEntity.ok().body("Produto excluído com sucesso");

        }
        catch (Exception e){
            return ResponseEntity.ok().body("Produto não encontrado");
        }
    }

*/

}
