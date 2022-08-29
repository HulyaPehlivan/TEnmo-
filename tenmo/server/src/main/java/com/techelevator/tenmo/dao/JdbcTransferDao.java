package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{
    private JdbcTemplate jdbcTemplate;
    private AccountDao accountDao;

    public JdbcTransferDao(DataSource dataSource){this.jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public Transfer getTransferByTransferId(int id) {
        Transfer transfer = new Transfer();
        String sql = "SELECT transfer_id, account_from, account_to, transfer_status, amount FROM transfer WHERE transfer_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
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
    public List<Transfer> getTransfersByUserID(int id) {
        List<Transfer> getAllTransfersById = new ArrayList<>();
        String sql ="SELECT transfer_id, account_from, account_to, amount, transfer_status FROM transfer WHERE account_from = ? OR account_to = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id, id);
        while (result.next()){
            getAllTransfersById.add(mapRowToTransfer(result));
        }
        return getAllTransfersById;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {

            String sql = "INSERT INTO transfer ( account_from, account_to,  amount, transfer_status) VALUES ( ?, ?, ?, 'Approved') RETURNING transfer_id";

                Integer newTransferId;
                newTransferId = jdbcTemplate.queryForObject(sql, Integer.class,  transfer.getAccountFrom(), transfer.getAccountTo(),  transfer.getAmount());
                transfer.setTransferId(newTransferId);
                transfer.setTransferStatus("Approved");
        return transfer;
    }







    @Override
    public void updateTransferStatus(Transfer transfer) {
        String sql = "UPDATE transfer SET transfer_status = 'Approved' WHERE transfer_id = ?";
        String sql2 = "UPDATE transfer SET transfer_status = 'Denied' WHERE transfer_id = ?";
        if(transfer.getAmount().compareTo(accountDao.getAccountBalance(transfer.getAccountFrom())) <= 0){
            jdbcTemplate.update(sql, transfer.getTransferId());
        } else {
            jdbcTemplate.update(sql2, transfer.getTransferId());
        }

    }




    public Transfer mapRowToTransfer(SqlRowSet result){
        Transfer transfer = new Transfer();
        transfer.setTransferId(result.getInt("transfer_id"));
        transfer.setAccountFrom(result.getInt("account_from"));
        transfer.setAccountTo(result.getInt("account_to"));
        transfer.setAmount(result.getBigDecimal("amount"));
        transfer.setTransferStatus(result.getString("transfer_status"));
        return transfer;
    }

}
