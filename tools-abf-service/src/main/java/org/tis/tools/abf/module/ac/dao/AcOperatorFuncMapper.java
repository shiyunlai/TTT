package org.tis.tools.abf.module.ac.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.AcOperatorFunc;

import java.util.List;

/**
 * acOperatorFunc的Mapper类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface AcOperatorFuncMapper extends BaseMapper<AcOperatorFunc>  {

    //根据角色id查询角色已关联应用
    List<AcApp> queryAppByOperator(@Param("operatorId") String operatorId);

    //根据角色id和应用id查询已关联功能
    List<AcFunc> queryFuncByOperator(@Param("operatorId") String operatorId, @Param("appId") String appId);

    //根据角色,应用id查询已关联行为
    List<AcFunc> queryBehaveByOperator(@Param("operatorId") String operatorId,@Param("appId") String appId,@Param("funcId") String funcId);

}

