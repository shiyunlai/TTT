package org.tis.tools.abf.module.ac.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.entity.AcOperatorRole;
import org.tis.tools.abf.module.ac.entity.AcRole;

import java.util.List;

/**
 * acOperatorRole的Mapper类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface AcOperatorRoleMapper extends BaseMapper<AcOperatorRole>  {

    //查询已分配给角色的操作员
    List<AcOperator> queryOperatorByRole(@Param("roleId") String roleId);

    //查询已绑定给操作员的角色
    List<AcRole> queryRoleByOperator(@Param("operatorId") String operatorId);
}

