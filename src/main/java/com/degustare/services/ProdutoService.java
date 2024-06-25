package com.degustare.services;

import com.degustare.DTO.ResponseDTO;
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

    public Produto findById (Integer id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isEmpty()) {
            throw new RuntimeException("Produto não encontrado!");
        }
        return produtoOpt.get();
    }

    public List<Produto> findByDescricao(String descricao) {
        List<Produto> produtoList = produtoRepository.findByDescricao(descricao);
        if (produtoList.isEmpty()){
            throw new RuntimeException("Produto não encontrado!");
        }
        return produtoList;
    }

    public Produto alterarProduto(Produto produto) {

        Produto produtoAlterado = new Produto();

        try {
            produtoAlterado.setId(produto.getId());
            produtoAlterado.setDescricao(produto.getDescricao());
            produtoAlterado.setPeso(produto.getPeso());
            produtoAlterado.setPeso(produto.getPeso());
            produtoAlterado.setTamanho(produto.getTamanho());
            produtoAlterado.setIngredientes(produto.getIngredientes());
        } catch (NullPointerException e){
            throw new RuntimeException("O campo informado não pode ser nulo");
        } catch (Exception e) {
            throw new RuntimeException("Erro desconhecido. Entre em contato com o Administrador.");
        }
        return produtoRepository.save(produtoAlterado);
    }

    public void deletarProduto(Produto produto) {
         produtoRepository.delete(produto);
    }

    private void validarProduto(Produto produto) {
        if (produto.getId() == null){
            throw new RuntimeException("Campo ID não pode ser nulo!");
        }
        if (produto.getDescricao() == null){
            throw new RuntimeException("Campo Descrição não pode ser nulo!");
        }
        if (produto.getPeso() == null){
            throw new RuntimeException("Campo Peso não pode ser nulo!");
        }
        if (produto.getTamanho() == null){
            throw new RuntimeException("Campo Tamanho não pode ser nulo!");
        }
        if (produto.getIngredientes() == null){
            throw new RuntimeException("Campo Ingredientes não pode ser nulo!");
        }
    }
}
