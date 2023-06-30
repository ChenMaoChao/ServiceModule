package com.mao.dao;

import com.mao.vo.Member;

import java.sql.SQLException;

public interface IMemberDAO extends IBaseDAO<String,Member>{
    /**
     * 根据数据表中注册的email地址查询对应的用户信息
     * @param email 要查询的email地址
     * @return 如果数据存在，则数据以vo的形式返回，如果数据不存在返回null
     * @throws SQLException 数据库执行异常
     */
    public Member findByEmail(String email) throws SQLException;
}
