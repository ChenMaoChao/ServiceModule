package com.mao.test;

import com.mao.factory.ServiceFactory;
import com.mao.service.IMemberService;
import com.mao.vo.Member;

import java.util.Date;

public class TestAdd {
    public static void main(String[] args) throws Exception{
        Member vo = new Member();
        vo.setMid("A");
        vo.setName("A");
        vo.setAge(14);
        vo.setSex("男");
        vo.setBirthday(new Date());
        vo.setEmail("A27412817@qq.com");
        vo.setNote("不错的人-a");
        IMemberService memberService = ServiceFactory.getIMemberServiceInstance();
        System.out.println(memberService.add(vo));
    }
}
