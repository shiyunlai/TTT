package org.tis.tools.abf.module.om.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.om.controller.request.OmPositionAppRequest;
import org.tis.tools.abf.module.om.entity.OmPositionApp;
import org.tis.tools.abf.module.om.exception.OrgManagementException;

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
     * 修改
     * @param omPositionAppRequest
     * @return
     * @throws OrgManagementException
     */
    OmPositionApp change(OmPositionAppRequest omPositionAppRequest) throws OrgManagementException;

}

