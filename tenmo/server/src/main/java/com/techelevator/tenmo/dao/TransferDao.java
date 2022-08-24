package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
    public Transfer getTransferId(int id);
    public BigDecimal getAmount(int id);
    public int getAccountFrom(int accountId);
    public  int getAccountTo();
    public List<Transfer> getAllTransfers();
    public List<Transfer> getTransfersByID(int id);

}
