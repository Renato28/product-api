package org.github.product_api.dto;

import org.github.product_api.model.Produto;

import java.math.BigDecimal;

public record DadosListarProduto(Integer id, String nome, BigDecimal preco) {
    public DadosListarProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco());
    }
}
