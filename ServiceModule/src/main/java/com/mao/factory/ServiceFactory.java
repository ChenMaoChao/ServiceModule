package com.mao.factory;

import com.mao.service.IMemberService;
import com.mao.service.impl.MemberServiceImpl;
import com.mao.service.proxy.ServiceProxy;

public class ServiceFactory {
    private ServiceFactory() {}

    public static IMemberService getIMemberServiceInstance() {
        return (IMemberService) new ServiceProxy().build(new MemberServiceImpl());
    }
}
