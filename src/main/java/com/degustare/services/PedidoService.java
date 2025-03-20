package com.degustare.services;

import com.degustare.DTO.PedidoDTO;
import com.degustare.DTO.ResponseDTO;
import com.degustare.entities.Cliente;
import com.degustare.entities.Pedido;
import com.degustare.entities.Produto;
import com.degustare.exceptions.validarException;
import com.degustare.repositories.ClienteRepository;
import com.degustare.repositories.PedidoRepository;
import com.degustare.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {


    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ResponseEntity<ResponseDTO> cadastrarPedido(PedidoDTO novoPedido) {
        try {
            Cliente cliente = clienteRepository.findById(novoPedido.getClienteId()).get();
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            List<Produto> produtos = new ArrayList<>();
            Double valorTotal = 0D;
            for (Integer produtoId : novoPedido.getProdutoIdList()) {
                Produto produto = produtoRepository.findById(produtoId).get();
                produtos.add(produto);
                valorTotal += produto.getPreco();
            }
            pedido.setProdutos(produtos);
            pedido.setTotal(valorTotal);
            pedidoRepository.save(pedido);
            String msgDTO = "Pedido cadastrado com sucesso!";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(pedido, msgDTO));
        } catch (validarException e) {
            String msgResponseDTO = e.getMessage();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(null, msgResponseDTO));
        } catch (Exception e) {
            String msgResponseDTO = "Erro inesperado. Entre em contato com o Administrador";
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(null, msgResponseDTO));
        }
    }

    public List<Pedido> obterPedidos(){
        List<Pedido> listarPedidos = pedidoRepository.findAll();
        return listarPedidos;
    }

    public Pedido obterPedidoPorId(Integer id){ // TODO devolver 400 not found quando nao encontrar
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isEmpty()){
            throw new RuntimeException("Pedido não encontrado!");
        }
        return pedidoOpt.get();
    }

    public List<Pedido> obterPedidosPorDescricao(String descricao){
        List<Pedido> pedidosList = pedidoRepository.findByDescricaoContainingIgnoreCase(descricao);
        if (pedidosList.isEmpty()){
            throw new RuntimeException("Pedido não encontrado!");
        }
        return pedidosList;
    }

    public Pedido alterarPedido(Pedido pedidoAlterado, Integer id) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setTotal(pedido.getTotal());
            pedido.setCliente(pedido.getCliente());
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void deletarPedido(Integer id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado no id: " + id));
        pedidoRepository.delete(pedido);
    }
}
