package com.degustare.services;

import com.degustare.DTO.ResponseDTO;
import com.degustare.entities.Cliente;
import com.degustare.exceptions.validarException;
import com.degustare.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cliente")

public class
ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<ResponseDTO> cadastrarCliente(Cliente novoCliente){
        try {
            validarCliente(novoCliente);
            Cliente clienteSalvo = clienteRepository.save(novoCliente);
            String MsgDTO = "Cliente cadastrado com sucesso!";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(clienteSalvo, MsgDTO));
        } catch (validarException e){
            String msgResponseDTO = e.getMessage();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(null, msgResponseDTO));
        } catch (Exception e){
            String msgResponseDTO = "Erro inesperado. Entre em contato com o Administrador";
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(null, msgResponseDTO));
        }
    }

    public Cliente obterClientePorId(Integer id){ // TODO devolver 400 not found quando nao encontrar
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isEmpty()){
            throw new RuntimeException("Cliente não encontrado!");
        }
        return clienteOpt.get();
    }

    public List<Cliente> obterClientes(){
        List<Cliente> listarCliente = clienteRepository.findAll();
        return listarCliente;
    }

//    public List<Cliente> obterClientePorNome(String nome){
//        List<Cliente> clienteList = clienteRepository.obterClientePorNome(nome);
//        if (clienteList.isEmpty()){
//            throw new RuntimeException("Cliente não encontrado!");
//        }
//        return clienteList;
//    }


    public Cliente alterarCliente (Cliente cliente){
        Cliente clienteAlterado = new Cliente();

        try{
            clienteAlterado.setNome(cliente.getNome());
            clienteAlterado.setTelefone(cliente.getTelefone());
        } catch (Exception e){
            throw new RuntimeException("Erro desconhecido. Entre em contato com o Administrador");
        }

        return clienteAlterado;
    }

    private void validarCliente(Cliente cliente) {

        if (cliente.getNome() == null){
            throw new RuntimeException("Campo Nome não pode ser nulo!");
        }
        if (cliente.getTelefone() == null){
            throw new RuntimeException("Campo Telefone não pode ser nulo!");
        }
    }
}
