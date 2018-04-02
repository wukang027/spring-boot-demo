package com.jm.integrate.dao.impl;

import com.jm.domain.User;
import com.jm.integrate.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * Created by wukang on 2017-12-23-0023.
 * @author wukang
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        User user =  mongoTemplate.findOne(query , User.class);
        return user;
    }

    @Override
    public void updateUser(User user) {
        Query query=new Query(Criteria.where("userId").is(user.getUserId()));
        Update update= new Update().set("userName", user.getUserName()).set("password", user.getPassword());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,User.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,User.class);
    }

    @Override
    public void deleteUserById(Integer id) {
        Query query=new Query(Criteria.where("userId").is(id));
        mongoTemplate.remove(query,User.class);
    }
}
