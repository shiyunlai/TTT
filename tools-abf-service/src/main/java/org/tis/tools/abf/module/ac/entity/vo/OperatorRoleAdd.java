package org.tis.tools.abf.module.ac.entity.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by chenchao
 * Created on 2018/7/25 14:05
 * 该类用于批量新增功能和功能
 */
@Data
public class OperatorRoleAdd {

    //需要添加的功能数据
    private Map<String,List<String>> funcData;

    public OperatorRoleAdd(){}

    @Override
    public String toString() {
        return super.toString();
    }
}
