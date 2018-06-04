package org.tis.tools.abf.module.sys.service;

import org.tis.tools.abf.module.sys.controller.request.SysErrCodeRequest;
import org.tis.tools.abf.module.sys.entity.SysErrCode;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.sys.exception.SysManagementException;

/**
 * sysErrCode的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface ISysErrCodeService extends IService<SysErrCode>  {

    /**
     * 新增系统错误码
     * @param sysErrCodeRequest
     * @throws SysManagementException
     */
    void add(SysErrCodeRequest sysErrCodeRequest) throws SysManagementException;

    /**
     * 修改系统错误码
     * @param sysErrCodeRequest
     * @throws SysManagementException
     */
    SysErrCode change(SysErrCodeRequest sysErrCodeRequest) throws SysManagementException;

}

