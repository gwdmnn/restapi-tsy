package com.aulas.restAPI.repositories;

import com.aulas.restAPI.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // --> permite a injeção da interface em outros locais através do framework
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
