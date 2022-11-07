package com.aulas.restAPI.repositories;


import com.aulas.restAPI.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository <Categoria, Long> {
}
