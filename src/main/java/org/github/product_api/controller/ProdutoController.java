package org.github.product_api.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.github.product_api.model.Produto;
import org.github.product_api.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.cadastrar(produto);
        URI location = URI.create("/produtos/" + novoProduto.getId());
        return ResponseEntity.created(location).body(novoProduto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public ResponseEntity<Void> atualizar(@NotNull Integer id, @RequestBody Produto produto) {
        produtoService.atualizar(id, produto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> produtos = produtoService.listar();
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> excluir(@NotNull Integer id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
