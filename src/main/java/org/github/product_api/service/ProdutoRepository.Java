package org.github.product_api.repository;

import org.github.product_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    /**
     * Busca produtos cujo nome contenha o valor informado, ignorando maiúsculas e minúsculas.
     *
     * @param nome termo de busca parcial ou completo
     * @return lista de produtos correspondentes
     */
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}

