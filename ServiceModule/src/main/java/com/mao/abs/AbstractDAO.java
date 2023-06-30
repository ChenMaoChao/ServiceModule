package com.mao.abs;

import com.mao.dbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class AbstractDAO {
    protected PreparedStatement pstmt;
    protected Connection conn;

    public AbstractDAO() {
        this.conn = DatabaseConnection.getConnection();
    }
}
