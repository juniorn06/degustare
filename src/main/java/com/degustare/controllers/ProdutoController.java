package com.degustare.controllers;

import com.degustare.DTO.ResponseDTO;
import com.degustare.entities.Produto;
import com.degustare.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping(path = "/cadastrarProduto")
    public ResponseEntity<ResponseDTO> cadastrarProduto(Produto produto){
        produtoService.cadastrarProduto(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
