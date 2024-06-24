package com.degustare.repositories;

import com.degustare.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByDescricao(String descricao);

    Produto findById(int id);

    void delete(Produto produto);

    <Prod extends Produto> Prod save(Prod produto);

}
