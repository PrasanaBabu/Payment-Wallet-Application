package app.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TransactionTest {

    private final Transaction testTransaction = new Transaction(1,2,100.0);

    @Test
    void returnCorrectTransactionWhenAllThreeFieldsGiven(){
        Transaction transactionWithConstructorAllValues =
                new Transaction(1, 2, 10.0);

        assertTrue(transactionWithConstructorAllValues.getCustomerId() == 1);

        assertTrue(transactionWithConstructorAllValues.getReceiverId() == 2);

        assertTrue(transactionWithConstructorAllValues.getAmount() == 10.0);
    }

    @Test
    void returnCorrectTransactionWhenTwoFieldsGiven(){
        Transaction transactionWithConstructorAllValues =
                new Transaction(1,10.0);

        assertTrue(transactionWithConstructorAllValues.getCustomerId() == 1);

        assertTrue(transactionWithConstructorAllValues.getReceiverId() == 0);

        assertTrue(transactionWithConstructorAllValues.getAmount() == 10.0);
    }

    @Test
    void ReturnCorrectCustomerId(){
        assertTrue(testTransaction.getCustomerId() == 1);
    }

}