package com.wxuy.example.aop;

import com.wxuy.example.annotation.ControllerSqlLog;
import com.wxuy.example.utils.SqlUtils;
import com.wxuy.example.utils.SqlUtilsTest;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Order(100)
public class SqlLogAspectPrimary {

//    @Autowired
//    private LogService logService;

/*    @Autowired  //默认的数据源
    private SqlSessionFactory sqlSessionFactory;*/

    @Autowired
    @Qualifier("PrimarySqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;


    private Logger logger = LoggerFactory.getLogger(SqlLogAspectPrimary.class);

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private static final String START_TIME = "startTime";

    private static final String REQUEST_PARAMS = "requestParams";

    @Pointcut("execution(* com.wxuy.example.mapper.primary..*.*(..))")
    public void sqlLog() {}

    //前置通知
    //指定该方法是前置通知，并指定切入点
    @Before(value = "sqlLog()&& @annotation(controllerSqlLog)")
    public void before(JoinPoint joinPoint, ControllerSqlLog controllerSqlLog){
//        System.out.println("这是前置通知！！！！！");
/*        JoinPoint的用法
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());*/

        // 开始时间。
        long startTime = System.currentTimeMillis();
        Map<String, Object> threadInfo = new HashMap<>();
        threadInfo.put(START_TIME, startTime);

        threadLocal.set(threadInfo);
        logger.info("【sql】{}.{}", joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
    }

    //后置通知
    @AfterReturning(value = "sqlLog()&& @annotation(controllerSqlLog)", returning = "res")
    public void afterReturning(JoinPoint joinPoint,ControllerSqlLog controllerSqlLog ,Object res) throws Throwable{
//        System.out.println("这是后置通知！(如果出现异常，将不会调用)！！！！");
        Map<String, Object> threadInfo = threadLocal.get();
        long takeTime = System.currentTimeMillis() - (long) threadInfo.getOrDefault(START_TIME, System.currentTimeMillis());
        String sql = SqlUtils.getMybatisSql(joinPoint,sqlSessionFactory);
        threadLocal.remove();
        logger.info("sql:{}", sql);
        logger.info("sql执行耗时={}ms", takeTime);

    }

/*    //环绕通知
    @Around(value = "sqlLog()&& @annotation(controllerSqlLog)")
    public void around(ProceedingJoinPoint pjp,ControllerSqlLog controllerSqlLog) throws Throwable{
        //1.从redis中获取主数据库，若获取不到直接退出，否则判断当前数据源是会否为主，若不为主，则切换到主数据源
        //2.调用目标方法
        Object proceed = pjp.proceed();
        //3.获取SQL
        String sql = SqlUtils.getMybatisSql(pjp,sqlSessionFactory);
        logger.info("sql:{}", sql);
//        //4.插入日志
//        logService.insert(sql);
        //5.通知同步程序
    }*/

    //异常通知
    @AfterThrowing(value = "sqlLog()&&@annotation(controllerSqlLog)", throwing = "throwable")
    public void afterException(ControllerSqlLog controllerSqlLog, Throwable throwable){
//        System.out.println("出事了，抛异常了！！！！");
        logger.error("sql方法调用异常，异常信息{}", throwable);
    }

    //后置通知
    @After(value = "sqlLog()")
    public void after(){
//        System.out.println("这是后置通知！(无论是否出现异常都会调用)！！！！");
    }
}