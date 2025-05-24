package org.github.product_api.service;

import lombok.RequiredArgsConstructor;
import org.github.product_api.dto.DadosAtualizacaoProduto;
import org.github.product_api.dto.DadosCadastroProduto;
import org.github.product_api.dto.DadosListarProduto;
import org.github.product_api.exceptions.ProdutoNotFoundException;
import org.github.product_api.model.Produto;
import org.github.product_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private static final String PRODUCT_NOT_FOUND_EXCEPTION = "Produto não encontrado";

    public void cadastrar(DadosCadastroProduto dados) {
        var produto = new Produto(dados);
        produtoRepository.save(produto);
    }

    public void atualizar(Integer id, DadosAtualizacaoProduto dados) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION));
        produto.atualizarProduto(dados);
        produtoRepository.save(produto);
    }

    public List<DadosListarProduto> listar() {
        return produtoRepository.findAll().stream()
                .map(DadosListarProduto::new)
                .collect(Collectors.toList());
    }

    public void excluir(Integer id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION));
        produtoRepository.delete(produto);
    }

    public DadosListarProduto buscarPorId(Integer id) {
        return produtoRepository.findById(id).stream()
                .map(DadosListarProduto::new)
                .findFirst()
                .orElseThrow(() -> new ProdutoNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION));
    }

    /**
     * Busca produtos pelo nome (contendo o termo, ignorando maiúsculas/minúsculas).
     *
     * @param nome parte do nome do produto
     * @return lista de produtos encontrados
     * @throws ProdutoNotFoundException se nenhum produto for encontrado
     */
    public List<DadosListarProduto> buscarPorNome(String nome) {
        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);

        if (produtos.isEmpty()) {
            throw new ProdutoNotFoundException(PRODUCT_NOT_FOUND_EXCEPTION);
        }

        return produtos.stream()
                .map(DadosListarProduto::new)
                .collect(Collectors.toList());
    }
}

