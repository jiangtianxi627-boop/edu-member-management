package com.xq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Systemlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 0  普通日志  1异常日志
     */
    private Integer logtype;

    /**
     * 当前用户
     */
    private String nowuser;

    /**
     * 日志时间
     */
    private String createtime;

    /**
     * 日志的方法
     */
    private String methodname;

    /**
     * 日志描述信息
     */
    private String logdescription;

    /**
     * 方法参数信息
     */
    private String methodparms;

    /**
     * 方法返回值类型
     */
    private String methodtype;

    /**
     * 方法返回值类型
     */
    private String methodteturn;

    /**
     * 异常信息
     */
    private String exmessage;
}
