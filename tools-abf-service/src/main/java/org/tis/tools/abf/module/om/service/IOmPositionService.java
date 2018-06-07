package org.tis.tools.abf.module.om.service;

import org.tis.tools.abf.module.om.controller.request.OmPositionRequest;
import org.tis.tools.abf.module.om.entity.OmPosition;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.om.entity.vo.OmPositionDetail;
import org.tis.tools.abf.module.om.exception.OrgManagementException;

/**
 * omPosition的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IOmPositionService extends IService<OmPosition>  {

    /**
     * 新增根岗位
     * @param omPositionRequest
     * @throws OrgManagementException
     */
    boolean addRoot(OmPositionRequest omPositionRequest) throws OrgManagementException;

    /**
     * 新增子岗位
     * @param omPositionRequest
     * @throws OrgManagementException
     */
    boolean addChild(OmPositionRequest omPositionRequest) throws OrgManagementException;

    /**
     * 修改岗位
     * @param omPositionRequest
     * @return
     * @throws OrgManagementException
     */
    boolean change(OmPositionRequest omPositionRequest) throws  OrgManagementException;

    /**
     * 岗位的树结构
     * @param id
     * @throws OrgManagementException
     */
    OmPositionDetail queryPositionTree(String id) throws OrgManagementException;

    /**
     * 删除父岗位
     * @param id
     * @throws OrgManagementException
     */
    void deleteRoot(String id)throws OrgManagementException;

}

