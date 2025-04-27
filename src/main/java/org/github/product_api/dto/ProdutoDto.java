package org.github.product_api.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) que representa um Produto.
 * 
 * @param id O ID do produto (opcional para criação)
 * @param nome O nome do produto
 * @param preco O preço do produto
 * @param descricao A descrição do produto
 */
public record ProdutoDto(
        Integer id,
        
        @NotBlank(message = "O nome do produto é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,
        
        @NotNull(message = "O preço do produto é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        @Digits(integer = 10, fraction = 2, message = "O preço deve ter no máximo 10 dígitos inteiros e 2 decimais")
        BigDecimal preco,
        
        @Size(max = 500, message = "A descrição não pode ultrapassar 500 caracteres")
        String descricao
) {

    /**
     * Construtor secundário sem ID para cadastro
     */
    public ProdutoDto(String nome, BigDecimal preco, String descricao) {
        this(null, nome, preco, descricao);
    }

    /**
     * Método para verificar se o produto é premium (preço maior que 1000)
     * @return true se o produto é premium, false caso contrário
     */
    public boolean isPremium() {
        return preco.compareTo(new BigDecimal("1000")) > 0;
    }
}
