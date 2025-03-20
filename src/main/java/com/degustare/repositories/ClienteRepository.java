package com.degustare.repositories;

import com.degustare.entities.Cliente;
import com.degustare.entities.Produto;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeIsContainingIgnoreCase(String nome);

    Integer id(Integer id);
}
