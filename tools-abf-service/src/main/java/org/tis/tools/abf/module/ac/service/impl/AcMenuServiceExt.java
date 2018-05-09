package org.tis.tools.abf.module.ac.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tis.tools.abf.module.ac.dao.AcMenuMapper;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ministrator on 2018/5/9.
 */
@Service
public class AcMenuServiceExt implements Serializable{
    @Autowired
    AcMenuMapper AcMenuMapper;
    /**
     * 重新排序菜单下的子菜单
     *
     * @param targetGuid 目标菜单GUID
     * @param index 起始位置
     * @param flag 自增或自减
     */
    public void reorderMenu(String targetGuid, BigDecimal index, String flag) {
        AcMenuMapper.reorderMenu(targetGuid, index, flag);
    }



}
