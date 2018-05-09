package org.tis.tools.abf.module.sys.service;

import com.baomidou.mybatisplus.service.IService;
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
public interface ISysDictItemService extends IService<SysDictItem> {
    /**
     * <pre>
     * 新增业务字典项
     * 系统自动补充guid
     *
     * </pre>
     * @param guidDict 业务字典项
     * @param  itemName
     * @param  itemType
     * @param  itemValue
     * @param  sendValue
     * @param  seqNo
     * @param  itemDesc
     * @return 新增的业务字典项记录
     * @throws SysManagementException
     */
    SysDictItem addDictItem(String guidDict,String itemName,String itemType,String itemValue,String sendValue,String seqNo,
                            String itemDesc) throws SysManagementException;


    /**
     * <pre>
     * 查询指定dictKey对应的业务字典信息（本身内容）
     * 一次查询可获取的信息包括：
     * 1. 业务字典
     * 2. 字典项
     *
     * </pre>
     * @param guidDict 字典KEY
     * @return 对应字典（SysDict）
     * @throws SysManagementException
     */
    List<SysDictItem> queryDict(String guidDict ) throws SysManagementException;

    /**
     * 修改业务字典项
     * @param guidDict 业务字典项
     * @param  itemName
     * @param  itemType
     * @param  itemValue
     * @param  sendValue
     * @param  seqNo
     * @param  itemDesc
     * @throws SysManagementException
     */
    SysDictItem editSysDictItem(String guid,String guidDict,String itemName,String itemType,String itemValue,String sendValue,String seqNo,String itemDesc) throws SysManagementException;

    /**
     * 根据字典项GUID删除业务字典项
     *
     * @param id
     * 			字典项GUID
     * @throws SysManagementException
     */
    SysDictItem deleteDictItem(String id) throws SysManagementException;

    /**
     * 根据字典GUID查询所有业务字典项
     *
     * @param id
     * 			业务字典GUID
     * @return
     * @throws SysManagementException
     */
    List<SysDictItem> querySysDictItems(String id) throws SysManagementException;


    /**
     * 根据GUID查询业务字典自身
     * @param id
     * 			业务字典GUID
     * @return
     * @throws SysManagementException
     */
    SysDictItem guidQueryOneSysDic(String id) throws SysManagementException;
    /**
     * 查询所有业务字典项
     *
     * @return
     * @throws SysManagementException
     */
    List<SysDictItem> querySysDictItemList() throws SysManagementException;

    /**
     * 根据字典项GUID删除业务字典项
     *
     * @param id
     * 			字典项GUID
     * @throws SysManagementException
     */
    SysDictItem keyQueryDictItem(String id) throws SysManagementException;
}
