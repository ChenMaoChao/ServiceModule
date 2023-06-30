package com.mao.abs;

import com.mao.dbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public abstract class AbstractDAO {
    protected PreparedStatement pstmt;
    protected Connection conn;

    public AbstractDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    public boolean handleRemoveByLong(String tableName, String columnName, Set<Long> ids) throws SQLException {
        StringBuffer sql = new StringBuffer("DELETE FROM ").append(tableName).append(" WHERE ") ;
        sql.append(columnName).append(" IN (") ;
        for (Long id : ids) {
            sql.append("?,") ;
        }
        sql.delete(sql.length() - 1,sql.length()).append(")") ;
        this.pstmt = this.conn.prepareStatement(sql.toString()) ;
        int foot = 1 ;
        for (Long id : ids) {
            this.pstmt.setLong(foot++,id);
        }
        return this.pstmt.executeUpdate() == ids.size() ;
    }

    public boolean handleRemoveByString(String tableName, String columnName, Set<String> ids) throws SQLException {
        StringBuffer sql = new StringBuffer("DELETE FROM ").append(tableName).append(" WHERE ") ;
        sql.append(columnName).append(" IN (") ;
        for (String id : ids) {
            sql.append("?,") ;
        }
        sql.delete(sql.length() - 1,sql.length()).append(")") ;
        this.pstmt = this.conn.prepareStatement(sql.toString()) ;
        int foot = 1 ;
        for (String id : ids) {
            this.pstmt.setString(foot++,id);
        }
        return this.pstmt.executeUpdate() == ids.size() ;
    }



    protected Long handleGetAllCount(String table) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + table;
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }
    protected Long handleGetAllCount(String table,String column, String keyWord) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + table + " WHERE " + column + " LIKE ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, "%" + keyWord + "%");
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }
}
