package com.xq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2025-05-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privatecoachinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    private Integer memberid;

    private Integer coachid;

    private Integer subjectid;

    private Integer count;

    private Float countprice;

    private String date;

    private Integer state;

    private String remark;

    private String admin;

    private Integer del;


    @TableField(exist = false)
    private Member member;

    @TableField(exist = false)
    private Coach coach;

    @TableField(exist = false)
    private Subject subject;

    public Privatecoachinfo(Integer pid, Integer del) {
        this.pid = pid;
        this.del = del;
    }
}
