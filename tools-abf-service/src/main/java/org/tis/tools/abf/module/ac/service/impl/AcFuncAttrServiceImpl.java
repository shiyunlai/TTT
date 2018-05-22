package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcFuncAttrMapper;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcFuncAttrService;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.entity.AcFuncAttr;

import java.util.ArrayList;
import java.util.List;

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
                    wrap(AcFuncAttr.COLUMN_GUID_FUNC,guidFunc),guidFunc
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

        try{
            Wrapper<AcFuncAttr> wrapper = new EntityWrapper<AcFuncAttr>();
            wrapper.eq(AcFuncAttr.COLUMN_GUID,guid);

            //收集参数
            acFuncAttr.setGuid(guid);
            acFuncAttr.setGuidFunc(guidFunc);
            acFuncAttr.setAttrType(attrType);
            acFuncAttr.setAttrKey(attrKey);
            acFuncAttr.setAttrValue(attrValue);
            acFuncAttr.setMemo(memo);

            acFuncAttrService.update(acFuncAttr,wrapper);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_UPDATE_AC_FUNCRESOURCE,
                    wrap(AcFuncAttr.COLUMN_GUID_FUNC,guidFunc),guidFunc
            );
        }

        return acFuncAttr;
    }

    /**
     * 根据GUID查询功能属性列表
     */
    @Override
    public List<AcFuncAttr> queryList(String id)throws AcManagementException {

        List<AcFuncAttr> attrList = new ArrayList<AcFuncAttr>();

        try{
            //判断条件 guid_func = id and data_status = "0"
            Wrapper<AcFuncAttr> wrapper = new EntityWrapper<AcFuncAttr>();
            wrapper.eq(AcFuncAttr.COLUMN_GUID_FUNC,id)
            .eq(AcFuncAttr.COLUMN_DATA_STATUS,"0");

            attrList = acFuncAttrService.selectList(wrapper);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNCRESOURCE,
                    wrap(AcFuncAttr.COLUMN_GUID_FUNC,id),id
            );
        }

        return attrList;
    }
}

