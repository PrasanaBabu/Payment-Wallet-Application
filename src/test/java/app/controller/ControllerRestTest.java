package app.controller;

import app.dto.Transaction;
import app.dto.Wallet;
import app.exception.WalletException;
import app.service.WalletService;
import app.service.WalletServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class ControllerRestTest {

    ControllerRest controllerRest = new ControllerRest();

    @Test
    void returnHiWhenGreetingCalled() {
        assertEquals("Hi", controllerRest.greeting());
    }

    @Order(1)
    @Nested
    class addNewWalletMethodTests{


        @Test
        void returnAddedSuccessfully() throws WalletException {
            Wallet testWallet = new Wallet(30, "GPay", 0.0, "root");

            String actual = controllerRest.addNewWallet(testWallet);
            assertEquals("Added Successfully", actual);
        }
        @Test
        void throwWalletExceptionForRedundantId(){
            Integer alreadyPresentId = 1;

            // when
            Executable executable = () -> controllerRest.addNewWallet(new Wallet(alreadyPresentId,"p", 0.0,"root"));

            // then
            WalletException thrown = assertThrows(WalletException.class, executable);
            assertTrue(thrown.getMessage().contains("Redundant Id"));
        }
    }
    @Order(2)
    @Nested
    class loginTests{

        @Test
        void returnTrueWhenIdPasswordMatch() throws WalletException {
            Wallet correctIdPasswordWallet = new Wallet(30, "GPay", 0.0, "root");
            assertEquals("Login Passed",
                    controllerRest.toLogIn(correctIdPasswordWallet));
        }
        @Test
        void returnFalseWhenIdPasswordMisMatch() throws WalletException {
            Wallet correctIdIncorrectPasswordWallet = new Wallet(30, "GPay", 0.0, "00");
            assertEquals("Login Failed. Incorrect ID/ Password",
                    controllerRest.toLogIn(correctIdIncorrectPasswordWallet));
        }

        @Test
        void throwWalletExceptionForInvalidId(){
            Integer invalidId = 10000;

            // when
            Executable executable = () -> controllerRest.toLogIn(new Wallet(invalidId,"p", 0.0,"root"));

            // then
            WalletException thrown = assertThrows(WalletException.class, executable);
            assertTrue(thrown.getMessage().contains("Invalid ID"));
        }

    }


    @Order(2)
    @Nested
    class addAmountTests{

        @Test
        void returnNewBalanceWhenAmountAddedSuccessfully() throws WalletException {
            Transaction validTransaction = new Transaction(30, 10.0);
            assertEquals("Amount added successfully. New Balance = 10.0",
                    controllerRest.addAmount(validTransaction));
        }
    }


    @Order(3)
    @Nested
    class withdrawFundTests{

        @Test
        void returnNewBalanceWhenAmountWithdrawnSuccessfully() throws WalletException {
            Transaction validTransaction = new Transaction(30, 10.0);
            assertEquals("Successful",
                    controllerRest.withdrawFunds(validTransaction));

        }

    }

    @Order(4)
    @Nested
    class balanceCheckTests{

        @Test
        void returnCorrectBalance() throws WalletException {
            assertEquals("0.0",
                    controllerRest.balanceCheck(30));
        }

    }


    @Order(5)
    @Nested
    class transferTests{

        @Test
        void returnSuccessfulWhenTransferSuccess() throws WalletException {
            Transaction validTransaction = new Transaction(1,30, 10.0);
            assertEquals("Transfer Successful",
                    controllerRest.transfer(validTransaction));
            Transaction revValidTransaction = new Transaction(30,1, 10.0);
            assertEquals("Transfer Successful",
                    controllerRest.transfer(revValidTransaction));

        }
    }

    @Nested
    @Order(6)
    class unRegisterTests{

        @Test
        void returnDeletedWallet() throws WalletException {
            Wallet testWallet = new Wallet(30, "GPay", 0.0, "root");
            assertEquals("Deleted Wallet with ID : 30 successfully.",
                    controllerRest.unRegister(testWallet));
        }
    }
}