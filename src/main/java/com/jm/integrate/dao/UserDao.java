package com.jm.integrate.dao;

import com.jm.domain.User;

/**
 * Created by wukan on 2017-12-23-0023.
 */
public interface UserDao {
    void saveUser(User user);

    User findUserByUserName(String userName);

    void updateUser(User user);

    void deleteUserById(Integer id);
}
