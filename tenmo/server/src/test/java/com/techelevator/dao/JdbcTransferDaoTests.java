package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcTransferDaoTests extends BaseDaoTests{
    private Transfer testTransfer;
    private JdbcTransferDao sut;

    @Before
    public void setup(){
        sut = new JdbcTransferDao(dataSource);
        testTransfer = new Transfer(1, 1001, 1002, "Approved", new BigDecimal("200.00"));
    }

    @Test
    public void getTransferByTransferId_returns_correct_transfer(){
        Transfer transfer = sut.getTransferByTransferId(1);
        assertTransfersMatch(testTransfer, transfer);
    }

    private void assertTransfersMatch(Transfer expected, Transfer actual){
        Assert.assertEquals(expected.getTransferId(), actual.getTransferId());
        Assert.assertEquals(expected.getAccountFrom(), actual.getAccountFrom());
        Assert.assertEquals(expected.getAccountTo(), actual.getAccountTo());
        Assert.assertEquals(expected.getTransferStatus(), actual.getTransferStatus());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
    }
}
