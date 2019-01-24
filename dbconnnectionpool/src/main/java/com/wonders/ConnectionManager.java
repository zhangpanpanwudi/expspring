package com.wonders;

import java.sql.Connection;

/**
 * @author: zph
 * @data: 2019/01/05 22:50
 */
public class ConnectionManager {
    private static DbBean dbBean = new DbBean();
    private static ConnectionPool connectionPool = new ConnectionPool(dbBean);

    public static Connection getConnection(){return connectionPool.getConnection();}

    public static void realseConnect(Connection connection){
        connectionPool.realseConnection(connection);
    }
}
