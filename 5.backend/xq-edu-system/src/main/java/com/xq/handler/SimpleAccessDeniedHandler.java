package com.xq.handler;

import com.google.gson.Gson;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问资源权限不足的处理器
 */
@Component(value = "simpleAccessDeniedHandler")
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        // 获取访问资源的url
        String requestURI = request.getRequestURI();
        response.setContentType("text/html;charset=utf-8");
        if(requestURI != null && requestURI.endsWith(".html")){ // 同步请求
            // 重定向到403.html
            response.sendRedirect("/403.html");
        }else{// 异步请求
            DataResults res = DataResults.success(ResultCode.NO_RIGTHS);
            // 向前端响应json数据
            response.getWriter().write(new Gson().toJson(res));
        }
    }
}
