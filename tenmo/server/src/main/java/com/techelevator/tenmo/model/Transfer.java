package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    //variables
    private int transferId;
    private String transferStatus;
    private int accountFrom;
    private int accountTo;
    private BigDecimal amount;


    //constructor
    public Transfer(int transferId, int accountFrom, int accountTo, String transferStatus, BigDecimal amount) {
        this.transferId = transferId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.transferStatus = transferStatus;
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

    public String getTransferStatus() {
        return transferStatus;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }




    @Override
    public String toString() {
        return "Transfer: " + " Amount: " + getAmount() + " Sender: " + getAccountFrom() + " Recipient: " + getAccountTo();
    }
}
