package com.jm.test;

import com.jm.domain.User;
import com.jm.integrate.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wukan on 2017-12-23-0023.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveUser() throws Exception {
        User user=new User();
        user.setUserId(21);
        user.setUserName("小明");
        user.setPassword("fffooo123");
        userDao.saveUser(user);
    }

    @Test
    public void findUserByUserName(){
        User user= userDao.findUserByUserName("小明");
        System.out.println("user is :"+user);
    }

    @Test
    public void updateUser(){
        User user=new User();
        user.setUserId(21);
        user.setUserName("天空");
        user.setPassword("fffxxxx");
        userDao.updateUser(user);
    }

    @Test
    public void deleteUserById(){
        userDao.deleteUserById(21);
    }

}
