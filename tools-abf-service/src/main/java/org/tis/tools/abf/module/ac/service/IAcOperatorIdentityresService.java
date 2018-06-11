package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorIdentityresRequest;
import org.tis.tools.abf.module.ac.entity.AcOperatorIdentityres;
import org.tis.tools.abf.module.ac.exception.AcManagementException;

/**
 * acOperatorIdentityres的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcOperatorIdentityresService extends IService<AcOperatorIdentityres>  {

    /**
     * 新增身份权限集
     * @param acOperatorIdentityres
     * @throws AcManagementException
     */
    void add(AcOperatorIdentityresRequest acOperatorIdentityres) throws AcManagementException;

    /**
     * 修改身份权限集
     * @param acOperatorIdentityres
     * @return
     * @throws AcManagementException
     */
    AcOperatorIdentityres change(AcOperatorIdentityresRequest acOperatorIdentityres)throws AcManagementException;

    /**
     * 通过身份id分页查询身份权限集
     * @param page
     * @param wrapper
     * @param id
     * @return
     * @throws AcManagementException
     */
    Page<AcOperatorIdentityres> queryByIdentity(Page<AcOperatorIdentityres> page, Wrapper<AcOperatorIdentityres>
            wrapper,String id)throws AcManagementException;

}

