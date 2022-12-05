package app.service;

import app.dto.Wallet;
import app.exception.WalletException;

@SuppressWarnings("SameReturnValue")
public interface WalletService {

    Wallet registerWallet(Wallet newWallet) throws WalletException;
    boolean login(Integer walletId, String password) throws WalletException;
    Double addFundsToWallet(Integer walletId, Double amountToAdd) throws WalletException;
    Double showWalletBalance(Integer walletId) throws WalletException;
    boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException;
    Wallet unregisterWallet(Integer walletId, String password) throws WalletException;
    Boolean withdrawFundsFromWallet(Integer walletId, Double amountToWithdraw) throws WalletException;

}
