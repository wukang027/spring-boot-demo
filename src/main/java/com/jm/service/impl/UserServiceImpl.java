package com.jm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jm.mapper.UserMapper;
import com.jm.domain.User;
import com.jm.service.UserService;
import com.jm.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wukan on 2017-12-12-0012.
 * @author wukang
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public int addUser(User user) {

        return userMapper.insertSelective(user);
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        List<User> list = null;
        String key = RedisKeyUtil.getUserKey("pageNum:"+pageNum+":pageSize:"+pageSize);
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)){
            logger.info("findAllUser=" + operations.get(key));
            list = JSONObject.parseArray(operations.get(key),User.class);
            return list;
        }
        //将参数传给这个方法就可以实现物理分页了，非常简单。
//        pageSize = (pageNum-1)*pageSize;
        PageHelper.startPage(pageNum, pageSize,false);
        list = userMapper.selectAllUser();
        operations.set(key, JSONObject.toJSONString(list), 5, TimeUnit.MINUTES);
        logger.info("findAllUser.list:=" + list);
        return list;
    }

    @Override
    public User findOne(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
