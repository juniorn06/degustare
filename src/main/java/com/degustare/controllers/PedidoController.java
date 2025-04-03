package com.degustare.controllers;

import com.degustare.DTO.PedidoDTO;
import com.degustare.DTO.ResponseDTO;
import com.degustare.entities.Cliente;
import com.degustare.entities.Pedido;
import com.degustare.entities.Produto;
import com.degustare.repositories.PedidoRepository;
import com.degustare.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping(path = "/cadastrarPedido")
    public ResponseEntity<ResponseDTO> cadastrarPedido(@RequestBody PedidoDTO pedidoDTO){
        ResponseEntity<ResponseDTO> pedido = pedidoService.cadastrarPedido(pedidoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(pedido.getBody().getObjeto()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/obterPedidoPorId/{id}")
    public ResponseEntity<Pedido> obterProdutoPorId(@PathVariable Integer id){
        return ResponseEntity.ok().body(pedidoService.obterPedidoPorId(id));
    }

    @GetMapping(path = "/obterPedidos")
    public ResponseEntity<List> obterProdutos(){
        return ResponseEntity.ok().body(pedidoService.obterPedidos());
    }

    @GetMapping(path = "/obterPedidosPorDescricao/{nome}")
    public List<Pedido> obterPedidosPorDescricao(Cliente cliente) {
        List<Pedido> pedidoList = pedidoRepository.findByCliente(cliente);
        if (pedidoList.isEmpty()){
            throw new RuntimeException("Pedido não encontrado!");
        }
        return pedidoList;
    }

    @PutMapping(path = "/alterarPedido/{id}")
    public ResponseEntity<Pedido> alterarPedido(@RequestBody Pedido pedido, @PathVariable Integer id){
        return ResponseEntity.ok().body(pedidoService.alterarPedido(pedido, id));
    }

    @DeleteMapping(path = "/deletarPedido/{id}")
    public void deletarPedido(@PathVariable Integer id){
        pedidoService.deletarPedido(id);
    }
}
