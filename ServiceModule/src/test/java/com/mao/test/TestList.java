package com.mao.test;

import com.mao.factory.ServiceFactory;
import com.mao.service.IMemberService;

import java.util.Map;

public class TestList {
    public static void main(String[] args) throws Exception{
        IMemberService memberService = ServiceFactory.getIMemberServiceInstance();
        Map<String, Object> map = memberService.split(1, 1, "name", "A");
        System.out.println(map);
    }
}
