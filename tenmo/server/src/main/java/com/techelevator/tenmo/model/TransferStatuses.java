package com.techelevator.tenmo.model;

public class TransferStatuses {

    private int transferStatusId;
    private String transferStatusLabel;

    public int getTransferStatusId() {
        if(transferStatusLabel.equals("Approved")){
            return 1;
        } else if(transferStatusLabel.equals("Denied")){
            return 2;
        } return 3;
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
