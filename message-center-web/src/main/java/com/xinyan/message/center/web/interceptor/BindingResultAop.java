package com.xinyan.message.center.web.interceptor;

import com.xinyan.message.center.web.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Module Desc:
 * User: gambo
 * DateTime: 2018/8/14
 */
@Aspect
@Component
@Slf4j
public class BindingResultAop {


    //比配所有控制器方法,并且返回值是AjaxResult的方法
    @Pointcut("execution(@org.springframework.web.bind.annotation.RequestMapping  * com.xinyan.message.center.web.controller.*.*(..))")
    private void pointcut() {
    }

    /**
     * 环绕通知
     */
    @Around("pointcut()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        BindingResult br = null;
        for (Object o : pjp.getArgs()) {
            if (o instanceof BindingResult) {
                ; br = (BindingResult) o;
            }
        }

        if (br != null) {
            List<ObjectError> allErrors = br.getAllErrors();
            if (allErrors.size() > 0) {
                StringBuilder sf = new StringBuilder();
                StringBuilder sb = new StringBuilder();
                for (ObjectError error : allErrors) {
                    sf.append(error.getCode());
                    sf.append(";");
                    sb.append(error.getDefaultMessage());
                    sb.append(";");
                }
                Result result=new Result(sf.toString(),sb.toString());
                return result;
            }
        }
        return pjp.proceed();

    }
}

