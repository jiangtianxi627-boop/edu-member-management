package com.xq.filter;

import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kriss
 * @version 1.0.0
 * @date 2025-05-25 13:51
 * @description TODO
 */
@Component(value = "systemCodeCheckFilter")
public class SystemCodeCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 校验前端发送的请求是否是/login的请求，并且请求方式是POST请求
        if(StringUtils.equals("/login",request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(),"post")){
            // 表示当前请求是一次登录请求，可以校验验证码
            String systemCode = (String) request.getSession().getAttribute("systemCode");
            String sysCode = request.getParameter("syscode");
            if(systemCode.equalsIgnoreCase(sysCode)){
                // 放行
                filterChain.doFilter(request,response);
            }else{
                // 验证码输入错误
                response.setContentType("application/json;charset=utf-8");
                // 响应浏览器信息
                DataResults dataResults = DataResults.success(ResultCode.CODE_FAIL);
                response.getWriter().write(new Gson().toJson(dataResults));
            }
        }else{
            // 放行
            filterChain.doFilter(request,response);
        }
    }
}
