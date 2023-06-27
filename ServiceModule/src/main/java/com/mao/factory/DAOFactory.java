package com.mao.factory;

import com.mao.dao.IMemberDAO;
import com.mao.dao.impl.MemberDAOImpl;

import java.sql.Connection;

public class DAOFactory {
    private IMemberDAO getIMemberDAOInstance(Connection conn) {
        return new MemberDAOImpl(conn);
    }
}
