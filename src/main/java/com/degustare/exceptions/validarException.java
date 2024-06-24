package com.degustare.exceptions;

public class validarException extends RuntimeException{
    public validarException(String mensagem){
        super(mensagem);
    }

    public validarException(Throwable t){
        super(t);
    }
}
