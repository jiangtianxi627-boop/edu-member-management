package com.xq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xq
 * @since 2025-05-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adminuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "adminId", type = IdType.AUTO)
    private Integer adminId;

    @TableField("adminName")
    private String adminName;

    @TableField("adminPassword")
    private String adminPassword;

    private String phone;

    private String birthday;

    private String idcard;

    @TableField("createTime")
    private String createTime;

    private Integer gender;

    private Integer del;

    public Adminuser(Integer adminId, int del) {
        this.adminId = adminId;
        this.del = del;
    }
}
