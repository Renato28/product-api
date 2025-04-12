package org.github.product_api.dto;

import java.math.BigDecimal;

public record ProdutoDto(String nome, BigDecimal preco, String descricao) {
}
