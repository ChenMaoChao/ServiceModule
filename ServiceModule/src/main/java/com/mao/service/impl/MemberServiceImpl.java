package com.mao.service.impl;

import com.mao.dao.IMemberDAO;
import com.mao.dbc.DatabaseConnection;
import com.mao.factory.DAOFactory;
import com.mao.service.IMemberService;
import com.mao.vo.Member;

import java.sql.SQLException;
import java.util.*;

public class MemberServiceImpl implements IMemberService {

    @Override
    public boolean add(Member vo) throws Exception{
        if (vo.getAge() > 250 || vo.getAge() < 0) {
            return false;
        }
        if (!(vo.getSex().equalsIgnoreCase("男") || vo.getSex().equalsIgnoreCase("女"))) {
            return false;
        }
        IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance();
        if (memberDAO.findById(vo.getMid()) == null) { //当前数据可以查找到
            if (memberDAO.findByEmail(vo.getEmail()) == null) { //当前数据找不到重复的email
                return memberDAO.doCreate(vo);
            }
        }
        return false;
    }

    @Override
    public boolean edit(Member vo) throws Exception{
        if (vo.getAge() > 250 || vo.getAge() < 0) {
            return false;
        }
        if (!(vo.getSex().equalsIgnoreCase("男") || vo.getSex().equalsIgnoreCase("女"))) {
            return false;
        }
        IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance();
        return memberDAO.doEdit(vo);
    }

    @Override
    public boolean removeById(String... ids) throws Exception{
        if (ids.length == 0) {
            return false;
        }
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(ids));
        IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance();
        return memberDAO.doRemove(set);
    }

    @Override
    public Member get(String id) throws Exception{
        IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance();
        return memberDAO.findById(id);
    }

    @Override
    public List<Member> list() throws Exception {
        IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance();
        return memberDAO.findAll();
    }

    @Override
    public Map<String, Object> split(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        IMemberDAO memberDAO = DAOFactory.getIMemberDAOInstance();
        if (column == null || "".equals(column) || keyWord == null || "".equals(keyWord)) {
            map.put("allMembers", memberDAO.findSplit(currentPage, lineSize));
            map.put("allRecorders", memberDAO.getAllCount());
        } else {
            map.put("allMembers", memberDAO.findSplit(currentPage, lineSize, column, keyWord));
            map.put("allRecorders", memberDAO.getAllCount(column, keyWord));
        }
        return map;
    }
}
