package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public BigDecimal getAccountBalance(int id) {
        BigDecimal balance = null;
        String sql = "SELECT balance FROM account WHERE account_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()){
            Account account = mapRowToAccount(result);
            balance = account.getBalance();
        }
        return balance;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            accounts.add(mapRowToAccount(result));
        }
        return accounts;
    }

    @Override
    public Account getAccountById(int id) {
        Account account = new Account();
        String sql = "SELECT * FROM account WHERE account_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()){
           account = mapRowToAccount(result);
        }
        return account;
    }

    @Override
    public BigDecimal addBalance(int id, BigDecimal amount) {
        String sql = "UPDATE account SET balance = (balance + ?) WHERE user_id = ?";
        jdbcTemplate.update(sql, amount, id);
        return getAccountBalance(id);
    }

    @Override
    public BigDecimal subtractBalance(int id, BigDecimal amount) {
        String sql = "UPDATE accounts SET balance = (balance - ?) WHERE user_id = ?";
        jdbcTemplate.update(sql, amount, id);
        return getAccountBalance(id);

    }

    @Override
    public Account getAccountByUserId(int id) {
        Account account = new Account();
        String sql = "SELECT * FROM account WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if(result.next()){
            account = mapRowToAccount(result);
        }
        return account;
    }

    @Override
    public boolean checkValidTransfer(int id, BigDecimal amount) {

        if(amount.compareTo(getAccountBalance(id)) < 0){
            return false;
        }
        return true;
    }
    
    private Account mapRowToAccount(SqlRowSet result){
        Account account = new Account();
        account.setAccountId(result.getInt("account_id"));
        account.setUserId(result.getInt("user_id"));
        account.setBalance(result.getBigDecimal("balance"));
        return account;
    }
}
