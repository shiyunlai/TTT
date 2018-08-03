package org.tis.tools.abf.module.ac.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import org.tis.tools.abf.module.ac.entity.AcOperator;

import java.util.List;

/**
 * acOperator的Mapper类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface AcOperatorMapper extends BaseMapper<AcOperator>  {

    List<AcOperator> queryByRole(Pagination page , @Param("roleId") String roleId , @Param("ew") EntityWrapper<AcOperator> ew);
}

