package com.lly.service;

import com.lly.pojo.Users;

public interface UsersService {

    public int addUsers(Users users);

    public Users selectUsers(Users users);

    public boolean selectByname(Users users);
}
