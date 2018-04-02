package com.jm.biz.aop;

import com.alibaba.fastjson.JSONObject;
import com.jm.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;


/**
 * sbm
 * Created by wukang
 * User：wukang
 * Date：2016/10/21
 * Time：15:53
 */
@Component
@Aspect
/*
 * 定义切面执行的优先级，数字越低，优先级越高
 * 在切入点之前执行：按order值有小到大的顺序执行
 * 在切入点之后执行：按order值由大到小的顺序执行
 */
@Order(-5)
public class AppLogAspect {
private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    // 保证每个线程都有一个单独的实例
    private ThreadLocal<Long> time = new ThreadLocal<>();
    @Pointcut("execution(public * com.jm.controller..*.*(..))")
    public void pointcut() {
    }
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint)throws Throwable {
        time.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求的内容
        logger.info("Request URL: " + request.getRequestURL().toString());
        logger.info("Request Method: " + request.getMethod());
        logger.info("User-Agent: " + request.getHeader("User-Agent"));
        logger.info("Class Method: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("Cookies: " + JSONObject.toJSONString(request.getCookies()));
        logger.info("IP : " + IpUtil.getRemoteHost(request));
        logger.info("Params: " + Arrays.toString(joinPoint.getArgs()));
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            logger.info("key:{},value:{}", key, request.getParameter(key));
        }
    }
    @AfterReturning(pointcut = "pointcut()",returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("RESPONSE : " + JSONObject.toJSONString(ret));
        logger.info("耗时 : " + ((System.currentTimeMillis() - time.get())) + "ms");
        logger.info("AppLogAspect.doAfterReturning()");
    }
}
