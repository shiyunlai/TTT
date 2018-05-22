package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcFuncAttr;
import org.tis.tools.abf.module.ac.exception.AcManagementException;

import java.util.List;

/**
 * acFuncAttr的Service接口类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcFuncAttrService extends IService<AcFuncAttr>  {

    /**
     *  新增功能属性
     * @param guidFunc  功能GUID
     * @param attrType  属性类型
     * @param attrKey   属性名
     * @param attrValue 属性名
     * @param memo      备注
     * @return
     */
    AcFuncAttr creatFuncAttr(String guidFunc,String attrType,String attrKey,String attrValue,String memo) throws AcManagementException;

    /**
     *  修改功能属性
     * @param guidFunc  功能GUID
     * @param attrType  属性类型
     * @param attrKey   属性名
     * @param attrValue 属性名
     * @param memo      备注
     * @return
     */
    AcFuncAttr changeFuncAttr(String guid,String guidFunc,String attrType,String attrKey,String attrValue,String memo)throws AcManagementException;

    /**
     * 根据GUID查询功能属性列表
     * @param id
     * @return
     */
    List<AcFuncAttr> queryList(String id)throws AcManagementException;
}

