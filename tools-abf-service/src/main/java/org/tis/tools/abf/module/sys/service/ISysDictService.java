package org.tis.tools.abf.module.sys.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.sys.entity.SysDict;
import org.tis.tools.abf.module.sys.entity.SysDictItem;
import org.tis.tools.abf.module.sys.exception.SysManagementException;

import java.util.List;

/**
 * <pre>
 * 业务菜单服务
 * </pre>
 * @author megapro
 *
 */
public interface ISysDictService extends IService<SysDict> {
    /**
     * <pre>
     * 新增业务字典
     * 系统自动补充guid
     *
     * </pre>
     * @param  sysDict
     * @return 新增的业务字典记录
     * @throws SysManagementException
     */
    SysDict addDict(SysDict sysDict) throws SysManagementException;

    /**
     * <pre>
     * 查询指定dictKey对应的业务字典信息（包括子业务字典信息）
     * 一次查询可获取的信息包括：
     * 1. 业务字典
     * 2. 字典项
     * 3. 子业务字典
     *
     * </pre>
     * @param id 字典KEY
     * @return 字典信息视图对象(SysDictDetail)
     * @throws SysManagementException
     */
    SysDict queryDictDetail( String id ) throws SysManagementException;

    /**
     * <pre>
     * 查询指定dictKey对应的业务字典信息（本身内容）
     * 一次查询可获取的信息包括：
     * 1. 业务字典
     * 2. 字典项
     *
     * </pre>
     * @param id 字典KEY
     * @return 对应字典（SysDict）
     * @throws SysManagementException
     */
    List<SysDict> queryDict( String id ) throws SysManagementException;

    /**
     * 修改业务字典
     * @param sysDict
     * @throws SysManagementException
     */
    SysDict editSysDict(SysDict sysDict) throws SysManagementException;


    /**
     * 根据业务字典GUID删除业务字典
     *
     * @param id
     * 			业务字典GUID
     * @throws SysManagementException
     */
    SysDict deleteDict(String id) throws SysManagementException;

    /**
     * 查询所有业务字典
     *
     * @param
     * @return
     * @throws SysManagementException
     */
    List<SysDict> querySysDicts() throws SysManagementException;

    /**
     * 根据GUID查询业务字典自身
     * @param id
     * 			业务字典GUID
     * @return
     * @throws SysManagementException
     */
    SysDict queryOneSysDictByGuid(String id) throws SysManagementException;

    /**
     * 设置业务字典的默认字典项
     *
     * @param dictGuid 字典GUID
     * @param itemValue 默认值
     * @return
     * @throws SysManagementException
     */
    SysDict setDefaultDictValue(String dictGuid, String itemValue) throws SysManagementException;

    /**
     * 设置业务字典的默认字典项
     *
     * @param sysDict 字典GUID
     * @return
     * @throws SysManagementException
     */
    List<SysDict> dictKeyQuery(SysDict sysDict) throws SysManagementException;
    /**
     * 设置业务字典的默认字典项
     *
     * @param id 字典GUID
     * @return
     * @throws SysManagementException
     */
    SysDict querySysDictByGuid(String id) throws SysManagementException;

}
