package com.mao.dao.impl;

import com.mao.abs.AbstractDAO;
import com.mao.dao.IMemberDAO;
import com.mao.vo.Member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MemberDAOImpl extends AbstractDAO implements IMemberDAO {
    public MemberDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Member vo) throws SQLException {
        String sql = "INSERT INTO member(mid,name,age,sex,email,birthday,note) VALUES(?,?,?,?,?,?,?) ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, vo.getMid());
        super.pstmt.setString(2, vo.getName());
        super.pstmt.setInt(3, vo.getAge());
        super.pstmt.setString(4, vo.getSex());
        super.pstmt.setString(5, vo.getEmail());
        super.pstmt.setDate(6, new java.sql.Date(vo.getBirthday().getTime()));
        super.pstmt.setString(7, vo.getNote());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doEdit(Member vo) throws SQLException {
        String sql = "UPDATE member SET name=?,age=?,sex=?,email=?,birthday=?,note=? WHERE mid=? ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, vo.getName());
        super.pstmt.setInt(2, vo.getAge());
        super.pstmt.setString(3, vo.getSex());
        super.pstmt.setString(4, vo.getEmail());
        super.pstmt.setDate(5, new java.sql.Date(vo.getBirthday().getTime()));
        super.pstmt.setString(6, vo.getNote());
        super.pstmt.setString(7, vo.getMid());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doRemove(Set<String> ids) throws SQLException {
        StringBuffer sql = new StringBuffer(30);
        sql.append("DELETE FROM member WHERE mid IN(");
        ids.forEach((id) -> {
            sql.append("?," );
        });
        sql.delete(sql.length() - 1, sql.length()).append(")");
        super.pstmt = super.conn.prepareStatement(sql.toString());
        Iterator<String> iter = ids.iterator();
        int foot = 1;
        while (iter.hasNext()) {
            super.pstmt.setString(foot++,iter.next());
        }
        return super.pstmt.executeUpdate() == ids.size();
    }

    @Override
    public Member findById(String id) throws SQLException {
        Member vo = null;
        String sql = "SELECT mid,name,age,sex,email,birthday,note FROM member WHERE mid=? ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, id);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getString(4));
            vo.setEmail(rs.getString(5));
            vo.setBirthday(rs.getDate(6));
            vo.setNote(rs.getString(7));
        }
        return vo;
    }

    @Override
    public Member findByEmail(String email) throws SQLException {
        Member vo = null;
        String sql = "SELECT mid,name,age,sex,birthday,email,note FROM member WHERE email=? ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, email);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getString(4));
            vo.setBirthday(rs.getDate(5));
            vo.setEmail(rs.getString(6));
            vo.setNote(rs.getString(7));
        }
        return vo;
    }

    @Override
    public List<Member> findAll() throws SQLException {
        List<Member> all = new ArrayList<>();
        String sql = "SELECT mid,name,age,sex,birthday,email,note FROM member ";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getString(4));
            vo.setBirthday(rs.getDate(5));
            vo.setEmail(rs.getString(6));
            vo.setNote(rs.getString(7));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<Member> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        List<Member> all = new ArrayList<>();
        String sql = "SELECT mid,name,age,sex,birthday,email,note FROM member LIMIT " + (currentPage - 1) * lineSize + " , " + lineSize;
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getString(4));
            vo.setBirthday(rs.getDate(5));
            vo.setEmail(rs.getString(6));
            vo.setNote(rs.getString(7));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<Member> findSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
        List<Member> all = new ArrayList<>();
        String sql = "SELECT mid,name,age,sex,birthday,email,note FROM member WHERE " + column + " LIKE ? LIMIT " + (currentPage - 1) * lineSize + ", " + lineSize;
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getString(4));
            vo.setBirthday(rs.getDate(5));
            vo.setEmail(rs.getString(6));
            vo.setNote(rs.getString(7));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Long getAllCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM member";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }

    @Override
    public Long getAllCount(String column, String keyWord) throws SQLException {
        String sql = "SELECT COUNT(*) FROM member WHERE " + column + " LIKE ? ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }
}
