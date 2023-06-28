package com.mao.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 定义一个专门负责mysql数据库连接管理的程序类，该类主要可以获取数据库的连接对象以及关闭处理
 */
public class DatabaseConnection implements AutoCloseable{

    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver" ;
    public static final String DBURL = "jdbc:mysql://localhost:3306/mao" ;
    public static final String USER = "root" ;
    public static final String PASSWORD = "mysqladmin" ;
    private Connection conn;//数据库连接对象

    /**
     * 每当通过此构造方法进行实例化的时候，就表示需要获取一个数据库的连接
     * 对于数据库的连接，都通过本类的实例封装，调用此方法连接数据库
     */
    public DatabaseConnection() {
        try {
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回一个JDBC的连接对象，如果连接失败返回的内容是null
     * @return Connection接口实例
     */
    public Connection getConnection() {
        return this.conn;
    }

    @Override
    public void close() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
        }
    }
}
