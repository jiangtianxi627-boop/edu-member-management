package com.xq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2025-05-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "eqId", type = IdType.AUTO)
    private Integer eqId;

    @TableField("eqName")
    private String eqName;

    @TableField("eqText")
    private String eqText;

    private Integer del;

    public Equipment(Integer eqId,Integer del){
        this.eqId = eqId;
        this.del = del;
    }
}
