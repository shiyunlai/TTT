package org.tis.tools.abf.module.sys.service;

import org.tis.tools.abf.module.sys.exception.SysManagementException;

public interface ISysRunConfigGenerator {
    /**
     * 根据传入的参数，生成机构代码
     * @param orgDegree 机构等级
     * @param areaCode  地区码
     * @return
     * @throws SysManagementException
     */
    String genSysRunConfig(String orgDegree, String areaCode) throws SysManagementException;
}
