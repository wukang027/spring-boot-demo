package com.jm.controller;
import com.jm.domain.User;
import com.jm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wukan on 2017-12-12-0012.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
//    @Autowired
//    private UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user){
        return userService.addUser(user);
    }
    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){

        return userService.findAllUser(pageNum,pageSize);
    }
//    @ResponseBody
//    @RequestMapping(value = "/addUserInfo" , produces = {"application/json;charset=UTF-8"})
//    public int addUserInfo(UserInfo userInfo){
//        logger.info("addUserInfo.param="+userInfo);
//        return userInfoService.addUser(userInfo);
//    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(Integer id ){
        Map<String,Object> map = new HashMap<>();
            map.put("user",userService.findOne(id));
        return new ModelAndView("user",map);
    }
}
