package com.degustare.controllers;

import com.degustare.DTO.ResponseDTO;
import com.degustare.entities.Cliente;
import com.degustare.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(path = "/cadastrarCliente")
    public ResponseEntity<ResponseDTO> cadastrarCliente(@RequestBody Cliente cliente){
        clienteService.cadastrarCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/obterClientePorId/{id}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable Integer id){
        return ResponseEntity.ok().body(clienteService.obterClientePorId(id));
    }

    @GetMapping(path = "/obterClientes")
    public ResponseEntity<List> obterClientes(){
        return ResponseEntity.ok().body(clienteService.obterClientes());
    }

}
