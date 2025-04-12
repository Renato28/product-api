package org.github.product_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.github.product_api.dto.ProdutoDto;
import org.github.product_api.exceptions.ProdutoNotFoundException;
import org.github.product_api.model.Produto;
import org.github.product_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private static final String PRODUTO_NOT_FOUND_EXCEPTION = "Produto não encontrado";

    @Transactional
    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public void atualizar(Integer id, Produto produto) {
        Produto p = produtoRepository.
                findById(id)
                .orElseThrow(() ->
                        new ProdutoNotFoundException(PRODUTO_NOT_FOUND_EXCEPTION));
        p.setNome(produto.getNome());
        p.setPreco(produto.getPreco());
        p.setDescricao(produto.getDescricao());
        produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public void excluir(Integer id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() ->
                new ProdutoNotFoundException(PRODUTO_NOT_FOUND_EXCEPTION));
        produtoRepository.delete(produto);
    }
}
