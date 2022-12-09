package app.controller;

import app.dto.Wallet;
import app.exception.WalletException;
import app.service.WalletService;
import app.service.WalletServiceImpl;

import java.util.Scanner;

public class ConsoleController {
    static final Scanner scanner = new Scanner(System.in);
    static Integer lastId = 2;
    public static void main(String[] args) {
        WalletService walletService = new WalletServiceImpl();
        System.out.println("************************************************************************************************************");

        char choiceToContinue;

        do {
            int option = ConsoleInputProvider.getOptionThroughConsole();

            switch (option){
                case 1:
                    String name = ConsoleInputProvider.getNameThroughConsole();
                    String password = ConsoleInputProvider.getPasswordThroughConsole();
                    String checkPassword = ConsoleInputProvider.getPasswordThroughConsole();

                    boolean passwordsMatch = password.equals(checkPassword);

                    if (passwordsMatch){
                        addUserWithGivenDetails(walletService, ++lastId , name, password);
                    }
                    else {
                        printIdPasswordIncorrect();
                    }
                    break;

                case 2:
                    Integer walletId = ConsoleInputProvider.getWalletIdThroughConsole();
                    try {
                            String userEnteredPassword = ConsoleInputProvider.getPasswordThroughConsole();
                            boolean userAuthenticated = walletService.login(walletId, userEnteredPassword);
                            if (userAuthenticated){
                                addFundToUserGivenId(walletService, walletId);
                            }
                            else
                                printIdPasswordIncorrect();

                    } catch (WalletException e) {
                        printMessageAccordingToException(e);
                        }
                    break;

                case 3:
                    walletId = ConsoleInputProvider.getWalletIdThroughConsole();
                    try {
                            String userEnteredPassword = ConsoleInputProvider.getPasswordThroughConsole();
                            boolean userAuthenticated = walletService.login(walletId, userEnteredPassword);
                            if (userAuthenticated){
                                Double currentBalance = callShowBalanceWithUserInput(walletService, walletId);
                                System.out.println("Current Balance in your wallet = " + currentBalance);
                            }
                            else
                               printIdPasswordIncorrect();
                    } catch (WalletException e) {
                        printMessageAccordingToException(e);
                    }
                    break;

                case 4:
                    walletId = ConsoleInputProvider.getWalletIdThroughConsole();
                    String userEnteredPassword = ConsoleInputProvider.getPasswordThroughConsole();

                    try {
                        boolean userAuthenticated = walletService.login(walletId, userEnteredPassword);
                        if (userAuthenticated) {
                            Wallet deletedWallet = callDeleteWalletWithUserInput(walletService, walletId, userEnteredPassword);
                            System.out.println("Deleted the following wallet successfully " + deletedWallet);
                        } else{
                            printIdPasswordIncorrect();
                        }
                    } catch (WalletException e) {
                        printMessageAccordingToException(e);
                    }
                    break;

                case 5:
                    walletId = ConsoleInputProvider.getWalletIdThroughConsole();
                    try {
                            userEnteredPassword = ConsoleInputProvider.getPasswordThroughConsole();
                            boolean userAuthenticated = walletService.login(walletId, userEnteredPassword);
                            if (userAuthenticated){
                                withdrawFromWallet(walletService, walletId);
                            }
                            else
                                System.out.println("Password Incorrect");

                    } catch (WalletException e) {
                        printMessageAccordingToException(e);
                    }
                    break;

                case 6:
                    Integer senderWalletId = ConsoleInputProvider.getWalletIdThroughConsole();
                    userEnteredPassword = ConsoleInputProvider.getPasswordThroughConsole();
                    try {
                        boolean userAuthenticated = walletService.login(senderWalletId, userEnteredPassword);
                        if (userAuthenticated){
                            System.out.println("Enter details of receiver");
                            Integer receiverWalletId = ConsoleInputProvider.getWalletIdThroughConsole();
                            Double amountToTransfer = ConsoleInputProvider.getAmountToTransferThroughConsole();
                            walletService.fundTransfer(senderWalletId,receiverWalletId,amountToTransfer);
                        }
                        else
                            printIdPasswordIncorrect();

                    } catch (WalletException e) {
                        printMessageAccordingToException(e);
                    }
                    break;

                default:
                    printErrorChoiceStatement();
            }
            choiceToContinue = ConsoleInputProvider.getChoiceToContinueFromConsole();
        }while (choiceToContinue == 'Y' || choiceToContinue == 'y');

        System.out.println();
        System.out.println("Thank you for using the application ❤❤");
        System.out.println("************************************************************************************************************");

    }

    public static Double callShowBalanceWithUserInput(WalletService walletService, Integer walletId) throws WalletException {
        Double currentBalance = walletService.showWalletBalance(walletId);
        return currentBalance;
    }

    public static Wallet callDeleteWalletWithUserInput(WalletService walletService, Integer walletId, String userEnteredPassword) throws WalletException {
        Wallet deletedWallet = walletService.unregisterWallet(walletId, userEnteredPassword);
        return deletedWallet;
    }

    public static void printIdPasswordIncorrect() {
        System.out.println(ErrorOutputProvider.printIdPasswordIncorrect());
    }

    public static void printErrorChoiceStatement() {
        System.out.println(ErrorOutputProvider.printErrorChoiceStatement());
    }

    public static void printMessageAccordingToException(WalletException e) {
        System.out.println(ErrorOutputProvider.printMessageAccordingToException(e));
    }

    public static void withdrawFromWallet(WalletService walletService, Integer walletId) throws WalletException {
        Double newBalance = walletService.withdrawFundsFromWallet(walletId, ConsoleInputProvider.getAmountToWithdrawThroughConsole());
            System.out.println("Current Balance = " + newBalance);
    }

    public static void addFundToUserGivenId(WalletService walletService, int walletId) {
        try {
            double amountToCredit = ConsoleInputProvider.getAmountToCreditThroughConsole();
            Double amountAdded = walletService.addFundsToWallet(walletId, amountToCredit);
            System.out.println("amountAdded = " + amountAdded);
        } catch (WalletException e) {
            ErrorOutputProvider.printMessageAccordingToException(e);
        }
    }

    public static void addUserWithGivenDetails(WalletService walletService, int lastId, String name, String password) {
        Double balance = 0.0;
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(lastId + 1, name, balance, password));
            System.out.println("Your new wallet details : " + wallet);
        } catch (WalletException e) {
            ErrorOutputProvider.printMessageAccordingToException(e);
        }
    }
}
