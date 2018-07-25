package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.enums.FuncType;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.util.List;

/**
 * acFunc的Service接口类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcFuncService extends IService<AcFunc>  {

    /**
     * 新增功能行为
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
    AcFunc creatFunc(String guidApp, FuncType funcType, String funcCode, String funcName, String displayOrder,
                     String funcDesc, String guidFunc, YON isopen, YON ischeck) throws AcManagementException;

    /**
     * 修改功能行为
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
    AcFunc changeFunc(String guid, String guidApp, FuncType funcType, String funcCode, String funcName, String funcDesc,
                      YON isopen, YON ischeck, String displayOrder, String guidFunc)throws AcManagementException;


    /**
     * 查询对应应用下的功能
     * @param page
     * @param wrapper
     * @param id
     * @return
     * @throws AcManagementException
     */
    Page<AcFunc> queryPageById(Page<AcFunc> page, Wrapper<AcFunc> wrapper ,String id)throws AcManagementException;

    /**
     * 查询所有功能的接口
     * @return
     * @throws AcManagementException
     */
    List<AcFunc> queryAll()throws AcManagementException;

    /**
     * 删除功能
     * @param id
     * @throws AcManagementException
     */
    void moveFunc(String id) throws AcManagementException;

    /**
     * 根据应用查询功能列表
     * @param appId
     * @return
     * @throws AcManagementException
     */
    List<AcFunc> queryFuncTreeByApp(String appId) throws AcManagementException;

    /**
     * 根据应用和父功能下的子功能查询功能列表
     * @param appId
     * @return
     * @throws AcManagementException
     */
    List<AcFunc> queryFuncTreeByAppFunc(String appId,String funcId) throws AcManagementException;

    /**
     * 根据应用和功能查询行为列表
     * @param appId
     * @return
     * @throws AcManagementException
     */
    List<AcFunc> queryBehaveTreeByAppFunc(String appId,String funcId) throws AcManagementException;
}

