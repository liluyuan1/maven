package com.lly.service.impl;

import com.lly.dao.UsersDao;
import com.lly.pojo.Users;
import com.lly.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(rollbackFor=Exception.class)
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao um;

    @Override
    public int addUsers(Users users) {
        return um.addUsers(users);
    }

    @Override
    public Users selectUsers(Users users) {
        return um.selectUsers(users);
    }

    @Override
    public boolean selectByname(Users users){
        Users users1 = um.selectUsers(users);
        if(users1 != null){
            return true;
        }
        return false;
    }
}
