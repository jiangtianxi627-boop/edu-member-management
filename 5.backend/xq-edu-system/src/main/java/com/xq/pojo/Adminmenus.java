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
 * @since 2025-06-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adminmenus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String url;

    private String iocn;

    @TableField("parentId")
    private Integer parentId;

    /**
     * 0 菜单  1 权限
     */
    private Integer type;

    private String remark;

    private Integer sort;

    private Integer del;

}
