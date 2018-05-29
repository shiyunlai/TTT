package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.dao.AcFuncAttrMapper;
import org.tis.tools.abf.module.ac.entity.AcFuncAttr;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcFuncAttrService;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acFuncAttr的Service接口实现类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcFuncAttrServiceImpl extends ServiceImpl<AcFuncAttrMapper, AcFuncAttr> implements IAcFuncAttrService {

    @Autowired
    IAcFuncAttrService acFuncAttrService;

    /**
     *  新增功能属性
     */
    @Override
    public AcFuncAttr creatFuncAttr(String guidFunc, String attrType, String attrKey, String attrValue, String memo)throws AcManagementException {

        AcFuncAttr acFuncAttr = new AcFuncAttr();

        try{
            //收集参数
            acFuncAttr.setGuidFunc(guidFunc);
            acFuncAttr.setAttrType(attrType);
            acFuncAttr.setAttrKey(attrKey);
            acFuncAttr.setAttrValue(attrValue);
            acFuncAttr.setMemo(memo);

            acFuncAttrService.insert(acFuncAttr);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_CREATE_AC_FUNCRESOURCE,
                    wrap(e.getMessage())
            );
        }

        return acFuncAttr;
    }

    /**
     * 修改功能属性
     */
    @Override
    public AcFuncAttr changeFuncAttr(String guid,String guidFunc, String attrType, String attrKey, String attrValue, String memo)throws AcManagementException {

        AcFuncAttr acFuncAttr = new AcFuncAttr();

        try{;
            //收集参数
            acFuncAttr.setGuid(guid);
            acFuncAttr.setGuidFunc(guidFunc);
            acFuncAttr.setAttrType(attrType);
            acFuncAttr.setAttrKey(attrKey);
            acFuncAttr.setAttrValue(attrValue);
            acFuncAttr.setMemo(memo);

            acFuncAttrService.updateById(acFuncAttr);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_UPDATE_AC_FUNCRESOURCE,
                    wrap(e.getMessage())
            );
        }

        return acFuncAttr;
    }

    /**
     * 根据GUID查询功能的属性列表
     */
    @Override
    public Page<AcFuncAttr> queryPageById(Page<AcFuncAttr> page, Wrapper<AcFuncAttr> entityWrapper , String id) throws AcManagementException {

        Page<AcFuncAttr> pageAttr = null;

        if (null == entityWrapper){
            entityWrapper = new EntityWrapper<AcFuncAttr>();
        }

        try {
            entityWrapper.eq(AcFuncAttr.COLUMN_GUID_FUNC,id);
            pageAttr = acFuncAttrService.selectPage(page,entityWrapper);
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNCRESOURCE,wrap(e.getMessage()));
        }

        return pageAttr;
    }
}

