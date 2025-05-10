package org.github.product_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.github.product_api.dto.DadosAtualizacaoProduto;
import org.github.product_api.dto.DadosCadastroProduto;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private BigDecimal preco;

    private String descricao;

    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
        this.descricao = dados.descricao();
    }

    public void atualizarProduto(DadosAtualizacaoProduto dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.preco() != null) {
            this.preco = dados.preco();
        }

        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
    }
}
