package app.controller;

import app.exception.WalletException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorOutputProviderTest {

    @Test
    void printIdPasswordIncorrect() {
        assertEquals("ID/Password Incorrect",
                ErrorOutputProvider.printIdPasswordIncorrect());
    }

    @Test
    void printErrorChoiceStatement() {
        assertEquals("Please enter any one of the following numbers only (1,2,3,4,5,6)",
                ErrorOutputProvider.printErrorChoiceStatement());
    }

    @Test
    void printMessageAccordingToException() {
        assertEquals("No such id present in database",
                ErrorOutputProvider.printMessageAccordingToException(new WalletException("At get")));
    }
}