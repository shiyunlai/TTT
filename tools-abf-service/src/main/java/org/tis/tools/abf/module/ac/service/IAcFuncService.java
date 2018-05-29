package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.enums.FuncType;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.common.entity.enums.YON;

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


}

