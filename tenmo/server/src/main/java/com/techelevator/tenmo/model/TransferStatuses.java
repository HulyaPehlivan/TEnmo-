package com.techelevator.tenmo.model;

public class TransferStatuses {

    private int transferStatusId;
    private String transferStatusLabel;

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public String getTransferStatusLabel() {
        return transferStatusLabel;
    }

    public void setTransferStatusLabel(String transferStatusLabel) {
        if(transferStatusId == 1){
            setTransferStatusLabel("Approved");
        }
    }
}
