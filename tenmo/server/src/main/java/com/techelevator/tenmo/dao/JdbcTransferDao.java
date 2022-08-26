package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferStatuses;
import com.techelevator.tenmo.model.TransferTypes;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Transfer getTransferId(int id) {
        Transfer transfer = new Transfer();
        String sql = "SELECT * transfer WHERE transfer_id =?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        if(result.next()){
            transfer = mapRowToTransfer(result);
        }
        return transfer;
    }

    @Override
    public BigDecimal getAmount(int id) {
        Transfer transfer = new Transfer();
        BigDecimal amount;
        String sql ="SELECT amount FROM transfer WHERE transfer_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        if(result.next()){
            transfer = mapRowToTransfer(result);
          amount = new BigDecimal (String.valueOf(transfer.getAmount()));
        }
        return null;
    }

    @Override
    public List<Transfer> getAllTransfers() {
        List<Transfer> getAllTransfers = new ArrayList<>();
        String sql ="SELECT * FROM transfer";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next()){
            getAllTransfers.add(mapRowToTransfer(result));
        }
        return getAllTransfers;
    }

    @Override
    public List<Transfer> getTransfersByID(int id) {
        List<Transfer> getAllTransfersById = new ArrayList<>();
        String sql ="SELECT * FROM transfer WHERE transfer_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next()){
            getAllTransfersById.add(mapRowToTransfer(result));
        }
        return getAllTransfersById;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        TransferTypes transferTypes = new TransferTypes();
        TransferStatuses transferStatuses = new TransferStatuses();
        if(transfer.getAccountFrom() == transfer.getAccountTo()){
            System.out.println("You cannot send money to yourself");
        } // else // if accountTo is not equal to acountFrom then type is a send type  transfer.setTransferTypeId = 1 (Send)
                          //  if the type is send then its approved
        else if (transfer.getAccountTo() != transfer.getAccountFrom()){

            String sql = "INSERT INTO transfer (transfer_type_id, account_from, account_to, transfer_status_id, amount) VALUES (?, ?, ?, ?, ?) RETURNING transfer_id";

            Integer newTransferId;
            newTransferId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getTransferTypeId(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getTransferStatusId(), transfer.getAmount());
            transfer.setTransferStatusLabel("Approved");
            transfer.setTransferTypeLabel("Send");
            transfer.setTransferId(newTransferId);
        }
        // request: else if aocountTo belongs to user thats logged in.  setTransferType to request
        // status as pending

        // user can see a list of pending requests .. user selects request to approve or reject transfer
        // if they choose approved then we add balance and subtract balance we insert it into the database

        return transfer;
    }

    @Override
    public void updateTransferStatus(int statusId, int transferId) {
        JdbcAccountDao jdbcAccountDao = new JdbcAccountDao(jdbcTemplate.getDataSource());
        String sql = "UPDATE transfer SET transfer_status_id = ? WHERE transfer_id = ?";
        jdbcTemplate.update(sql, statusId, transferId);

    }



    public Transfer mapRowToTransfer(SqlRowSet result){
        Transfer transfer = new Transfer();
        transfer.setTransferId(result.getInt("transfer_id"));
        transfer.setAccountFrom(result.getInt("account_from"));
        transfer.setAccountTo(result.getInt("account_to"));
        transfer.setAmount(result.getBigDecimal("amount"));
        transfer.setTransferTypeId(result.getInt("transfer_type_id"));
        transfer.setTransferStatus(result.getInt("transfer_status_id"));
        return transfer;
    }

}
