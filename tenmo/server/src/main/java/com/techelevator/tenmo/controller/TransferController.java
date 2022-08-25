package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.swing.tree.TreeNode;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TransferController {

    private TransferDao transferDao;
    private AccountDao accountDao;
    private UserDao userDao;

    public TransferController(TransferDao transferDao, AccountDao accountDao){
        this.transferDao = transferDao;
        this.accountDao = accountDao;
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public Transfer createTransfer(@Valid @RequestBody Transfer transfer){

        Transfer transfer1 = transferDao.createTransfer(transfer);

        try {
            updateAccountBalance(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
        } catch(Exception e){
            System.out.println(e);
        }

        return transfer;
    }

    private void updateAccountBalance(int accountFrom, int accountTo, BigDecimal amount){
        accountDao.subtractBalance(accountFrom, amount);
        accountDao.addBalance(accountTo, amount);
    }

    @RequestMapping(value = "/transfer/{id}", method = RequestMethod.GET)
    public List<Transfer> getTransfersById(@Valid @PathVariable int id){
        List<Transfer> transfers = transferDao.getTransfersByID(id);
        return transfers;
    }

    @RequestMapping(value = "/transfer/account/{id}", method = RequestMethod.GET)
    public List<Transfer> getTransfersByAccountId(){
        return null;
    }
    


}
