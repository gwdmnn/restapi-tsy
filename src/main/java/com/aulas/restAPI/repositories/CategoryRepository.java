package com.aulas.restAPI.repositories;


import com.aulas.restAPI.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository <Categoria, Long> {

    @Query("SELECT c FROM Categoria c WHERE c.status = 0")
    List<Categoria> findAllAtivo();

    @Query("SELECT c FROM Categoria c WHERE c.status = 1")
    List<Categoria> findAllInativo();
}
