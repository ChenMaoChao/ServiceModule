package com.mao.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 定义一个专门负责mysql数据库连接管理的程序类，该类主要可以获取数据库的连接对象以及关闭处理
 */
public class DatabaseConnection {

    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/mao?useUnicode=true&characterEncoding=UTF8";
    public static final String USER = "root";
    public static final String PASSWORD = "mysqladmin";
    public static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 该类的所有操作不需要产生实例化对象，所有数据的存储都保存在ThreadLocal中
     * 这样可以保存每一个线程只拥有属于自己的Connection接口实例
     */
    private DatabaseConnection() {}

    /**
     * 每当通过此构造方法进行实例化的时候，就表示需要获取一个数据库的连接
     * 对于数据库的连接，都通过本类的实例封装，调用此方法连接数据库
     */
    private static Connection rebuildConnection() {
        Connection conn = null;
        try {
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 返回一个JDBC的连接对象，如果连接失败返回的内容是null
     *
     * @return Connection接口实例
     */
    public static Connection getConnection() {
        Connection conn = THREAD_LOCAL.get();
        if (conn == null) {
            conn = rebuildConnection();
            THREAD_LOCAL.set(conn);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     */
    public static void close() {
        Connection conn = THREAD_LOCAL.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            THREAD_LOCAL.remove();
        }
    }
}
