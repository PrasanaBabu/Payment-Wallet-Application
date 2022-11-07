package app.service;

import app.dao.WalletDao;
import app.dao.WalletDaoImpl;
import app.dto.Wallet;
import app.exception.WalletDaoException;
import app.exception.WalletException;

public class WalletServiceImpl implements WalletService {
    public static final int MINIMUM_AMOUNT_FOR_TRANSFER = 1;
    public static final double MINIMUM_AMOUNT_TO_ADD = 1.0;
    private WalletDao walletDao = new WalletDaoImpl();
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
            if (wallet != null && wallet.getPassword().equals(enteredPassword))
                return true;
        } catch (WalletDaoException e){
            throw new WalletException(e.getMessage());
        }

        return false;
    }
    @Override
    public Double addFundsToWallet(Integer walletId, Double amountToAdd) throws WalletException{
        try {
            if(amountToAdd < MINIMUM_AMOUNT_TO_ADD)
                throw new WalletException("Amount to add must be greater than or equal to 1");
            Wallet wallet;
            wallet = walletDao.getWalletById(walletId);

            Double newBalance = wallet.getBalance() + amountToAdd;
            wallet.setBalance(newBalance);
            walletDao.updateWallet(wallet);
        } catch (WalletDaoException e){
            throw new WalletException(e.getMessage());
        }

        return amountToAdd;
    }
    @Override
    public Double showWalletBalance(Integer walletId) throws WalletException {
        Wallet wallet;
        try {
            wallet = walletDao.getWalletById(walletId);
        } catch (WalletDaoException e){
            throw new WalletException(e.getMessage());
        }
        return wallet.getBalance();
    }
    @Override
    public boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException {
        Wallet senderWallet;
        if(amount < MINIMUM_AMOUNT_FOR_TRANSFER)
            throw new WalletException("Amount Low For Transfer");
        try {
            senderWallet = walletDao.getWalletById(fromId);
        } catch (Exception e){
            throw new WalletException("From Id Invalid");
        }

        Wallet receiverWallet;

        try {
            receiverWallet = walletDao.getWalletById(toId);
        } catch (Exception e){
            throw new WalletException("Receiver Id Invalid");
        }

        Double senderCurrentBalance = senderWallet.getBalance();
        if (senderCurrentBalance < amount) {
            throw new WalletException("Sender wallet Insufficient Balance");
        }
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
        return true;
    }
    @Override
    public Wallet unregisterWallet(Integer walletId, String password) throws WalletException {
        Wallet walletDeleted;
        try {
            walletDeleted = walletDao.deleteWalletById(walletId);
        } catch (WalletDaoException e) {
            throw new WalletException(e.getMessage());
        }

        return walletDeleted;
    }
    @Override
    public Boolean withdrawFundsFromWallet(Integer walletId, Double amountToWithdraw) throws WalletException {
        Wallet wallet;
        try{
            wallet = walletDao.getWalletById(walletId);

            if (amountToWithdraw > wallet.getBalance())
                throw new WalletException("Insufficient Balance");
            Double newBalance = wallet.getBalance() - amountToWithdraw;
            wallet.setBalance(newBalance);
            walletDao.updateWallet(wallet);
        } catch (WalletDaoException e){
            throw new WalletException(e.getMessage());
        }

        return true;
    }
}
