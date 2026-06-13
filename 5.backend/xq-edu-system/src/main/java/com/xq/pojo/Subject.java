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
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "subId", type = IdType.AUTO)
    private Integer subId;

    private String subname;

    @TableField("sellingPrice")
    private Double sellingPrice;

    private Integer del;

   public Subject(Integer subId,Integer del){
       this.subId = subId;
       this.del = del;
   }
}
