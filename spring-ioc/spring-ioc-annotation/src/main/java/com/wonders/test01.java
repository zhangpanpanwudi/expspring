package com.wonders;

import com.wonders.service.UserService;
import com.wonders.spring.AnnotationApplicationContext;

/**
 * @author: zph
 * @data: 2018/12/14 18:09
 */
public class test01 {

    public static void main(String[] args) throws Exception {
        AnnotationApplicationContext app = new AnnotationApplicationContext("com.wonders");
        UserService userService = (UserService) app.getBean("userServiceImpl");
        userService.add();
    }

}
