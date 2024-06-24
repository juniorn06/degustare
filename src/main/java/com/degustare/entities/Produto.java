package com.degustare.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PESO")
    private Double peso;

    @Column(name = "PRECO")
    private Double preco;

    @Column(name = "TAMANHO")
    private Character tamanho;

    @Column(name = "INGREDIENTES")
    private String ingredientes;


}
