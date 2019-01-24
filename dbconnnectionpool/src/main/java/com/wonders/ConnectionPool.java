package com.wonders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zph
 * @data: 2019/01/05 22:00
 */
public class ConnectionPool implements IConnectionPool {

    private DbBean dbBean;

    //空闲队列
    private BlockingQueue<Connection> freeQueue;
    //正在使用的队列
    private BlockingQueue<Connection> activeQueue;
    //数据库连接总数
    private BlockingQueue<Connection> allQueue;

    private AtomicInteger countConne;

    public ConnectionPool(DbBean dbBean) {
        this.dbBean = dbBean;
        freeQueue = new ArrayBlockingQueue<Connection>(dbBean.getMaxConnections());
        activeQueue = new LinkedBlockingDeque<Connection>();
        allQueue = new ArrayBlockingQueue<Connection>(dbBean.getMaxActiveConnections());
        init();
    }

    public void  init(){
        for(int i=0;i<dbBean.getInitConnections();i++){
            Connection connection = createConnection();
            if(connection != null){
                freeQueue.add(connection);
            }
        }
    }

    @Override
    public Connection getConnection() {
        Connection connection=null;
        if(!freeQueue.isEmpty()){
            connection = freeQueue.poll();
        }else{
            connection = createConnection();
        }
        boolean available = isAvailable(connection);
        if(available){
            activeQueue.add(connection);
        }else{
            connection=getConnection();
        }
        return connection;
    }

    @Override
    public void realseConnection(Connection connection) {
        if(isAvailable(connection)){
            if(freeQueue.size()<dbBean.getMaxConnections()){
                //回收
                freeQueue.add(connection);
            }else{
                try {
                    allQueue.remove(connection);
                    //释放
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            activeQueue.remove(connection);
        }
    }

    // 判断连接是否可用
    public boolean isAvailable(Connection connection) {
        try {
            if (connection == null || connection.isClosed()) {
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return true;

    }

    private Connection createConnection(){
        try {
            Class.forName(dbBean.getDriverName());
            Connection connection = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUserName(),
                    dbBean.getPassword());
            allQueue.add(connection);
            return connection;
        } catch (Exception e) {
            return null;
        }
    }
}
