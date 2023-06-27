package com.mao.service.impl;

import com.mao.service.IMemberService;
import com.mao.vo.Member;

import java.util.List;

public class MemberService implements IMemberService {

    @Override
    public boolean add(Member vo) {
        return false;
    }

    @Override
    public boolean edit(Member vo) {
        return false;
    }

    @Override
    public boolean removeById(String... ids) {
        return false;
    }

    @Override
    public Member get(String id) {
        return null;
    }

    @Override
    public List<Member> list() {
        return null;
    }
}
