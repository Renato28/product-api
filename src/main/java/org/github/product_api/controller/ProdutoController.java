package org.github.product_api.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.github.product_api.dto.DadosAtualizacaoProduto;
import org.github.product_api.dto.DadosCadastroProduto;
import org.github.product_api.dto.DadosListarProduto;
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
    public ResponseEntity<Void> cadastrar(@RequestBody DadosCadastroProduto dados) {
        produtoService.cadastrar(dados);
        URI location = URI.create("/produtos/" + dados.id());
        return ResponseEntity.created(location).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public ResponseEntity<Void> atualizar(@NotNull Integer id, @RequestBody DadosAtualizacaoProduto dados) {
        produtoService.atualizar(id, dados);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DadosListarProduto>> listar() {
        List<DadosListarProduto> produtos = produtoService.listar();
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping
    public ResponseEntity<Void> excluir(@NotNull Integer id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListarProduto> buscarPorId(@NotNull @PathVariable Integer id) {
        DadosListarProduto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok().body(produto);
    }
}
