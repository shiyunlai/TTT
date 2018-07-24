package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorMenuRequest;
import org.tis.tools.abf.module.ac.entity.AcOperatorMenu;
import org.tis.tools.abf.module.ac.exception.AcManagementException;

/**
 * acOperatorMenu的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcOperatorMenuService extends IService<AcOperatorMenu>  {

    /**
     * 新增根操作员重组菜单
     * @param acOperatorMenuRequest
     * @throws AcManagementException
     */
    void addRoot(AcOperatorMenuRequest acOperatorMenuRequest) throws AcManagementException;

    /**
     * 新增子操作员重组菜单
     * @param acOperatorMenuRequest
     * @throws AcManagementException
     */
    void addChild(AcOperatorMenuRequest acOperatorMenuRequest)throws AcManagementException;

    /**
     * 修改操作员重组菜单
     * @param acOperatorMenuRequest
     * @return
     * @throws AcManagementException
     */
    AcOperatorMenu change(AcOperatorMenuRequest acOperatorMenuRequest)throws AcManagementException;

    /**
     * 删除所有的操作员重组菜单
     * @param id
     * @throws AcManagementException
     */
    void deleteAllOperatorMenu(String id) throws AcManagementException;

    /**
     * 根据操作员ID查询重组菜单
     * @param page
     * @param wrapper
     * @param id
     * @return
     * @throws AcManagementException
     */
    Page<AcOperatorMenu> queryByOperatorId(Page<AcOperatorMenu> page, Wrapper<AcOperatorMenu> wrapper,String id)
            throws AcManagementException;
}

