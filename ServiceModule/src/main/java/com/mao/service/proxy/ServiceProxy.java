package com.mao.service.proxy;

import com.mao.dbc.DatabaseConnection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.prefs.PreferencesFactory;

public class ServiceProxy implements InvocationHandler {
    private Object target; //真实对象

    public Object build(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
    }

    public boolean isTransaction(String methodName) { //判断是否需要开启事务
        return methodName.startsWith("add") || methodName.startsWith("edit") || methodName.startsWith("delete") ||
                methodName.startsWith("create") || methodName.startsWith("update") || methodName.startsWith("remove");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try {
            if (this.isTransaction(method.getName())) { //要操作的业务方法存在有事务支持
                DatabaseConnection.getConnection().setAutoCommit(false);
            }
            result = method.invoke(this.target, args);
            if (isTransaction(method.getName())) { //要操作的业务方法存在有事务支持
                DatabaseConnection.getConnection().commit();
            }
        } catch (Exception e) {
            if (this.isTransaction(method.getName())) { //要操作的业务方法存在有事务支持
                DatabaseConnection.getConnection().rollback();
            }
            throw e;
        }finally {
            DatabaseConnection.close();
        }
        return result;
    }
}
