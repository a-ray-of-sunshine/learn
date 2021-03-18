package com.sunshine.learn.web.spring.service.impl;

import com.sunshine.learn.web.spring.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class TxServiceImpl implements TxService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final TransactionTemplate transactionTemplate;

    public TxServiceImpl(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    public void someServiceMethod() {
        transactionTemplate.execute(status -> {
            insertData();
            throw new RuntimeException();
        });
    }

    private void insertData() {
        String sql = "insert into test.person (name, age) values ('json', 24)";
        jdbcTemplate.execute(sql);
    }
}
