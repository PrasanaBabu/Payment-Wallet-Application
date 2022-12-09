package app.controller;

import app.exception.WalletException;
import app.service.WalletServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsoleControllerTest {
    
    @Test
    @Order(1)
    void checkBalanceFromMain() throws WalletException {
        Double actual = ConsoleController.callShowBalanceWithUserInput
                (new WalletServiceImpl(), 11);

        assertEquals(1143.0, actual);
    }
    @Test
    @Order(2)

    void addUserCorrectlyFromMain() throws WalletException {
        ConsoleController.addUserWithGivenDetails(new WalletServiceImpl(),
                11, "Arun", "root");


        assertEquals(0.0, ConsoleController.callShowBalanceWithUserInput(new WalletServiceImpl(), 12));
    }
    @Test
    @Order(3)
    void deleteFromMain() throws WalletException {
        ConsoleController.callDeleteWalletWithUserInput(new WalletServiceImpl(),
                12, "root");

        Executable executable = () -> ConsoleController.callDeleteWalletWithUserInput(new WalletServiceImpl(),
                12, "root");;

        // then
        WalletException thrown = assertThrows(WalletException.class, executable);
        assertTrue(thrown.getMessage().contains("Invalid ID"));

    }
}