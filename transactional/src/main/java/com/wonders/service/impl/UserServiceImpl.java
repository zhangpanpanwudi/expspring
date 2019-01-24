package com.wonders.service.impl;

import com.wonders.annotation.ExtAnnotational;
import com.wonders.dao.UserDao;
import com.wonders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zph
 * @data: 2018/12/09 17:41
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @ExtAnnotational
    public void add() {
        userDao.add("test001", 20);
//            int i = 1 / 0;
        System.out.println("################");
        userDao.add("test002", 21);
    }
}
