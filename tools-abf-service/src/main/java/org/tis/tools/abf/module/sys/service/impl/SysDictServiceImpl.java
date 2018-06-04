package org.tis.tools.abf.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.sys.dao.SysDictMapper;
import org.tis.tools.abf.module.sys.entity.SysDict;
import org.tis.tools.abf.module.sys.entity.SysDictItem;
import org.tis.tools.abf.module.sys.entity.vo.SysDictDetail;
import org.tis.tools.abf.module.sys.exception.SYSExceptionCodes;
import org.tis.tools.abf.module.sys.exception.SysManagementException;
import org.tis.tools.abf.module.sys.service.ISysDictItemService;
import org.tis.tools.abf.module.sys.service.ISysDictService;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.util.ArrayList;
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
    /**
     * @param sysDict
     * @return
     * @throws SysManagementException
     * */
    @Override
    public SysDict addDict(SysDict sysDict) throws SysManagementException {
        if(StringUtils.isNotBlank(sysDict.getGuidParents())){

            SysDict sysDictNew = selectById(sysDict.getGuidParents());
            if (sysDictNew == null){
                throw new SysManagementException(ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                        wrap(SYSExceptionCodes.NOT_FOUND_SYS_DICT_WITH_GUID,sysDict.getGuidParents())
                );
            }
        }
        insert(sysDict);
        return sysDict;
    }
    /**
     * @param id
     * @return
     * @throws SysManagementException
     * */
    @Override
    public SysDict queryDictDetail(String id) throws SysManagementException {
        return null;
    }
    /**
     * @param id
     * @return
     * @throws SysManagementException
     * */
    @Override
    public List<SysDict> queryDict(String id) throws SysManagementException {
        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDict.COLUMN_DICT_KEY,id);
        return selectList(wrapper);
    }
    /**
     * @param sysDict
     * @return
     * @throws SysManagementException
     * */
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
    /**
     * @param id
     * @return
     * @throws SysManagementException
     * */
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
    /**
     * @param
     * @return
     * @throws SysManagementException
     * */
    @Override
    public Page<SysDict> querySysDicts(Page<SysDict> page, Wrapper<SysDict> wrapper){
        return selectPage(page,wrapper);
    }
    /**
     * @param  id
     * @return
     * @throws SysManagementException
     * */
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
    /**
     * @param  sysDict
     * @return
     * @throws SysManagementException
     * */
    @Override
    public List<SysDict> dictKeyQuery(SysDict sysDict) throws SysManagementException {
        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        if(sysDict.getDictKey()!= null && sysDict.getDictKey()!= "" ){
            wrapper.eq(SysDict.COLUMN_DICT_KEY,sysDict.getDictKey());
        }
        if(sysDict.getDictName()!=null && sysDict.getDictName()!= ""){
            wrapper.eq(SysDict.COLUMN_DICT_NAME,sysDict.getDictName());
        }
        List<SysDict> list = selectList(wrapper);
        return list;
    }
    /**
     * @param  id
     * @return
     * @throws SysManagementException
     * */
    @Override
    public SysDict querySysDictByGuid(String id) throws SysManagementException {
        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDict.COLUMN_GUID,id);
        SysDict sysDict = selectOne(wrapper);
        return sysDict;
    }

    /**
     * 查询业务字典的树结构
     */
    @Override
    public SysDictDetail queryDictTree(String id) throws SysManagementException {

            SysDictDetail sysDictDetail = new SysDictDetail();

        try {
            //查询该业务字典
            SysDict sysDictOne = selectById(id);

            List<Object> list = new ArrayList<Object>();

            //查询业务字典对应的子业务字典
            Wrapper<SysDict> wrapper = new EntityWrapper<SysDict>();
            wrapper.eq(SysDict.COLUMN_GUID_PARENTS,id);

            List<SysDict> listDict = selectList(wrapper);

            //查询业务字典的字典项
            Wrapper<SysDictItem> wrapperItem = new EntityWrapper<SysDictItem>();
            wrapperItem.eq(SysDictItem.COLUMN_GUID_DICT,id);

            List<SysDictItem> listDictItem = iSysDictItemService.selectList(wrapperItem);

            //如果listDict和listDictItem非空,则将其内容set到
            for (SysDict sysDict: listDict) {
                list.add(sysDict);
            }
            for (SysDictItem sysDictItem: listDictItem) {
                list.add(sysDictItem);
            }

            sysDictDetail.setGuid(sysDictOne.getGuid());
            sysDictDetail.setDictKey(sysDictOne.getDictKey());
            sysDictDetail.setDictName(sysDictOne.getDictName());
            sysDictDetail.setChildren(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new SysManagementException(SYSExceptionCodes.NOT_FOUND_SYS_DICT_ITEM_WITH_GUID,
                    wrap(e.getMessage())
                    );
        }
        return sysDictDetail;
    }
}
