package com.xq.handler;

import com.google.gson.Gson;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异步认证失败的处理器
 */
@Component(value = "authenticationFailureHandler")
@Slf4j
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 设置后端向前端响应的数据的json格式
        response.setContentType("application/json;charset=utf-8");
        log.info("登录失败的信息:" + exception.getMessage() + ",登录异常的类型是:" + exception);
        // 向前端响应数据
        DataResults<String> results = DataResults.success(ResultCode.LOGIN_FAIL);
        response.getWriter().write(new Gson().toJson(results));
    }
}
