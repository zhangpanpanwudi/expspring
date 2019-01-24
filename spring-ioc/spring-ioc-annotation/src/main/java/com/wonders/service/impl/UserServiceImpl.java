package com.wonders.service.impl;

import com.wonders.annotation.ExpService;
import com.wonders.service.UserService;

/**
 * @author: zph
 * @data: 2018/12/14 18:25
 */
@ExpService
public class UserServiceImpl implements UserService {
    public void add() {
        System.out.println("user.add()方法");
    }
}
