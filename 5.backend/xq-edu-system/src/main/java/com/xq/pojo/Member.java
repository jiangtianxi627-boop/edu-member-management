package com.xq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2025-05-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "memberId", type = IdType.AUTO)
    private Integer memberId;

    @TableField("memberName")
    private String memberName;

    @TableField("memberPhone")
    private String memberPhone;

    @TableField("memberSex")
    private Integer memberSex;

    @TableField("memberAge")
    private Integer memberAge;

    @TableField("memberTypes")
    private Integer memberTypes;

    @TableField("nenberDate")
    private String nenberDate;

    private String birthday;

    @TableField("memberStatic")
    private Integer memberStatic;

    private Float memberbalance;

    private String memberxufei;

    private Integer del;

    @TableField(exist = false)
    private Membertype membertype;
}
