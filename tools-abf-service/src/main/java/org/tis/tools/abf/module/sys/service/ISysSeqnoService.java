package org.tis.tools.abf.module.sys.service;


import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.sys.controller.request.SysSeqnoResetRequest;
import org.tis.tools.abf.module.sys.entity.SysSeqno;
import org.tis.tools.abf.module.sys.exception.SysManagementException;

public interface ISysSeqnoService extends IService<SysSeqno> {

    /**
     * 新增序号资源
     * @param sysSeqnoResetRequest
     * @return
     * @throws SysManagementException
     */
    void add(SysSeqnoResetRequest sysSeqnoResetRequest) throws SysManagementException;

    /**
     * 修改序号资源
     * @param sysSeqnoResetRequest
     * @return
     * @throws SysManagementException
     */
    SysSeqno change(SysSeqnoResetRequest sysSeqnoResetRequest) throws SysManagementException;

    /**
     * 获取下一个序列号
     * @param seqKey 序号键值
     * @return
     * @throws SysManagementException
     */
    long getNextSequence(String seqKey, String seqName) throws SysManagementException;




}
