package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    //variables
    private int transferId;
    private int transferTypeId;
    private int accountFrom;
    private int accountTo;
    private  boolean transferStatus;
    private BigDecimal amount;

    //constructor


    public Transfer(int transferId, int transferTypeId, int accountFrom, int accountTo, boolean transferStatus, BigDecimal amount) {
        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
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

//    public int getTransferTypeId() {
//        return transferTypeId;
//    }
//
//    public void setTransferTypeId(int transferTypeId) {
//        this.transferTypeId = transferTypeId;
//    }

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

//    public boolean isTransferStatus() {
//        return transferStatus;
//    }
//
//    public void setTransferStatus(boolean transferStatus) {
//        this.transferStatus = transferStatus;
//    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + transferId +
                ", transferTypeId=" + transferTypeId +
                ", accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", transferStatus=" + transferStatus +
                ", amount=" + amount +
                '}';
    }
}
