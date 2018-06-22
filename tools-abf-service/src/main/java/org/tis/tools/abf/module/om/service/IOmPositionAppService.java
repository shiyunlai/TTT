package org.tis.tools.abf.module.om.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.om.controller.request.OmPositionAppListRequest;
import org.tis.tools.abf.module.om.controller.request.OmPositionAppRequest;
import org.tis.tools.abf.module.om.entity.OmPositionApp;
import org.tis.tools.abf.module.om.exception.OrgManagementException;

import java.util.List;

/**
 * omPositionApp的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IOmPositionAppService extends IService<OmPositionApp>  {

    /**
     * 新增
     * @throws OrgManagementException
     */
    void add(OmPositionAppRequest omPositionAppRequest) throws OrgManagementException;

    /**
     * 批量新增岗位应用列表
     * @param om
     * @throws OrgManagementException
     */
    void addList(OmPositionAppListRequest om)throws OrgManagementException;

    /**
     * 修改
     * @param omPositionAppRequest
     * @return
     * @throws OrgManagementException
     */
    OmPositionApp change(OmPositionAppRequest omPositionAppRequest) throws OrgManagementException;

    /**
     * 查询岗位下已有的应用权限
     * @throws OrgManagementException
     */
    Page<AcApp> queryAppByPosition(Page<OmPositionApp> page, Wrapper<OmPositionApp> wrapper,String id)throws
            OrgManagementException;

    /**
     * 查询不在该岗位下的应用权限
     * @param id
     * @return
     * @throws OrgManagementException
     */
    List<AcApp> queryNotInPosition(String id)throws OrgManagementException;

    /**
     * 根据岗位id和应用id删除这条数据
     * @throws OrgManagementException
     */
    void deleteByPositionAndApp(String guidApp, String guidPosition )throws OrgManagementException;

}

