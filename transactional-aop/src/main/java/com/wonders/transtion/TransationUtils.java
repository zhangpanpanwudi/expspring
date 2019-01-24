package com.wonders.transtion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @author: zph
 * @data: 2018/12/09 15:00
 */
@Component
public class TransationUtils {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //开启事务
    public TransactionStatus beggin(){
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transaction;
    }

    //提交事务
    public void commit(TransactionStatus transaction){
        dataSourceTransactionManager.commit(transaction);
    }

    //回滚事务
    public void rollBack(TransactionStatus transaction){
        dataSourceTransactionManager.rollback(transaction);
    }

}
