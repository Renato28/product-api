package org.github.product_api.exceptions;

public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(String mensagem) {
        super(mensagem);
    }
}
