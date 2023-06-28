package com.mao.service.impl;

import com.mao.dao.IMemberDAO;
import com.mao.dbc.DatabaseConnection;
import com.mao.factory.DAOFactory;
import com.mao.service.IMemberService;
import com.mao.vo.Member;

import java.sql.SQLException;
import java.util.*;

public class MemberService implements IMemberService {
    private DatabaseConnection dbc = new DatabaseConnection(); //获得数据库连接以及关闭处理

    @Override
    public boolean add(Member vo) {
        boolean flag = false;
        try {
            if (vo.getAge() > 250 || vo.getAge() < 0) {
                return false;
            }
            if (!(vo.getSex().equalsIgnoreCase("男") || vo.getSex().equalsIgnoreCase("女"))) {
                return false;
            }
            this.dbc.getConnection().setAutoCommit(false);
            IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection());
            if (memberDAO.findById(vo.getMid()) == null) { //当前数据可以查找到
                if (memberDAO.findByEmail(vo.getEmail()) == null) { //当前数据找不到重复的email
                    flag = memberDAO.doCreate(vo); //数据保存
                }
            }
            this.dbc.getConnection().commit();
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                this.dbc.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }finally {
            try {
                this.dbc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean edit(Member vo) {
        boolean flag = false;
        try {
            if (vo.getAge() > 250 || vo.getAge() < 0) {
                return false;
            }
            if (!(vo.getSex().equalsIgnoreCase("男") || vo.getSex().equalsIgnoreCase("女"))) {
                return false;
            }
            this.dbc.getConnection().setAutoCommit(false);
            IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection());
            System.out.println("----------------");
            flag = memberDAO.doEdit(vo);
            this.dbc.getConnection().commit();
            return flag;
        } catch (Exception e) {
            try {
                this.dbc.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }finally {
            try {
                this.dbc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean removeById(String... ids) {
        boolean flag = false;
        try {
            if (ids.length == 0) {
                return false;
            }
            Set<String> set = new HashSet<>();
            set.addAll(Arrays.asList(ids));
            this.dbc.getConnection().setAutoCommit(false);
            IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection());
            flag = memberDAO.doRemove(set);
            this.dbc.getConnection().commit();
            return flag;
        } catch (Exception e) {
            try {
                this.dbc.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }finally {
            try {
                this.dbc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Member get(String id) {
        try {
            IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection());
            return memberDAO.findById(id);
        } catch (Exception e) {
            return null;
        }finally {
            try {
                this.dbc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Member> list() {
        try {
            IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection());
            return memberDAO.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }finally {
            try {
                this.dbc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Map<String, Object> split(Integer currentPage, Integer lineSize, String column, String keyWord) {
        Map<String, Object> map = new HashMap<>();
        try {
            IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection());
            if (column == null || "".equals(column) || keyWord == null || "".equals(keyWord)) {
                map.put("allMembers", memberDAO.findSplit(currentPage, lineSize));
                map.put("allRecorders", memberDAO.getAllCount());
            } else {
                map.put("allMembers", memberDAO.findSplit(currentPage, lineSize, column, keyWord));
                map.put("allRecorders", memberDAO.getAllCount(column, keyWord));
            }
            return map;
        } catch (Exception e) {
            return map;
        }finally {
            try {
                this.dbc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
