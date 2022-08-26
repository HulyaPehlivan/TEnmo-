package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    //variables
    private int transferId;
    private int transferStatusId;
    private int transferTypeId;
    private int accountFrom;
    private String transferStatusLabel;
    private String transferTypeLabel;
    private int accountTo;
    private BigDecimal amount;


    //constructor
    public Transfer(int transferId, int transferTypeId, int accountFrom, int accountTo, int transferStatusId, BigDecimal amount) {
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
        TransferTypes transferTypes = new TransferTypes();
        transferTypes.setTransferTypeName(transferTypeLabel);
        this.transferTypeId = transferTypes.getTransferTypeId();
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

    public void setTransferStatus(int transferStatusId) {
        TransferStatuses transferStatuses = new TransferStatuses();
        transferStatuses.setTransferStatusLabel(transferStatusLabel);
        this.transferStatusId = transferStatuses.getTransferStatusId();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransferStatusLabel(){
        return transferStatusLabel; }

    public void setTransferStatusLabel(String transferStatusLabel) {
        this.transferStatusLabel = transferStatusLabel;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public String getTransferTypeLabel() {
        return transferTypeLabel;
    }

    public void setTransferTypeLabel(String transferTypeLabel) {
        this.transferTypeLabel = transferTypeLabel;
    }

    @Override
    public String toString() {
        return "Transfer: " + " Amount: " + getAmount() + " Sender: " + getAccountFrom() + " Recipient: " + getAccountTo();
    }
}
