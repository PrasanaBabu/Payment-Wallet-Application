package app.controller;

import app.exception.WalletException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletControllerAdviceTest {

    @Test
    void walletExceptionHandler() {
        WalletControllerAdvice walletControllerAdvice = new WalletControllerAdvice();
        String actual = walletControllerAdvice.walletExceptionHandler(new WalletException("Test Wallet Exception"));
        String expected = "Test Wallet Exception";

        assertEquals(expected, actual);
    }
}