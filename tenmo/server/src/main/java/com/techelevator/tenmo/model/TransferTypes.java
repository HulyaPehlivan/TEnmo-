package com.techelevator.tenmo.model;

public class TransferTypes {

    public int transferTypeId;
    public String transferTypeName;

    public int getTransferTypeId() {
        if(transferTypeName.equals("Send")){
            return 1;
        }
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public String getTransferTypeName() {
        return transferTypeName;
    }

    public void setTransferTypeName(String transferTypeName) {
        this.transferTypeName = transferTypeName;
    }
}
