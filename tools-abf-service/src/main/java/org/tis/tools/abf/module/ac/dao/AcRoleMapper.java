package org.tis.tools.abf.module.ac.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.tis.tools.abf.module.ac.entity.AcRole;

import java.util.List;

/**
 * acRole的Mapper类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface AcRoleMapper extends BaseMapper<AcRole>  {

    //查询分组字段的不重复值
    List<String> queryDistinctRoleGroup();

}

