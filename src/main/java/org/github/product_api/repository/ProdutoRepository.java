package org.github.product_api.repository;

import org.github.product_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Produto findByNome(String nome);

    Produto findByPreco(BigDecimal preco);
}
