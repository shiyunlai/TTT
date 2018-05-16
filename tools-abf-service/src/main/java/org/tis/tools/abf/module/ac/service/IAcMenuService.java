package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;
import org.tis.tools.abf.module.ac.entity.AcMenu;
import org.tis.tools.abf.module.ac.exception.AcMenuManagementException;

import java.math.BigDecimal;
import java.util.List;

/**
 * acMenu的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcMenuService extends IService<AcMenu>  {
    /**
     * 菜单移动
     *
     * @param targetGuid  目标菜单GUID
     * @param moveGuid   移动的菜单GUID
     * @param order 排序
     * @exception AcMenuManagementException
     */
    AcMenu moveMenu(String targetGuid, String moveGuid, BigDecimal order)throws AcMenuManagementException;
    /**
     * <p>
     * 分页查询
     * </p>
     *
     * @param offset  当前页
     * @param limit  每页显示数
     * @return List<AcMenu> 所有子菜单值
     * @exception AcMenuManagementException
     */
    Page<AcMenu> queryPageAcMenu(int offset , int limit) throws AcMenuManagementException;

    /**
     * 删除父菜单及其子菜单
     * @param menuGuid  父菜单GUID
     * @return List<AcMenu> 所有子菜单值
     * @exception AcMenuManagementException
     */
    AcMenu deleteAllSubAcMenu(String menuGuid) throws AcMenuManagementException;

    /**
     * 修改菜单
     * @param acMenu  修改菜单对象
     * @return 修改结果
     * @exception AcMenuManagementException
     */
    AcMenu updateAcMenu(AcMenu acMenu) throws AcMenuManagementException;

    /**
     * 新增子菜单
     * @param acMenu  新增菜单对象
     * @return 新增结果
     * @exception AcMenuManagementException
     */
    AcMenu createChildMenu(AcMenu acMenu) throws AcMenuManagementException;

    /**
     * 新增根菜单
     * @param acMenu  新增菜单对象
     * @return 新增结果
     * @exception AcMenuManagementException
     */
    AcMenu createRootMenu(AcMenu acMenu) throws AcMenuManagementException;

    /**
     * 查询应用下根菜单
     * @param GUID_APP  新增菜单对象
     * @return 新增结果
     * @exception AcMenuManagementException
     */
    List<AcMenu> queryRootMenu(String GUID_APP) throws AcMenuManagementException;

    /**
     * <p>
     * 根据 id 条件，查询该菜单的下一级所有的子菜单
     * </p>
     *
     * @param gidParents  菜单Gid
     * @return List<AcMenu> 所有子菜单值
     * @exception AcMenuManagementException
     */
    List<AcMenu> selectSubMenu(String gidParents)throws AcMenuManagementException;

    /**
     * 重新排序菜单下的子菜单
     *
     * @param identityGuid 目标菜单GUID
     * @param index 起始位置
     * @param flag 自增或自减
     */
    void reorderMenu(String identityGuid, BigDecimal index ,String flag);
}

