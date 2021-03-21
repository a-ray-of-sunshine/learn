package com.sunshine.learn.web.spring.service.impl;

import com.sunshine.learn.web.spring.domain.Person;
import com.sunshine.learn.web.spring.service.TxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TxServiceImpl implements TxService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final TransactionTemplate transactionTemplate;

    public TxServiceImpl(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        this.transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
    }

    @Override
    public void someServiceMethod() {
        transactionTemplate.execute(status -> {
            query();
            query();
            insertData(new Person("siri", 21));
            query();
            transactionTemplate.execute(status1 -> {
                query();
                return null;
            });
            query();
            return null;
        });
        query();
    }

    @Override
    @Transactional
    public void txMethod() {
        query();

        try {
            transactionTemplate.execute(status -> {
                query();
                insertData(new Person("happy1", 10));
                throw new RuntimeException();
//            return null;
            });
        }catch (Exception ex){
            log.info("出现异常事务回滚");
        }

        Person person = new Person("happy2", 12);
        insertData(person);
    }

    private void insertData(Person person) {
        String sql = "insert into test.person (name, age) values ('"+ person.getName() + "', " + person.getAge() + ")";
        jdbcTemplate.execute(sql);
    }

    private void query() {
        String sql = "select * from test.person where age < 24";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps.size());
    }
}
