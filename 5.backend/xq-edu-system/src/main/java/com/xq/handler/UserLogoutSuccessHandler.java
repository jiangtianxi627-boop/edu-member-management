package com.xq.handler;

import com.google.gson.Gson;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kriss
 * @version 1.0.0
 * @date 2025-05-25 16:17
 * @description TODO
 */
@Component(value = "userLogoutSuccessHandler")
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    // 注销成功需要触发的handler方法
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 设置向浏览器响应数据的格式
        response.setContentType("application/json;charset=utf-8");
        DataResults dataResults = DataResults.success(ResultCode.LOGOUT_SUCCESS);
        response.getWriter().write(new Gson().toJson(dataResults));
    }
}
