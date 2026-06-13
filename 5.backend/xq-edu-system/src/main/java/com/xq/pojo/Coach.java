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
 * @since 2025-05-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coach implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "coachId", type = IdType.AUTO)
    private Integer coachId;

    @TableField("coachName")
    private String coachName;

    @TableField("coachPhone")
    private String coachPhone;

    @TableField("coachSex")
    private Integer coachSex;

    @TableField("coachAge")
    private Integer coachAge;

    @TableField("coachDate")
    private String coachDate;

    private Integer teach;

    @TableField("coachWages")
    private Double coachWages;

    @TableField("coachAddress")
    private String coachAddress;

    @TableField("coachStatic")
    private Integer coachStatic;

    private Integer del;

    public Coach(Integer coachId,Integer del){
        this.coachId = coachId;
        this.del = del;
    }


}
