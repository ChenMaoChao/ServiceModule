package com.mao.factory;

import com.mao.service.IMemberService;
import com.mao.service.impl.MemberService;

public class ServiceFactory {
    private ServiceFactory() {}

    public static IMemberService getIMemberServiceInstance() {
        return new MemberService();
    }
}
