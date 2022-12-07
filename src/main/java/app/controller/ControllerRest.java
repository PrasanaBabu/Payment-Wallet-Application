package app.controller;

import app.dto.Wallet;
import app.dto.WalletDto;
import app.exception.WalletException;
import app.service.WalletService;
import app.service.WalletServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class ControllerRest {

    final WalletService walletService = new WalletServiceImpl();

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
    public String addAmount(@RequestBody WalletDto walletDto) throws WalletException {
        System.out.println("cus id = " + walletDto.getCustomerId() + "amt = " + walletDto.getAmount());
        Double updatedBalance = walletService.addFundsToWallet(
                walletDto.getCustomerId(),
                walletDto.getAmount()
        );

        System.out.println("added amount to " + walletDto.getCustomerId());
        return "Amount added successfully. New Balance = " + updatedBalance;
    }

    @GetMapping("/wallet/check/{id}")
    public String balanceCheck(@PathVariable("id") Integer walletId ) throws WalletException {
        System.out.println("Inside check");
        return String.valueOf(walletService.showWalletBalance(walletId));
    }

    @PostMapping("wallet/transfer")
    public String transfer(@RequestBody WalletDto walletDto) throws WalletException {
        walletService.fundTransfer(
                walletDto.getCustomerId(),
                walletDto.getReceiverId(),
                walletDto.getAmount()
        );
        return "Transfer Successful";
    }

    @DeleteMapping("wallet/unregister")
    public String unRegister(@RequestBody Wallet wallet) throws WalletException {

        Wallet deletedWallet = walletService.unregisterWallet(wallet.getId(), wallet.getPassword());

        return "Deleted Wallet with ID : " + wallet.getId() + " successfully.";
    }

    @PatchMapping("wallet/withdraw")
    public String withdrawFunds(@RequestBody WalletDto walletDto) throws WalletException {
        walletService.withdrawFundsFromWallet(walletDto.getReceiverId(), walletDto.getAmount());
        return "Successful";
    }



}
