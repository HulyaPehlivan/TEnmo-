package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.tree.TreeNode;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private TransferDao transferDao;
    private AccountDao accountDao;
    private UserDao userDao;

    public TransferController(TransferDao transferDao, AccountDao accountDao, UserDao userDao){
        this.transferDao = transferDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = "/transfer/{id}", method = RequestMethod.GET)
    public Transfer getTransferByTransferId(@Valid @PathVariable int id, Principal principal){
        Transfer transfer = new Transfer();
        String username = principal.getName();
        int actualUserId = userDao.findByUsername(username).getId();
        int accountFromId = transferDao.getTransferByTransferId(id).getAccountFrom();
        int accountToId = transferDao.getTransferByTransferId(id).getAccountTo();
        if(actualUserId == accountFromId || actualUserId == accountToId){
            transfer = transferDao.getTransferByTransferId(id);
        }
        return transfer;
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public Transfer createTransfer(@Valid @RequestBody Transfer transfer, Principal principal){
        transfer.setAccountFrom(userDao.findIdByUsername(principal.getName()));
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

    @GetMapping(path="/transfer/user/{id}")
    public List<Transfer> listTransfersByUserId(@Valid @PathVariable int id, Principal principal) {
        List<Transfer> transfers = new ArrayList<>();
        String userName = principal.getName();
        int actualUserId = userDao.findByUsername(userName).getId();

        if (id == actualUserId) {
            transfers = transferDao.getTransfersByUserID(actualUserId);
            transfers = transferDao.getTransfersByUserID(id);

        }
        return transfers;
    }
}
