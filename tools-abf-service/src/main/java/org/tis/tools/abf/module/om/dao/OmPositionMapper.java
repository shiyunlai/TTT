package org.tis.tools.abf.module.om.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.tis.tools.abf.module.om.entity.OmPosition;

/**
 * omPosition的Mapper类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface OmPositionMapper extends BaseMapper<OmPosition>  {

    int queryEmpCountByOrgPosition(@Param("guidorg") String guidorg , @Param("guidPosition") String guidPosition);

    String queryParentName(String parentId);

}

