package app.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TransactionTest {

    private final Transaction testTransaction = new Transaction(1,2,100.0);

    @Test
    void returnCorrectTransactionWhenAllThreeFieldsGiven(){
        Transaction transactionWithConstructorAllValues =
                new Transaction(1, 2, 10.0);

        assertEquals(1, (int) transactionWithConstructorAllValues.getCustomerId());

        assertEquals(2, (int) transactionWithConstructorAllValues.getReceiverId());

        assertEquals(10.0, transactionWithConstructorAllValues.getAmount());
    }

    @Test
    void returnCorrectTransactionWhenTwoFieldsGiven(){
        Transaction transactionWithConstructorAllValues =
                new Transaction(1,10.0);

        assertEquals(1, (int) transactionWithConstructorAllValues.getCustomerId());

        assertEquals(0, (int) transactionWithConstructorAllValues.getReceiverId());

        assertEquals(10.0, transactionWithConstructorAllValues.getAmount());
    }

    @Test
    void ReturnCorrectCustomerId(){
        assertEquals(1, (int) testTransaction.getCustomerId());
    }

}