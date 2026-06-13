package com.xq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2025-05-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cardsrecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("memberId")
    private Integer memberId;

    @TableField("typeId")
    private Integer typeId;

    private Float money;

    private String daoqi;

    private String createtime;

    private String remark;

    private Integer del;

    @TableField(exist = false)
    private Member member;
}
