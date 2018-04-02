//package com.jm.biz.exception;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * 拦截异常
// *
// * @author wukang
// *
// */
//@ControllerAdvice
//public class GlobalExceptionHandler {
//	@ExceptionHandler(RuntimeException.class)
//	@ResponseBody // 拦截返回是json的返回结果
//	public Map<String, Object> exceptionHandler() {
//		Map<String, Object> result = new HashMap<>();
//		result.put("code", 500);
//		result.put("msg", "亲，系统错误，请稍后重试。。。");
//		return result;
//	}
//}
