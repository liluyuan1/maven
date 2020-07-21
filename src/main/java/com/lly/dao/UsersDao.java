package com.lly.dao;

import com.lly.pojo.Users;

public interface UsersDao {

    public Users selectUsers(Users users);

    public int addUsers(Users users);

}