package com.mao.test;

import java.util.Set;

public class TestSplit {
    public static void main(String[] args) {
        Set ids = Set.of("1", "2", "3", "4", "5");
        StringBuffer sql = new StringBuffer(30);
        sql.append("DELETE FORM member WHERE mid IN ( ") ;
        ids.forEach((id) -> {
            sql.append("?,");
        });
        sql.delete(sql.length() - 1, sql.length()).append(")");
        System.out.println(sql);

    }
}
