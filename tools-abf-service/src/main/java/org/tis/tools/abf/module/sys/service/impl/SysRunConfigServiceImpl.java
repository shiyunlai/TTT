package org.tis.tools.abf.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.module.om.exception.OrgManagementException;
import org.tis.tools.abf.module.sys.exception.SYSExceptionCodes;
import org.tis.tools.abf.module.sys.exception.SysManagementException;
import org.tis.tools.abf.module.sys.service.ISysRunConfigService;
import org.tis.tools.abf.module.sys.dao.SysRunConfigMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.sys.entity.SysRunConfig;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;
/**
 * <pre>
 * 系统参数（SYS_RUN_CONFIG）管理服务功能的实现类
 *
 * <pre>
 *
 * @author megapro
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRunConfigServiceImpl extends ServiceImpl<SysRunConfigMapper, SysRunConfig> implements ISysRunConfigService {
    /** 拷贝新增时，代码前缀  */
    private static final String CODE_HEAD_COPYFROM = "Copyfrom-";
    @Autowired
    private ISysRunConfigService sysRunConfigService;
    /**
     * 查询所有系统运行参数
     *
     * @return 系统运行参数集合
     * @throws SysManagementException
     */
    @Override
    public List<SysRunConfig> queryAllSysRunConfig(String guid) throws SysManagementException {
        List<SysRunConfig> list = null;
        try{
            EntityWrapper<SysRunConfig> wrapper = new EntityWrapper<>();
            if( !"".equals(guid)){
                wrapper.eq(SysRunConfig.COLUMN_GUID,guid);
                list = selectList(wrapper);
                if( list== null){
                    throw new SysManagementException(
                            ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                            wrap(SysRunConfig.COLUMN_GUID,guid),SysRunConfig.COLUMN_GUID);
                }
            }else{
                list = ((selectList(wrapper)));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new SysManagementException(
                    ExceptionCodes.FAILURE_WHEN_QUERY,
                    wrap("SYS_RUN_CONFIG",e));
        }
        return list;
    }
    /**
     * @param  guidApp
     * @param  groupName
     * @param  keyName
     * @param  valueFrom
     * @param  value
     * @param  description
     * @return
     * @throws SysManagementException
     * */
    @Override
    public SysRunConfig createSysRunConfig(String guidApp, String groupName, String keyName, String valueFrom, String value, String description) throws SysManagementException {
        try {
            SysRunConfig sysRunConfig = new SysRunConfig();
            sysRunConfig.setGuidApp(guidApp);
            sysRunConfig.setGroupName(groupName);
            sysRunConfig.setKeyName(keyName);
            sysRunConfig.setValueFrom(valueFrom);
            sysRunConfig.setValue(value);
            sysRunConfig.setDescription(description);
            insert(sysRunConfig);
            return sysRunConfig;
        }catch (SysManagementException ae){
            throw ae;
        }catch (Exception e) {
            e.printStackTrace();
            throw new OrgManagementException(
                    SYSExceptionCodes.FAILURE_WHEN_INSERT,
                    wrap("SYS_RUN_CONFIG",e));
        }
    }

    /**
     * @param guid
     * @param guidApp
     * @param groupName
     * @param keyName
     * @param valueFrom
     * @param value
     * @param description
     * @return
     * @throws SysManagementException
     */
    @Override
    public SysRunConfig editSysRunConfig(String guid,String guidApp, String groupName, String keyName, String valueFrom, String value, String description) throws SysManagementException {
        try {
            EntityWrapper<SysRunConfig> wrapper = new EntityWrapper<>();
            wrapper.eq(SysRunConfig.COLUMN_GUID,guid);
            if(selectOne(wrapper) == null){
                throw new SysManagementException(
                        ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                        wrap(SysRunConfig.COLUMN_GUID,guid),SysRunConfig.COLUMN_GUID);
            }
            SysRunConfig sysRunConfig = new SysRunConfig();
            sysRunConfig.setGuidApp(guidApp);
            sysRunConfig.setGroupName(groupName);
            sysRunConfig.setKeyName(keyName);
            sysRunConfig.setValueFrom(valueFrom);
            sysRunConfig.setValue(value);
            sysRunConfig.setDescription(description);
            update(sysRunConfig,wrapper);
            return sysRunConfig;
        } catch (SysManagementException ae) {
            throw ae;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysManagementException(
                    ExceptionCodes.FAILURE_WHEN_UPDATE,
                    wrap("SYS_RUN_CONFIG", e));
        }
    }
    /**
     * <p>删除系统运行参数</p>
     * <p>
     * <pre>
     *     验证必输字段：
     *          1.系统运行参数GUID:’guid’;
     *
     *     服务端业务逻辑：
     *          无
     *
     * @param guid
     * @throws SysManagementException
     */
    @Override
    public SysRunConfig deleteSysRunConfig(String guid) throws SysManagementException {
        try {
            if (guid == null || guid == ""){
                throw new SysManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE,wrap("GUID","SYS_RUN_CONFIG"));
            }
            EntityWrapper<SysRunConfig> wrapper = new EntityWrapper<>();
            wrapper.eq(SysRunConfig.COLUMN_GUID,guid);
            SysRunConfig sysRunConfig = selectOne(wrapper);
            if(sysRunConfig == null){
                throw new SysManagementException(
                        ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                        wrap(SysRunConfig.COLUMN_GUID,guid),SysRunConfig.COLUMN_GUID);
            }
            delete(wrapper);
            return sysRunConfig;
        } catch (SysManagementException ae) {
            throw ae;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysManagementException(
                    ExceptionCodes.FAILURE_WHEN_DELETE,
                    wrap("SYS_RUN_CONFIG", e));
        }
    }
}

