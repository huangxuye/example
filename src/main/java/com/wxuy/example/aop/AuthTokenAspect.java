package com.wxuy.example.aop;

import com.wxuy.example.annotation.AuthToken;
import com.wxuy.example.entity.Requester;
import com.wxuy.example.entity.User;
import com.wxuy.example.mapper.primary.AuthTokenMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthTokenAspect {

    private Logger logger = LoggerFactory.getLogger(AuthTokenAspect.class);

    @Autowired
    private AuthTokenMapper authTokenMapper;

    @Pointcut("@annotation(authToken)")
    public void doAuthToken(AuthToken authToken) {
    }
//    @Pointcut("execution(* com.wxuy.example.controller..*.*(..))")
//    public void doAuthToken() {
//    }

    @Around(value = "doAuthToken(authToken)")
    public Object deBefore(ProceedingJoinPoint pjp, AuthToken authToken) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object reqObject = pjp.getArgs()[0];
        User user = (User) reqObject;
        // 获取访问该方法所需的role_name信息
        String interview_method = authToken.interview_method();
        if ("admin".equals(interview_method)) {
            if(authTokenMapper.loginAuthentication(user)){
                return pjp.proceed();
            }
            return "权限校验失败，不具有指定的身份";
        } else if ("user".equals(interview_method)) {
            String id = String.valueOf(user.getId());
            if("0".equals(id)){
                return "请登陆后再试！";
            }
            if (id != null && !id.equals("")) {
                return pjp.proceed();
            }
            return "请登陆后再试！";
        } else {
            return pjp.proceed();
        }
    }
}