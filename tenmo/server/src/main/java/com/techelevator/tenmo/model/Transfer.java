package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    //variables
    private int transferId;
    private int transferTypeId;
    private int accountFrom;
    private int accountTo;
    private  int transferStatusId;
    private BigDecimal amount;

    //constructor


    public Transfer(int transferId, int transferTypeId, int accountFrom, int transferStatusId, int accountTo, BigDecimal amount) {
        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.transferStatusId = transferStatusId;
        this.amount = amount;
    }

    public Transfer() {
    }

    //getter setter
    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transfer: " + " Amount: " + getAmount() + " Sender: " + getAccountFrom() + " Recipient: " + getAccountTo();
    }
}
