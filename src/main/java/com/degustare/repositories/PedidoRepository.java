package com.degustare.repositories;

import com.degustare.entities.Pedido;
import com.degustare.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    //List<Pedido> findByDescricao(String descricao);
}