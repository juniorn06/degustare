package com.degustare.controllers;

import com.degustare.DTO.ResponseDTO;
import com.degustare.entities.Cliente;
import com.degustare.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(path = "/cadastrarCliente")
    public ResponseEntity<ResponseDTO> cadastrarCliente(Cliente cliente){
        clienteService.cadastrarCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
