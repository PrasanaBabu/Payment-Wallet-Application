package app.controller;
import java.util.Scanner;
public class ConsoleInputProvider {
    private static final Scanner scanner = new Scanner(System.in);
    public static char getChoiceToContinueFromConsole() {
        char choiceToContinue;
        System.out.println("Do you wish to continue(Y/N)");
        choiceToContinue = scanner.next().charAt(0);
        return choiceToContinue;
    }
    public static Double getAmountToTransferThroughConsole() {
        System.out.println("Enter the amount you want to withdraw to your wallet : ");
        Double amountToTransfer = scanner.nextDouble();
        scanner.nextLine();
        return amountToTransfer;
    }
    public static Double getAmountToWithdrawThroughConsole() {
        System.out.println("Enter the amount you want to withdraw to your wallet : ");
        double amountToWithdraw = scanner.nextDouble();
        scanner.nextLine();
        return amountToWithdraw;
    }

    public static int getOptionThroughConsole() {
        System.out.println("Enter any one choice from the following : ");
        System.out.println("1.Add/Register new user ");
        System.out.println("2.Add funds to wallet ");
        System.out.println("3.Check current wallet balance ");
        System.out.println("4.Delete wallet details ");
        System.out.println("5.Withdraw funds from wallet ");
        System.out.println("6.Transfer funds from wallet to another person");
        System.out.println("Enter the corresponding number : ");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public static double getAmountToCreditThroughConsole() {
        System.out.println("Enter the amount you want to add to your wallet : ");
        double amountToCredit = scanner.nextDouble();
        scanner.nextLine();
        return amountToCredit;
    }

    public static String getNameThroughConsole() {
        System.out.println("Enter name : ");
        return scanner.nextLine();
    }

    public static String getPasswordThroughConsole() {
        System.out.println("Enter your password");
        return scanner.nextLine();
    }

    public static int getWalletIdThroughConsole() {
        int walletId;
        System.out.println("Enter your wallet Id : ");
        walletId = scanner.nextInt();
        scanner.nextLine();
        return walletId;
    }

}
