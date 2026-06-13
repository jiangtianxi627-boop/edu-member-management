package com.xq.mapper;

import com.xq.pojo.Adminmenus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xq
 * @since 2025-06-02
 */
public interface AdminmenusMapper extends BaseMapper<Adminmenus> {

    List<Adminmenus> listMenusByAmindId(Integer adminId);
}
