package com.jm.service;

import com.jm.domain.User;

import java.util.List;

/**
 * Created by wukan on 2017-12-12-0012.
 */
public interface UserService {
    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);

    User findOne(Integer userId);
}
