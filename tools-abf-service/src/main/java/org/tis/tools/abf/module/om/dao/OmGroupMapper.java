package org.tis.tools.abf.module.om.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.om.entity.OmEmpGroup;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.entity.OmGroup;

import java.util.List;

/**
 * omGroup的Mapper类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface OmGroupMapper extends BaseMapper<OmGroup>  {


    List<OmEmployee> selectOrgEmpNotInGroup(String guidOrg,String groupCode);

    List<AcApp> selectAppNotInGroup(String groudCode);
}

