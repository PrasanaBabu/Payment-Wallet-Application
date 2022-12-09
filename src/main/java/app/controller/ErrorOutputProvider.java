package app.controller;

import app.exception.WalletException;

public class ErrorOutputProvider {
    public static String printIdPasswordIncorrect() {
        return "ID/Password Incorrect";
    }

    public static String printErrorChoiceStatement() {
        return "Please enter any one of the following numbers only (1,2,3,4,5,6)";
    }

    public static String printMessageAccordingToException(WalletException e) {
        if (e.getMessage().contains("At get"))
            return "No such id present in database";
        else if (e.getMessage().contains("Amount to add must be greater than or equal to 1"))
            return "Amount to add must be greater than or equal to 1";
        else if (e.getMessage().contains("Insuf"))
            return "Insufficient Balance";
        else if (e.getMessage().contains("From Id Invalid"))
            return "Sender Id Invalid";
        else if(e.getMessage().contains("Receiver Id Invalid"))
            return "Receiver Id Invalid";
        else if(e.getMessage().contains("Sender wallet Insufficient Balance"))
            return "Sender wallet Insufficient Balance";
        else if(e.getMessage().contains("Amount Low For Transfer"))
            return "Enter amount greater than minimum amount to transfer";
        else
            return e.getMessage();
    }
}
