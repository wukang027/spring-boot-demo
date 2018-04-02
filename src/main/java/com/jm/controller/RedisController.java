package com.jm.controller;


import com.jm.biz.redis.RedisClient;
import com.jm.biz.redis.RedisManager;
import com.jm.domain.Player;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/redis")
public class RedisController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RedisClient redisClient;
	@Autowired
	private RedisManager redisManager;
	
	@RequestMapping("/get")
	@ResponseBody
	public Object get()throws Exception{
		String string = redisClient.get("5");
		Player player = null;
		//player = new ObjectMapper().readValue(string,Player.class);
		player = redisClient.getObject("5",Player.class);
		logger.info("redis.get.player="+player);
		return string;
	}
	
	@ResponseBody
	@RequestMapping("/set")
	public String set(Long time){
		Player player = new Player();
		player.setName("莱昂纳德");
		player.setId(5);
		player.setPoints(27.1);
		if(time!=null){
			return redisClient.set(player.getId().toString(),player,time);
		}
		return redisClient.set(player.getId().toString(),player);
	}

	@ResponseBody
	@RequestMapping("/del")
	public Long del(String key){
		if(StringUtils.isNullOrEmpty(key)){
			return redisClient.del("5");
		}
		return redisClient.del(key);
	}

	@ResponseBody
	@RequestMapping("/expire")
	public Long expire(){
		return redisClient.expire("5",30);
	}

	@ResponseBody
	@RequestMapping("/append")
	public Long append(String key){
		Player player = new Player();
		player.setName("罗纳尔多");
		player.setId(6);
		player.setPoints(30.5);
		return redisClient.append("5", player);
	}

	@ResponseBody
	@RequestMapping("/setList")
	public String setList(){
		List list = new ArrayList();
		for(int i=0;i<3;i++){
			Player player = new Player();
			player.setName("罗纳尔多");
			player.setId(i);
			player.setPoints(30.5);
			list.add(player);
		}
		return redisClient.set("6", list);
	}

	@ResponseBody
	@RequestMapping("/getList")
	public Object getList(){
		logger.info("get String="+redisClient.get("6"));
		return redisClient.getArray("6",Player.class);
	}

	@ResponseBody
	@RequestMapping("/set2")
	public Object set2(){
		String key = UUID.randomUUID().toString().replaceAll("-","");
//		for(Long i=0L;i<10;i++){
//			redisManager.set(key,i,UUID.randomUUID().toString());
//		}
		try {
			redisManager.set(key,0L,UUID.randomUUID().toString());
		}catch (Exception e){
			logger.error("set2:",e);
		}
//		List<String> list = redisManager.getList(key);
		logger.info("set2.key="+key);
		return key;
	}

	
}





