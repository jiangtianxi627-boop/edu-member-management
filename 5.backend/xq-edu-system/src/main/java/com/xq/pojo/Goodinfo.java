package com.xq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
public class Goodinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer goodsid;

    private Integer memberid;

    private Integer count;

    private Float price;

    private String createtime;

    private String remark;

    private Integer del;

    @TableField(exist = false)
    private Member member;

    @TableField(exist = false)
    private Goods goods;

    public Goodinfo(Integer id,Integer del){
        this.id = id;
        this.del = del;
    }
}
