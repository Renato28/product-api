package org.github.product_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.github.product_api.dto.ProdutoDto;
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

    private static final String PRODUTO_NOT_FOUND_EXCEPTION = "Produto não encontrado";

    @Transactional
    public Produto cadastrar(ProdutoDto produtoDto) {
        Produto produto = toEntity(ProdutoDto);
        Produto produtoSalvo = produtoRepository.save.(produto);
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

        Produto produtoAtualizado = produtoRepository.save(produtoExiste);
        return toDto(produtoAtualizado);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
        .tream()
        .map(this::toDto)
        .collect(collectors.tolist());
    }

    public void excluir(Integer id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() ->
                new ProdutoNotFoundException(PRODUTO_NOT_FOUND_EXCEPTION));
        produtoRepository.delete(produto);
  {
  public ProdutoDto buscarPorId(Integer id) {
     Produto produto = produtoRepository.findById(id)
             .orElseThrow(() -> new ProdutoNotFoundException(PRODUTO_NOT_FOUND_EXCEPTION));
      return toDto(produto);
  }
  // Métodos auxiliares para converssão
  private Produto toEntity(ProdutoDto dto) {
      Produto produto = new Produto();
      Produto.setNome(dto.nome());
      produto.setPreco(dto.preco());
      produto.setDescricao(dto.descricao());
      neturn produto;
 }

 private ProdutoDto toDto(Produto entity) {
     return new ProdutoDto(
         entity.getNome(),
         entity.getPreco(),
         entity.getDescricao()
    );
 }
}
