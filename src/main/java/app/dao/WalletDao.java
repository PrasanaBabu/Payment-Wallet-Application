package app.dao;

import app.dto.Wallet;
import app.exception.WalletDaoException;
import app.exception.WalletException;

public interface WalletDao {

    Wallet addWallet(Wallet newWallet) throws WalletDaoException;
    Wallet getWalletById(Integer walletId) throws WalletDaoException;
    Wallet updateWallet(Wallet updateWallet) throws WalletDaoException;
    Wallet deleteWalletById(Integer walletId) throws WalletDaoException;


}
