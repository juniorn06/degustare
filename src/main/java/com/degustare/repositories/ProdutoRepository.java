package com.degustare.repositories;

import com.degustare.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByDescricaoIsContainingIgnoreCase(String descricao);
    Produto findById(int id);


}
