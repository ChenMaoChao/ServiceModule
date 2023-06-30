package com.mao.junit;

import com.mao.factory.ServiceFactory;
import com.mao.service.IMemberService;
import com.mao.vo.Member;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class JunitTest {
    @Test
    public void testAdd() throws Exception{
        Member vo = new Member();
        vo.setMid("junit");
        vo.setName("junit");
        vo.setAge(22);
        vo.setSex("男");
        vo.setBirthday(new Date());
        vo.setEmail("junit@qq.com");
        vo.setNote("大好人");
        IMemberService memberService = ServiceFactory.getIMemberServiceInstance();
        TestCase.assertTrue(memberService.add(vo));
    }
    @Test
    public void testEdit() throws Exception{
        Member vo = new Member();
        vo.setMid("chenchao");
        vo.setName("陈超");
        vo.setAge(30);
        vo.setSex("男");
        vo.setBirthday(new Date());
        vo.setEmail("chenchao@qq.com");
        vo.setNote("非常非常好人");
        IMemberService memberService = ServiceFactory.getIMemberServiceInstance();
        TestCase.assertTrue(memberService.edit(vo));

    }
    @Test
    public void testRemove() throws Exception{
        IMemberService memberService =  ServiceFactory.getIMemberServiceInstance();
        memberService.removeById("junit");
    }

    @Test
    public void testGet() throws Exception{
        IMemberService memberService = ServiceFactory.getIMemberServiceInstance();
        Member member = memberService.get("chenchao");
        System.out.println(member);
        TestCase.assertNotNull(member);
    }

    @Test
    public void testList() throws Exception{
        IMemberService memberService = ServiceFactory.getIMemberServiceInstance();
        List<Member> list = memberService.list();
        System.out.println(list);
        TestCase.assertTrue(list.size() > 0);
    }
    @Test
    public void testSplit() throws Exception{
        IMemberService memberService = ServiceFactory.getIMemberServiceInstance();
        Map<String, Object> map = memberService.split(1, 1, "name", "陈超");
        System.out.println(map);
        TestCase.assertTrue(map.get("allMembers") != null && map.get("allRecorders") != null);
    }
}
