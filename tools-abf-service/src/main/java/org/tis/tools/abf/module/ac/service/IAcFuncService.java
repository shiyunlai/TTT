package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.exception.AcManagementException;

import java.util.List;

/**
 * acFunc的Service接口类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcFuncService extends IService<AcFunc>  {

    /**
     * 新增功能父节点
     * @param guidApp       隶属应用GUID
     * @param funcType      功能类型
     * @param funcCode      功能编号
     * @param funcName      功能名称
     * @param displayOrder  显示顺序
     * @param funcDesc      功能描述
     * @return              AcFunc
     * @throws AcManagementException
     */
    AcFunc creatRootFunc(String guidApp,String funcType,String funcCode,String funcName,String displayOrder,
                         String funcDesc,String isopen,String ischeck) throws AcManagementException;

    /**
     * 新增功能子节点
     * @param guidApp       隶属应用GUID
     * @param funcType      功能类型
     * @param funcCode      功能编号
     * @param funcName      功能名称
     * @param displayOrder  显示顺序
     * @param funcDesc      功能描述
     * @param guidFunc      父节点GUID
     * @return              AcFunc
     * @throws AcManagementException
     */
    AcFunc creatFunc(String guidApp,String funcType,String funcCode,String funcName,String displayOrder,
                     String funcDesc,String guidFunc,String isopen,String ischeck) throws AcManagementException;

    /**
     * 修改功能
     * @param guid          功能GUID
     * @param guidApp       隶属应用GUID
     * @param funcType      功能类型
     * @param funcCode      功能编号
     * @param funcName      功能名称
     * @param funcDesc      功能描述
     * @param isopen        是否启用
     * @param ischeck       是否验证权限
     * @param displayOrder  显示顺序
     * @param guidFunc      父节点GUID
     * @return              AcFunc
     * @throws AcManagementException
     */
    AcFunc changeFunc(String guid, String guidApp, String funcType, String funcCode, String funcName, String funcDesc,
                      String isopen, String ischeck, String displayOrder, String guidFunc)throws AcManagementException;


    /**
     * 删除根功能
     * @param id
     * @return Boolean
     */
    Boolean clearRootFunc(String id,String statusData)throws AcManagementException;

    /**
     * 查询某个根功能列表
     * @param id
     * @return List<AcFunc>
     */
    List<AcFunc> queryFuncList(String id)throws AcManagementException;

    /**
     * 查询列表
     * @param page
     * @return
     */
    List<Object> getlist(Page<AcFunc> page);

}

