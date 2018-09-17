package com.xinyan.message.center.web.interceptor;

import com.xinyan.message.center.common.utils.LogUtil;
import com.xinyan.message.center.common.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Module Desc:环绕controller设置logId，打印方法运行时间
 * User: gambo
 * DateTime: 2018/8/14
 */
@Aspect
@Component
@Slf4j
public class SysLogAop {


    @Pointcut("execution(* com.xinyan.message.center.web.controller.*.*(..)) || execution(* com.xinyan.message.center.manager.consumer.*.doBiz(..))")
    private void pointcut() {
    }

    /**
     * 环绕通知
     */
    @Around("pointcut()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) point.getSignature();
        String logId = null;
        if (point.getArgs().length>0) {
            logId = ObjectUtils.getFieldValue(point.getArgs()[0],point.getArgs()[0].getClass(),"getLogId",String.class);
        }
        LogUtil.updateLogId(logId);
        logId = LogUtil.getLogId();
        if (point.getArgs().length>0) {
            ObjectUtils.setFieldValue(point.getArgs()[0],point.getArgs()[0].getClass(),"setLogId",logId);
        }
        //请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("执行方法:{}.{} 开始执行 ",className,methodName);
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        log.info("执行方法:{}.{} 执行完成耗时:{}ms",className,methodName,time);
        return result;
    }
}

