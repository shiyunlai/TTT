package org.tis.tools.abf.module.ac.entity.vo;

import lombok.Data;

/**
 * Created by chenchao
 * Created on 2018/7/25 9:52
 * 该类主要是返回操作员信息,操作员关联的员工信息,以及该员工的机构信息
 */
@Data
public class OperatorEmp {

    //操作员id
    private String operatorId;

    //操作员名名称
    private String empName;

    //员工代码
    private String empCode;

    //员工所在机构
    private String orgName;

    public OperatorEmp(){}

    @Override
    public String toString() {
        return super.toString();
    }
}
