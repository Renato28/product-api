package org.github.product_api.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.github.product_api.dto.DadosAtualizacaoProduto;
import org.github.product_api.dto.DadosCadastroProduto;
import org.github.product_api.dto.DadosListarProduto;
import org.github.product_api.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
@PostMapping
public ResponseEntity<Void> cadastrar(@RequestBody DadosCadastroProduto dados) {
    produtoService.cadastrar(dados);
    return ResponseEntity.ok().build();
}


    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable @NotNull Integer id,
            @RequestBody DadosAtualizacaoProduto dados) {
        produtoService.atualizar(id, dados);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DadosListarProduto>> listar() {
        List<DadosListarProduto> produtos = produtoService.listar();
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @NotNull Integer id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListarProduto> buscarPorId(@PathVariable @NotNull Integer id) {
        DadosListarProduto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<DadosListarProduto>> buscarPorNome(@RequestParam String nome) {
        List<DadosListarProduto> produtos = produtoService.buscarPorNome(nome);
        return ResponseEntity.ok(produtos);
    }
}

