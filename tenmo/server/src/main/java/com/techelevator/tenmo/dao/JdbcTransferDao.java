package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    public int getAccountFrom(int accountId) {
        return 0;
    }

    @Override
    public int getAccountTo() {
        return 0;
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

    public Transfer mapRowToTransfer(SqlRowSet result){
        Transfer transfer = new Transfer();
        transfer.setTransferId(result.getInt("transfer_id"));
        transfer.setAccountFrom(result.getInt("account_from"));
        transfer.setAccountTo(result.getInt("account_to"));
        transfer.setAmount(result.getBigDecimal("amount"));
        transfer.setTransferTypeId(result.getInt("transfer_type_id"));
        transfer.setTransferStatus(result.getBoolean("transfer_status"));
        return transfer;
    }

}
