package com.jm;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/***
 * @author wukang
 */
@SpringBootApplication
@ComponentScan("com.jm")
@MapperScan("com.jm.mapper")
@EnableScheduling
@EnableAutoConfiguration
@EnableAsync // 开启异步
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
