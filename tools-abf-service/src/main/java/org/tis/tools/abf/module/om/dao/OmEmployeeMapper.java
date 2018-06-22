package org.tis.tools.abf.module.om.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import org.tis.tools.abf.module.om.entity.OmEmployee;

import java.util.List;

/**
 * omEmployee的Mapper类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface OmEmployeeMapper extends BaseMapper<OmEmployee>  {

    List<OmEmployee> queryByOrgPosition(Pagination page , @Param("guidorg") String guidorg ,@Param("guidPosition") String guidPosition);

}

