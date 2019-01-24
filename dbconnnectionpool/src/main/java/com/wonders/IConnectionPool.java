package com.wonders;

import java.sql.Connection;

/**
 * @author: zph
 * @data: 2019/01/05 21:16
 */
public interface IConnectionPool {

    public Connection getConnection();

    public void realseConnection(Connection connection);

}
