package org.tis.tools.abf.module.ac.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;

import java.util.List;

/**
 * acRoleFunc的Mapper类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface AcRoleFuncMapper extends BaseMapper<AcRoleFunc>  {

    //根据角色id查询角色已关联应用
    List<AcApp> queryAppByRole(@Param("roleId") String roleId);

    //根据角色id和应用id查询已关联功能
    List<AcFunc> queryFuncByRole(@Param("roleId") String roleId,@Param("appId") String appId);

    //根据角色,应用id查询已关联行为
    List<AcFunc> queryBehaveByRole(@Param("roleId") String roleId,@Param("appId") String appId,@Param("funcId") String funcId);
}

