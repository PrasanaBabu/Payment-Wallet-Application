package app.service;

import app.dao.WalletDao;
import app.dao.WalletDaoImpl;
import app.dto.Wallet;
import app.exception.WalletDaoException;
import app.exception.WalletException;

public class WalletServiceImpl implements WalletService {
    public static final int MINIMUM_AMOUNT_FOR_TRANSFER = 1;
    public static final double MINIMUM_AMOUNT_TO_ADD = 1.0;
    private final WalletDao walletDao = new WalletDaoImpl();
    @Override
    public Wallet registerWallet(Wallet newWallet) throws WalletException {
        try {
            return walletDao.addWallet(newWallet);
        } catch (WalletDaoException e){
            throw new WalletException(e.getMessage());
        }
    }
    @Override
    public boolean login(Integer walletId, String enteredPassword) throws WalletException{
        try {
            Wallet wallet;
            wallet = walletDao.getWalletById(walletId);
            if (idPasswordMatch(enteredPassword, wallet)) {
                return true;
            }
        } catch (WalletDaoException e){
            throw new WalletException(e.getMessage());
        }

        return false;
    }

    private static boolean idPasswordMatch(String enteredPassword, Wallet wallet) {
        return wallet != null && wallet.getPassword().equals(enteredPassword);
    }

    @Override
    public Double addFundsToWallet(Integer walletId, Double amountToAdd) throws WalletException {
        try {
            if (amountToAdd < MINIMUM_AMOUNT_TO_ADD) {
                throw new WalletException("Amount to add must be greater than or equal to 1");
            }
            return getNewBalanceAfterAdding(walletId, amountToAdd);
        } catch (WalletDaoException e) {
            throw new WalletException(e.getMessage());
        }
    }

    private Double getNewBalanceAfterAdding(Integer walletId, Double amountToAdd) throws WalletDaoException {
        Double newBalance;
        Wallet wallet;
        wallet = walletDao.getWalletById(walletId);

        newBalance = wallet.getBalance() + amountToAdd;
        wallet.setBalance(newBalance);
        walletDao.updateWallet(wallet);
        return newBalance;
    }

    @Override
    public Double showWalletBalance(Integer walletId) throws WalletException {
        Wallet wallet;
        try {
            wallet = walletDao.getWalletById(walletId);
            return wallet.getBalance();
        } catch (WalletDaoException e){
            throw new WalletException(e.getMessage());
        }

    }
    @Override
    public boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException {

        if(amount < MINIMUM_AMOUNT_FOR_TRANSFER) {
            throw new WalletException("Amount Low For Transfer");
        }
        Wallet senderWallet = getSenderWallet(fromId);
        Wallet receiverWallet = getReceiverWallet(toId);

        transferGivenAmount(amount, senderWallet, receiverWallet);
        return true;
    }

    private void transferGivenAmount(Double amount, Wallet senderWallet, Wallet receiverWallet) throws WalletException {
        Double senderCurrentBalance = getSenderCurrentBalance(amount, senderWallet);
        Double senderFinalAmount = senderCurrentBalance - amount;
        Double receiverCurrentBalance = receiverWallet.getBalance();
        Double receiverFinalAmount = receiverCurrentBalance + amount;

        senderWallet.setBalance(senderFinalAmount);
        receiverWallet.setBalance(receiverFinalAmount);

        try {
            walletDao.updateWallet(senderWallet);
            walletDao.updateWallet(receiverWallet);
        } catch (WalletDaoException e){
            throw new WalletException(e.getMessage());
        }
    }

    private static Double getSenderCurrentBalance(Double amount, Wallet senderWallet) throws WalletException {
        Double senderCurrentBalance = senderWallet.getBalance();
        if (senderCurrentBalance < amount) {
            throw new WalletException("Sender wallet Insufficient Balance");
        }
        return senderCurrentBalance;
    }

    private Wallet getReceiverWallet(Integer toId) throws WalletException {
        Wallet receiverWallet;

        try {
            receiverWallet = walletDao.getWalletById(toId);
        } catch (Exception e){
            throw new WalletException("Receiver Id Invalid");
        }
        return receiverWallet;
    }

    private Wallet getSenderWallet(Integer fromId) throws WalletException {
        Wallet senderWallet;
        try {
            senderWallet = walletDao.getWalletById(fromId);
        } catch (Exception e){
            throw new WalletException("From Id Invalid");
        }
        return senderWallet;
    }

    @Override
    public Wallet unregisterWallet(Integer walletId, String password) throws WalletException {
        try {
            return walletDao.deleteWalletById(walletId);
        } catch (WalletDaoException e) {
            throw new WalletException(e.getMessage());
        }
    }
    @Override
    public Double withdrawFundsFromWallet(Integer walletId, Double amountToWithdraw) throws WalletException {

        try{
            Wallet wallet = walletDao.getWalletById(walletId);

            if (amountToWithdraw > wallet.getBalance()){
                throw new WalletException("Insufficient Balance");
            }
            else {
                return newBalanceAfterWithdrawing(amountToWithdraw, wallet);
            }
        } catch (WalletDaoException e){
            throw new WalletException(e.getMessage());
        }
    }

    private Double newBalanceAfterWithdrawing(Double amountToWithdraw, Wallet wallet) throws WalletDaoException {
        Double newBalance = wallet.getBalance() - amountToWithdraw;
        wallet.setBalance(newBalance);
        walletDao.updateWallet(wallet);
        return newBalance;
    }
}
