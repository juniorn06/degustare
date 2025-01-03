package com.degustare.controllers;

import com.degustare.DTO.PedidoDTO;
import com.degustare.DTO.ResponseDTO;
import com.degustare.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping(path = "/cadastrarPedido")
    public ResponseEntity<ResponseDTO> cadastrarPedido(@RequestBody PedidoDTO pedidoDTO){
        ResponseEntity<ResponseDTO> pedido = pedidoService.cadastrarPedido(pedidoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(pedido.getBody().getObjeto()).toUri();
        return ResponseEntity.created(uri).build();
    }

//    @GetMapping(path = "/obterProdutoPorId/{id}")
//    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Integer id){
//        return ResponseEntity.ok().body(produtoService.obterProdutoPorID(id));
//    }
//
//    @GetMapping(path = "/obterProdutos")
//    public ResponseEntity<List> obterProdutos(){
//        return ResponseEntity.ok().body(produtoService.obterProdutos());
//    }

}
