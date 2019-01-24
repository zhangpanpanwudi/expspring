package com.wonders;

import com.wonders.entity.User;
import com.wonders.spring.xml.ExpApplicationContext;

/**
 * @author: zph
 * @data: 2018/12/14 17:23
 */
public class Test01 {

    public static void main(String[] args) throws Exception {
        ExpApplicationContext expApplicationContext = new ExpApplicationContext("spring.xml");
        User user = (User) expApplicationContext.getBean("user");
        System.out.println(user);
    }
}
