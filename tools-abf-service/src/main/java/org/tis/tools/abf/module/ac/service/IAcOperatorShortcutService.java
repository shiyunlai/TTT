package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorShortcutRequest;
import org.tis.tools.abf.module.ac.entity.AcOperatorShortcut;
import org.tis.tools.abf.module.ac.exception.AcManagementException;

/**
 * acOperatorShortcut的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcOperatorShortcutService extends IService<AcOperatorShortcut>  {

    /**
     * 操作员快捷菜单的新增接口
     * @throws AcManagementException
     */
    void add(AcOperatorShortcutRequest acOperatorShortcutRequest)throws AcManagementException;

    /**
     * 操作员快捷菜单的修改接口
     * @param acOperatorShortcutRequest
     * @return
     * @throws AcManagementException
     */
    AcOperatorShortcut change(AcOperatorShortcutRequest acOperatorShortcutRequest)throws AcManagementException;

    /**
     * 根据
     * @param page
     * @param wrapper
     * @param OperatorId
     * @return
     * @throws AcManagementException
     */
    Page<AcOperatorShortcut> queryByOperator(Page<AcOperatorShortcut> page, Wrapper<AcOperatorShortcut> wrapper,String OperatorId)throws AcManagementException;

}

