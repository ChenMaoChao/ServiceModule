package com.mao.factory;

import com.mao.dao.IMemberDAO;
import com.mao.dao.impl.MemberDAOImpl;

import java.sql.Connection;

public class DAOFactory {
    public static IMemberDAO getIMemberDAOInstance(Connection conn) {
        return new MemberDAOImpl(conn);
    }
}
