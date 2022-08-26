package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    public BigDecimal getAccountBalance(int id);
    public List<Account> getAllAccounts();
    public Account getAccountById(int id);
    public BigDecimal addBalance(int id, BigDecimal amount);
    public BigDecimal subtractBalance(int id, BigDecimal amount);
    public Account getAccountByUserId(int id);
    public boolean checkValidTransfer(BigDecimal amount, int id);
}
