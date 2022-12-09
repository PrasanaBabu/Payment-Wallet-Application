package app.controller;

import app.dto.Wallet;
import app.dto.Transaction;
import app.exception.WalletException;
import app.service.WalletService;
import app.service.WalletServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class ControllerRest {

    private final WalletService walletService = new WalletServiceImpl();

    @GetMapping("/")
    public String greeting(){
        return "Hi";
    }


    @PostMapping("/wallet/new")
    public String addNewWallet(@RequestBody Wallet newWallet) throws WalletException {
            Wallet newlyAdded = walletService.registerWallet(newWallet);

        System.out.println(newWallet.toString());
        return "Added Successfully";
    }

    @PostMapping("/wallet/login")
    public String toLogIn(@RequestBody Wallet wallet) throws WalletException {

        boolean loginSuccess = walletService.login(wallet.getId(), wallet.getPassword());

        return loginSuccess? "Login Passed" : "Login Failed. Incorrect ID/ Password";
    }
    @PatchMapping("wallet/add")
    public String addAmount(@RequestBody Transaction transaction) throws WalletException {
        System.out.println("cus id = " + transaction.getCustomerId() + "amt = " + transaction.getAmount());
        Double updatedBalance = walletService.addFundsToWallet(
                transaction.getCustomerId(),
                transaction.getAmount()
        );

        System.out.println("added amount to " + transaction.getCustomerId());
        return "Amount added successfully. New Balance = " + updatedBalance;
    }

    @GetMapping("/wallet/check/{id}")
    public String balanceCheck(@PathVariable("id") Integer walletId ) throws WalletException {
        System.out.println("Inside check");
        return String.valueOf(walletService.showWalletBalance(walletId));
    }

    @PostMapping("wallet/transfer")
    public String transfer(@RequestBody Transaction transaction) throws WalletException {
        walletService.fundTransfer(
                transaction.getCustomerId(),
                transaction.getReceiverId(),
                transaction.getAmount()
        );
        return "Transfer Successful";
    }

    @DeleteMapping("wallet/unregister")
    public String unRegister(@RequestBody Wallet wallet) throws WalletException {

        Wallet deletedWallet = walletService.unregisterWallet(wallet.getId(), wallet.getPassword());

        return "Deleted Wallet with ID : " + wallet.getId() + " successfully.";
    }

    @PatchMapping("wallet/withdraw")
    public String withdrawFunds(@RequestBody Transaction transaction) throws WalletException {
        walletService.withdrawFundsFromWallet(transaction.getCustomerId(), transaction.getAmount());
        return "Successful";
    }



}
