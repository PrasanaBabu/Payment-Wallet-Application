package app.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WalletTestShould {
    private final Wallet testWallet = new Wallet();

    @Test
    void returnCorrectWalletDetailsPassedThroughConstructor(){
        Wallet testWalletWithConstructorDetails =
                new Wallet(2,"Arun", 10.0, "root");
        assertEquals(2, (int) testWalletWithConstructorDetails.getId());
        assertEquals("Arun", testWalletWithConstructorDetails.getName());
        assertEquals(10.0, testWalletWithConstructorDetails.getBalance());
        assertEquals("root", testWalletWithConstructorDetails.getPassword());

    }
    @Test
    void setAndReturnCorrectName(){
        testWallet.setName("phonepe");
        assertEquals("phonepe", testWallet.getName());
    }
    @Test
    void setAndReturnCorrectId(){
        testWallet.setId(1);
        assertEquals(1, (int) testWallet.getId());
    }
    @Test
    void setAndReturnCorrectBalance(){
        testWallet.setBalance(10.0);
        assertEquals(10.0, testWallet.getBalance());
    }
    @Test
    void setAndReturnCorrectPassword(){
        testWallet.setPassword("root");
        assertEquals("root", testWallet.getPassword());
    }



}