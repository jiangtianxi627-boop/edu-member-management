package com.xq.advice;

import com.google.gson.Gson;
import com.xq.annotation.EduSystemLogger;
import com.xq.pojo.Adminuser;
import com.xq.pojo.Systemlog;
import com.xq.service.ISystemlogService;
import com.xq.utils.DateTimeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * 切面类，专门完成用户操作资源的日志记录的获取
 */
@Aspect
@Component
public class LoggerAdvice {

    @Autowired(required = false)
    HttpSession session;

    @Autowired
    ISystemlogService systemlogService;

    @Around("@annotation(eduSystemLogger)")
    public Object around(ProceedingJoinPoint joinPoint, EduSystemLogger eduSystemLogger){
        // 获取当前认证的用户信息
        Adminuser adminuser = (Adminuser) session.getAttribute("loginUser");
        Object result = null;
        // 创建一个日志对象
        Systemlog systemlog = new Systemlog();
        try {
            systemlog.setLogtype(0);// 0代表普通的日志
            systemlog.setNowuser(adminuser.getAdminName());// 设置当前登录的用户
            systemlog.setCreatetime(DateTimeUtils.nowTime());
            systemlog.setMethodname(joinPoint.getSignature().getName());
            // 获取方法参数信息
            Object[] args = joinPoint.getArgs();
            systemlog.setMethodparms(this.parseParams(args));
            String logDescription = eduSystemLogger.logDescription();
            systemlog.setLogdescription(logDescription);
            String methodReturnType = eduSystemLogger.methodReturnType();
            systemlog.setMethodtype(methodReturnType);

            // 调用目标方法
            result = joinPoint.proceed();
            systemlog.setMethodteturn(new Gson().toJson(result));
        } catch (Throwable e) {
            e.printStackTrace();
            // 异常日志信息
            systemlog.setLogtype(1);
            // 异常信息的设置
            systemlog.setExmessage(e.getMessage());
        }finally {
            systemlogService.save(systemlog);
        }
        return result;
    }

    // 解析参数
    public String parseParams(Object[] params){
        if(params == null || params.length == 0){
            return "";
        }
        StringBuffer param = new StringBuffer("传入参数 # 个:[");
        int i = 0;
        for(Object obj : params){
            i++;
            if(i == 1){
                param.append(obj.toString());
                continue;
            }
            param.append(" ,").append(obj.toString());
        }
        return param.append("]").toString().replace("#",String.valueOf(i));
    }
}
