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
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "UNIDADE")
    private Integer unidade;

    @Column(name = "PRECO")
    private Double preco;

    @Column(name = "TAMANHO")
    private Character tamanho;

    @Column(name = "INGREDIENTES")
    private String ingredientes;




}
