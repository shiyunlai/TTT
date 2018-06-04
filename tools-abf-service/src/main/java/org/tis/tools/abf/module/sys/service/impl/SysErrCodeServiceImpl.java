package org.tis.tools.abf.module.sys.service.impl;

import org.tis.tools.abf.module.sys.controller.request.SysErrCodeRequest;
import org.tis.tools.abf.module.sys.entity.SysErrCode;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.sys.dao.SysErrCodeMapper;
import org.tis.tools.abf.module.sys.exception.SYSExceptionCodes;
import org.tis.tools.abf.module.sys.exception.SysManagementException;
import org.tis.tools.abf.module.sys.service.ISysErrCodeService;
import org.springframework.transaction.annotation.Transactional;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * sysErrCode的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysErrCodeServiceImpl extends ServiceImpl<SysErrCodeMapper, SysErrCode> implements ISysErrCodeService {

    @Override
    public void add(SysErrCodeRequest sysErrCodeRequest) throws SysManagementException {

        SysErrCode sysErrCode = new SysErrCode();

        //收集参数
        sysErrCode.setErrCode(sysErrCodeRequest.getErrCode());
        sysErrCode.setErrcodeKind(sysErrCodeRequest.getErrcodeKind());
        sysErrCode.setErrMsg(sysErrCodeRequest.getErrMsg());

        try {
                insert(sysErrCode);
        }catch (Exception e){
            e.printStackTrace();
            throw new SysManagementException(SYSExceptionCodes.FAILURE_WHEN_INSERT,
                    wrap(e.getMessage()));
        }

    }

    @Override
    public SysErrCode change(SysErrCodeRequest sysErrCodeRequest) throws SysManagementException {

        SysErrCode sysErrCode = new SysErrCode();

        //收集参数
        sysErrCode.setGuid(sysErrCodeRequest.getGuid());
        sysErrCode.setErrCode(sysErrCodeRequest.getErrCode());
        sysErrCode.setErrcodeKind(sysErrCodeRequest.getErrcodeKind());
        sysErrCode.setErrMsg(sysErrCodeRequest.getErrMsg());

        try {
            updateById(sysErrCode);
        }catch (Exception e){
            e.printStackTrace();
            throw new SysManagementException(SYSExceptionCodes.FAILURE_WHEN_UPDATE,
                    wrap(e.getMessage()));
        }

        return sysErrCode;
    }
}

