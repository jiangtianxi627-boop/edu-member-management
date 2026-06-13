package com.xq.controller;

import com.xq.utils.CheckCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;

@Controller
@Slf4j
public class SystemCodeController {

    @GetMapping("createSystemCode")
    public void createSystemCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
       /* int width = 120; // 验证码的宽度
        int height = 50; // 验证码的高度
        // 创建一个画布对象
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        // 创建一个画笔对象
        Graphics g = bi.getGraphics();
        // 给画布填充颜色
        g.setColor(Color.GRAY);
        // 填充矩形区域
        g.fillRect(0,0,width,height);
        // 获取随机生成的4位数字和字母组成的字符串
        String code = getCheckCode();
        // 将验证码存储在session作用域
        request.getSession().setAttribute("systemCode",code);
        // 设置画笔对象的颜色
        g.setColor(Color.BLUE);
        // 使用画笔对象设置字体的类型 风格字号
        g.setFont(new Font("微软雅黑",Font.BOLD,24));
        //设置了格式的字符串放在矩形区域里面去
        g.drawString(code,15,25);
        // 向浏览器输出图片
        ImageIO.write(bi,"jpg",response.getOutputStream());*/
        ServletOutputStream outputStream = response.getOutputStream();
        String sysCode = CheckCodeUtil.outputVerifyImage(100, 50, outputStream, 4);
        request.getSession().setAttribute("systemCode",sysCode);
        log.info("系统生成的验证码是:" + sysCode);
    }

    // 随机生成4位数字和字母组成的字符串
    public String getCheckCode(){
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int index = str.length() - 1;// 获取字符串中的最大索引值
        StringBuffer sbf = new StringBuffer();
        for(int i = 0;i<4;i++){
            int random  = (int) Math.floor(0 + Math.random() * (index + 1));
            sbf.append(str.charAt(random));
        }
        return sbf.toString();
    }
}
