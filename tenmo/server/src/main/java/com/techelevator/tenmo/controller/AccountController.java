package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthorized()")
public class AccountController {

    private AccountDao accountDao;

    public AccountController(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public List<Account> getAllAccounts(){
        List<Account> accounts = accountDao.getAllAccounts();
        return accounts;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @RequestMapping(path = "/accounts/user/{id}", method = RequestMethod.GET)
    public Account getAccountByUserId(@PathVariable int id){
        Account account = accountDao.getAccountByUserId(id);
        return account;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @RequestMapping(path = "/accounts/{id}/balance", method = RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable int id){
        BigDecimal balance = accountDao.getAccountBalance(id);
        return balance;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @RequestMapping(path = "/accounts/{id}", method = RequestMethod.GET)
    public Account getAccountByAccountId(@PathVariable int id){
        Account account = accountDao.getAccountById(id);
        return account;
    }

}
