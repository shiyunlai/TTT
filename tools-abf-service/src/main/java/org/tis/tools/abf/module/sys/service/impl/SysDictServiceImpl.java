package org.tis.tools.abf.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.module.sys.dao.SysDictMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.sys.entity.SysDictItem;
import org.tis.tools.abf.module.sys.entity.enums.DictFromType;
import org.tis.tools.abf.module.sys.exception.SysManagementException;
import org.tis.tools.abf.module.sys.service.ISysDictItemService;
import org.tis.tools.abf.module.sys.service.ISysDictService;
import org.tis.tools.abf.module.sys.entity.SysDict;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;
/**
 * sysDict的Service接口实现类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictServiceImpl  extends ServiceImpl<SysDictMapper,SysDict> implements ISysDictService {
    @Autowired
    private ISysDictItemService iSysDictItemService;
    @Override
    public SysDict addDict(SysDict sysDict) throws SysManagementException {
        if(!"".equals(sysDict.guidParents)){
            SysDictItem sysDictItem = iSysDictItemService.guidQueryOneSysDic(sysDict.guidParents);
            if(sysDictItem == null ){
                throw new SysManagementException(
                        ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                        wrap(SysDictItem.COLUMN_GUID,SysDictItem.COLUMN_GUID),sysDict.guidParents);
            }
        }
        insert(sysDict);
        return sysDict;
    }

    @Override
    public SysDict queryDictDetail(String id) throws SysManagementException {
        return null;
    }

    @Override
    public List<SysDict> queryDict(String id) throws SysManagementException {
        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDict.COLUMN_DICT_KEY,id);
        return selectList(wrapper);
    }

    @Override
    public SysDict editSysDict(SysDict sysDict) throws SysManagementException {
        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDict.COLUMN_GUID,sysDict.getGuid());
        if(selectOne(wrapper) == null){
            throw new SysManagementException(
                    ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                    wrap(SysDictItem.COLUMN_GUID,sysDict.getGuid()),sysDict.getGuid());
        }
        update(sysDict,wrapper);
        return sysDict;
    }

    @Override
    public SysDict deleteDict(String id) throws SysManagementException {
        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDict.COLUMN_GUID,id);
        SysDict sysDict = selectOne(wrapper);
        if(sysDict == null){
            throw new SysManagementException(
                    ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                    wrap(SysDictItem.COLUMN_GUID,id),id);
        }
        delete(wrapper);
        return sysDict;
    }

    @Override
    public List<SysDict> querySysDicts() throws SysManagementException {
        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        return selectList(wrapper);
    }

    @Override
    public SysDict queryOneSysDictByGuid(String id) throws SysManagementException {
        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDict.COLUMN_GUID,id);
        return selectOne(wrapper);
    }

    @Override
    public SysDict setDefaultDictValue(String dictGuid, String itemValue) throws SysManagementException {
        return null;
    }

    @Override
    public List<SysDict> dictKeyQuery(SysDict sysDict) throws SysManagementException {
        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        if(sysDict.getDictKey()!= null && sysDict.getDictKey()!= "" ){
            wrapper.eq(SysDict.COLUMN_DICT_KEY,sysDict.getDictKey());
        }
        if(sysDict.getDictName()!=null && sysDict.getDictName()!= ""){
            wrapper.eq(SysDict.COLUMN_DICT_NAME,sysDict.getDictName());
        }
        return selectList(wrapper);
    }

    @Override
    public SysDict querySysDictByGuid(String id) throws SysManagementException {
        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDict.COLUMN_GUID,id);
        SysDict sysDict = selectOne(wrapper);
        return sysDict;
    }
}
