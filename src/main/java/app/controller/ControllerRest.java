package app.controller;

import app.dto.Wallet;
import app.exception.WalletException;
import app.service.WalletService;
import app.service.WalletServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerRest {

    WalletService walletService = new WalletServiceImpl();

    /*@GetMapping("/employees/{id}")
    public Wallet getEmployeeById(@PathVariable("id") int id) {
        Wallet walletById;
        try {
            walletById = walletService.getWalletById(id);
        } catch (WalletDaoException e) {
            throw new RuntimeException(e);
        }
        return walletById;
    }*/

    @PostMapping("/wallet/new")
    public String addNewWallet(@RequestBody Wallet newWallet){
        try {
            Wallet newlyAdded = walletService.registerWallet(newWallet);
        } catch (WalletException e) {
            return "Wallet not added something went wrong..";
        }
        return "Added Successfully";
    }

    @PostMapping("/wallet/login")
    public String toLogIn(@RequestBody Integer walletId, String password){
        try {
            walletService.login(walletId, password);
        } catch (WalletException e) {
            return "Login Failed";
        }
        return "Login Passed";
    }
    @PostMapping
    public String addAmount(@RequestBody Integer walletId, @RequestBody Double amountToAdd){
        try {
            walletService.addFundsToWallet(walletId, amountToAdd);
        } catch (WalletException e) {
            return "Failed to add";
        }
        return "Amount added successfully";
    }


}
