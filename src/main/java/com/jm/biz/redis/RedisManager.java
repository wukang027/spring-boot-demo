package com.jm.biz.redis;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author wukang
 */
@Component
public class RedisManager {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * set 永久保存 json形式
	 * @param key
	 * @param value
	 */
	public void set(String key,Object value){
		redisTemplate.opsForValue().set(key,JSONObject.toJSONString(value));
	}

	public void set(String key,Long index,Object value){
		redisTemplate.opsForList().set(key,index,value.toString());
	}

	public List<String> getList(String key){
		return redisTemplate.opsForList().range(key,0,-1);
	}

	/**
	 * set 按时间保存
	 * @param key
	 * @param value
	 * @param time 过期时间(单位：秒)
	 */
	public void set(String key,Object value,Long time){
		redisTemplate.opsForValue().set(key,JSONObject.toJSONString(value),time, TimeUnit.SECONDS);
	}

	/**
	 * Get the value of the specified key. If the key does not exist null is returned. If the value
	 * stored at key is not a string an error is returned because GET can only handle string values.
	 * @param key
	 * @return
	 */
	public String get(String key){
		return redisTemplate.opsForValue().get(key);
	}
	
	public <T> T getObject(String key,Class<T> clazz){
		T value = null;
		try {
			String string = redisTemplate.opsForValue().get(key);
			value = JSONObject.parseObject(string,clazz);
		} catch (Exception e) {
			logger.error("RedisClient.getObject error:",e);
		} finally{
			return value;
		}		
	}

	public <T> List<T> getArray(String key,Class<T> clazz){
		List<T> value = null;
		try {
			String string = redisTemplate.opsForValue().get(key);
			value = JSONObject.parseArray(string,clazz);
		} catch (Exception e) {
			logger.error("RedisClient.getObject error:",e);
		} finally{
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
		return redisTemplate.hasKey(key);
	}

	/**
	 * Remove the specified keys. If a given key does not exist no operation is performed for this
	 * key. The command returns the number of keys removed. Time complexity: O(1)
	 * @param key
	 */
	public void del(String key){
		redisTemplate.delete(key);
	}

	/**
	 * Set a timeout on the specified key. After the timeout the key will be automatically deleted by
	 * the server. A key with an associated timeout is said to be volatile in Redis terminology.
	 * @param key
	 * @param seconds
	 */
	public void expire(String key,Long seconds){
		redisTemplate.expire(key,seconds,TimeUnit.SECONDS);
	}

	/**
	 * If the key already exists and is a string, this command appends the provided value at the end
	 * of the string. If the key does not exist it is created and set as an empty string, so APPEND
	 * will be very similar to SET in this special case.
	 * @param key
	 * @param value
	 */
	public void append(String key,Object value){
		redisTemplate.opsForValue().append(key,JSONObject.toJSONString(value));

	}
}
