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
 * @since 2025-04-25
 */
@Data // 省略写get set toString方法
@NoArgsConstructor // 提供无参数的构造函数
@AllArgsConstructor // 提供带参数的构造函数
public class Membertype implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "typeId", type = IdType.AUTO)
    private Integer typeId;

    @TableField("typeName")
    private String typeName;

    @TableField("typeciShu")
    private Integer typeciShu;

    @TableField("typeDay")
    private Integer typeDay;

    private Float typemoney;

    @TableField("typeDel")
    private Integer typeDel;

   public Membertype(Integer typeId,Integer typeDel){
       this.typeId = typeId;
       this.typeDel = typeDel;
   }
}
