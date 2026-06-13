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
 * @since 2025-05-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goodsId", type = IdType.AUTO)
    private Integer goodsId;

    @TableField("goodsName")
    private String goodsName;

    private String unit;

    @TableField("unitPrice")
    private Double unitPrice;

    @TableField("sellPrice")
    private Double sellPrice;

    private Integer inventory;

    private String remark;

    private Integer del;

    public Goods(Integer goodsId,Integer del){
        this.goodsId = goodsId;
        this.del = del;
    }
}
