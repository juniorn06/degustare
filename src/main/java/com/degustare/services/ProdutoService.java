package com.degustare.services;

import com.degustare.DTO.ResponseDTO;
import com.degustare.entities.Cliente;
import com.degustare.entities.Produto;
import com.degustare.exceptions.validarException;
import com.degustare.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {


    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity<ResponseDTO> cadastrarProduto(Produto novoProduto) {
        try {
            validarProduto(novoProduto);
            Produto produtoSalvo = produtoRepository.save(novoProduto);
            String msgDTO = "Produto cadastrado com sucesso!";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(produtoSalvo, msgDTO));
        } catch (validarException e) {
            String msgResponseDTO = e.getMessage();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(null, msgResponseDTO));
        } catch (Exception e) {
            String msgResponseDTO = "Erro inesperado. Entre em contato com o Administrador";
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(null, msgResponseDTO));
        }
    }

    public Produto obterProdutoPorID (Integer id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isEmpty()) {
            throw new RuntimeException("Produto não encontrado!");
        }
        return produtoOpt.get();
    }

    public List<Produto> obterProdutos(){
        List<Produto> listarProdutos = produtoRepository.findAll();
        return listarProdutos;
    }

    public List<Produto> obterProdutoPorDescricao(String descricao) {
        List<Produto> produtoList = produtoRepository.findByDescricao(descricao);
        if (produtoList.isEmpty()){
            throw new RuntimeException("Produto não encontrado!");
        }
        return produtoList;
    }

    public Produto alterarProduto(Produto produtoAlterado, Integer id) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setDescricao(produtoAlterado.getDescricao());
            produto.setPreco(produtoAlterado.getPreco());
            produto.setIngredientes(produtoAlterado.getIngredientes());
            return produtoRepository.save(produtoAlterado);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void deletarProduto(Produto produto) {
         produtoRepository.delete(produto);
    }

    private void validarProduto(Produto produto) {

        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            throw new RuntimeException("Preencha o campo DESCRIÇÃO!");
        }

        if (produto.getPreco() == null){
            throw new RuntimeException("Preencha o campo PREÇO");
        }
    }
}
