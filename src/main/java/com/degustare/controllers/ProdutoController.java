package com.degustare.controllers;

import com.degustare.DTO.ResponseDTO;
import com.degustare.entities.Cliente;
import com.degustare.entities.Produto;
import com.degustare.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping(path = "/cadastrarProduto")
    public ResponseEntity<ResponseDTO> cadastrarProduto(@RequestBody Produto produto){
        produtoService.cadastrarProduto(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/obterProdutoPorId/{id}")
    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Integer id){
        return ResponseEntity.ok().body(produtoService.obterProdutoPorID(id));
    }

    @GetMapping(path = "/obterProdutos")
    public ResponseEntity<List> obterProdutos(){
        return ResponseEntity.ok().body(produtoService.obterProdutos());
    }

    @GetMapping("/obterProdutoPorDescricao/{nome}")
    public ResponseEntity<List> obterProdutoPorDescricao(@PathVariable String nome){
        return ResponseEntity.ok().body(produtoService.obterProdutoPorDescricao(nome));
    }

    @PutMapping(path = "/alterarProduto/{id}")
    public ResponseEntity<Produto> alterarProduto(@PathVariable Integer id, @RequestBody Produto produto){
        return ResponseEntity.ok().body(produtoService.alterarProduto(produto, id));
    }
}
