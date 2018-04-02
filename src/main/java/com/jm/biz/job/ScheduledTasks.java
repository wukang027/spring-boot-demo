package com.jm.biz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author wukang
 * @date 2018-03-10-0010 21:57:16
 */
@Component
public class ScheduledTasks {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Scheduled(cron = "0 0/1 * * * ?")
	public void reportCurrentTime() {
		logger.info("现在时间：" + dateFormat.format(new Date()));
	}
}
