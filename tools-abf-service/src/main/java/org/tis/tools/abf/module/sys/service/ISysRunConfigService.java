package org.tis.tools.abf.module.sys.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.sys.entity.SysRunConfig;
import org.tis.tools.abf.module.sys.exception.SysManagementException;

import java.util.List;

public interface ISysRunConfigService extends IService<SysRunConfig>{
    /**
     * 查询所有系统运行参数
     *
     * @return 系统运行参数集合
     * @throws SysManagementException
     */
    List<SysRunConfig> queryAllSysRunConfig(String guid) throws SysManagementException;


    /**
     * <p>新增系统运行参数</p>
     *
     * <pre>
     *     验证必输字段：
     *          1.应用GUID:’guidApp’;
     *          2.参数组别:’groupName’;
     *          3.参数键: ‘keyName’ ;
     *          4.值来源类型:’valueFrom’ ;
     *          5.参数值:’value’ ;
     *
     *     服务端业务逻辑：
     *          1.生成主键GUID，通过方法GUID.runConfig(）
     * </pre>
     *
     * @param   guidApp
     * @param   groupName
     * @param   keyName
     * @param   valueFrom
     * @param   value
     * @param   description
     * @return  SysRunConfig
     * @throws SysManagementException
     */
    SysRunConfig createSysRunConfig(String guidApp,String groupName,String keyName,String valueFrom,String value,String description) throws SysManagementException;

    /**
     * <p>修改系统运行参数</p>
     *
     * <pre>
     *     验证必输字段：
     *          1.应用GUID:’guidApp’;
     *          2.参数组别:’groupName’;
     *          3.参数键: ‘keyName’ ;
     *          4.值来源类型:’valueFrom’ ;
     *          5.参数值:’value’ ;
     *
     *     服务端业务逻辑：
     *          无
     * </pre>
     * @param   guid
     * @param   guidApp
     * @param   groupName
     * @param   keyName
     * @param   valueFrom
     * @param   value
     * @param   description
     * @return  SysRunConfig
     * @throws SysManagementException
     */
    SysRunConfig editSysRunConfig(String guid,String guidApp,String groupName,String keyName,String valueFrom,String value,String description) throws SysManagementException;

    /**
     * <p>删除系统运行参数</p>
     *
     * <pre>
     *     验证必输字段：
     *          1.系统运行参数GUID:’guid’;
     *
     *     服务端业务逻辑：
     *          无
     *
     * @param guid
     * @throws SysManagementException
     */
    SysRunConfig deleteSysRunConfig(String guid) throws SysManagementException;

}
