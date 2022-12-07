package app.controller;

import app.exception.WalletException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WalletControllerAdvice {

    @ExceptionHandler({WalletException.class})
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public String walletExceptionHandler(WalletException e){

        System.err.println(e.getMessage());
        return e.getMessage();
    }
}
