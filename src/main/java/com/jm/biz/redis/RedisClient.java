package com.jm.biz.redis;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;


/**
 * @author wukang
 */
@Component
public class RedisClient {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JedisPool jedisPool;
	
//	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * set 永久保存
	 * @param key
	 * @param value
	 */
	public String set(String key,Object value){
		Jedis jedis = null;
		String code = "";
		try{
			jedis = jedisPool.getResource();
//			String valueAsString = mapper.writeValueAsString(value);
			code = jedis.set(key, JSONObject.toJSONString(value));
		} catch (Exception e) {
			logger.error("RedisClient.set error:",e);
		}finally{
			jedis.close();
			return code;
		}
	}

	/**
	 * set 按时间保存
	 * @param key
	 * @param value
	 * @param time 过期时间(单位：秒)
	 */
	public String set(String key,Object value,Long time){
		Jedis jedis = null;
		String code = "";
		try{
			jedis = jedisPool.getResource();
//			String valueAsString = mapper.writeValueAsString(value);
			//NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key if it already exist.
			//EX|PX, expire time units: EX = seconds; PX = milliseconds
			code = jedis.set(key, JSONObject.toJSONString(value),"XX", "EX",time);
		} catch (Exception e) {
			logger.error("RedisClient.set error:",e);
		}finally{
			jedis.close();
			return code;
		}
	}

	/**
	 * Get the value of the specified key. If the key does not exist null is returned. If the value
	 * stored at key is not a string an error is returned because GET can only handle string values.
	 * @param key
	 * @return
	 */
	public String get(String key){
		Jedis jedis = null;
		String string = null;
		try {
			jedis = jedisPool.getResource();
			string = jedis.get(key);			
		} catch (Exception e) {
			logger.error("RedisClient.get error:",e);
		} finally{
			jedis.close();	
			return string;
		}				
	}
	
	public <T> T getObject(String key,Class<T> clazz){
		Jedis jedis = null;
		T value = null;
		try {
			jedis = jedisPool.getResource();
			String string = jedis.get(key);
//			value = mapper.readValue(string,clazz);
			value = JSONObject.parseObject(string,clazz);
		} catch (Exception e) {
			logger.error("RedisClient.getObject error:",e);
		} finally{
			jedis.close();	
			return value;
		}		
	}

	public <T> List<T> getArray(String key,Class<T> clazz){
		Jedis jedis = null;
		List<T> value = null;
		try {
			jedis = jedisPool.getResource();
			String string = jedis.get(key);
//			value = mapper.readValue(string,clazz);
			value = JSONObject.parseArray(string,clazz);
		} catch (Exception e) {
			logger.error("RedisClient.getObject error:",e);
		} finally{
			jedis.close();
			return value;
		}
	}

	/**
	 * Test if the specified key exists. The command returns "1" if the key exists, otherwise "0" is
	 * returned. Note that even keys set with an empty string as value will return "1". Time
	 * complexity: O(1)
	 * @param key
	 * @return Boolean reply, true if the key exists, otherwise false
	 */
	public Boolean hasKey(String key){
		Jedis jedis = null;
		boolean flag = false;
		try {
			jedis = jedisPool.getResource();
			flag = jedis.exists(key);
		} catch (Exception e) {
			logger.error("RedisClient.hasKey error:",e);
		} finally{
			jedis.close();
			return flag;
		}
	}

	/**
	 * Remove the specified keys. If a given key does not exist no operation is performed for this
	 * key. The command returns the number of keys removed. Time complexity: O(1)
	 * @param key
	 */
	public Long del(String key){
		Jedis jedis = null;
		Long result = 0L;
		try {
			jedis = jedisPool.getResource();
			result = jedis.del(key);
		} catch (Exception e) {
			logger.error("RedisClient.hasKey error:",e);
		} finally{
			jedis.close();
			return result;
		}
	}

	/**
	 * Set a timeout on the specified key. After the timeout the key will be automatically deleted by
	 * the server. A key with an associated timeout is said to be volatile in Redis terminology.
	 * @param key
	 * @param seconds
	 */
	public Long expire(String key,Integer seconds){
		Jedis jedis = null;
		Long result = 0L;
		try {
			jedis = jedisPool.getResource();
			result = jedis.expire(key,seconds);
		} catch (Exception e) {
			logger.error("RedisClient.expire error:",e);
		} finally{
			jedis.close();
			return result;
		}
	}

	/**
	 * If the key already exists and is a string, this command appends the provided value at the end
	 * of the string. If the key does not exist it is created and set as an empty string, so APPEND
	 * will be very similar to SET in this special case.
	 * @param key
	 * @param value
	 */
	public Long append(String key,Object value){
		Jedis jedis = null;
		Long result = 0L;
		try {
			jedis = jedisPool.getResource();
			result = jedis.append(key, JSONObject.toJSONString(value));
		} catch (Exception e) {
			logger.error("RedisClient.expire error:",e);
		} finally{
			jedis.close();
			return result;
		}
	}
}
