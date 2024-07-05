package com.degustare.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TOTAL")
    private Double total;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTE")
    private Cliente cliente;

    @OneToMany
    @Column(name = "PRODUTO")
    private List<Produto> produto;
}
