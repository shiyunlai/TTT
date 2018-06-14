package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.dao.AcFuncMapper;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.enums.FuncType;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.math.BigDecimal;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acFunc的Service接口实现类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcFuncServiceImpl extends ServiceImpl<AcFuncMapper, AcFunc> implements IAcFuncService {

    @Autowired
    IAcFuncService acFuncService;


    /**
     * 新增功能行为
     */
    @Override
    public AcFunc creatFunc(String guidApp, FuncType funcType, String funcCode, String funcName, String displayOrder, String funcDesc, String guidFunc,YON isopen,YON ischeck) throws AcManagementException {

        AcFunc acFunc = new AcFunc();

       if (null == isopen){
           isopen = YON.YES;
       }

       if (null == ischeck){
           ischeck = YON.YES;
       }

        try{
            BigDecimal displayOrderNew =  BigDecimal.valueOf(Double.valueOf(displayOrder));

            //收集参数
            acFunc.setGuidApp(guidApp);
            acFunc.setFuncCode(funcCode);
            acFunc.setFuncType(funcType);
            acFunc.setFuncName(funcName);
            acFunc.setDisplayOrder(displayOrderNew);
            acFunc.setFuncDesc(funcDesc);
            acFunc.setGuidFunc(guidFunc);

            //默认为需要验证
            acFunc.setIscheck(ischeck);
            //默认为应用启用
            acFunc.setIsopen(isopen);

            //新增
            acFuncService.insert(acFunc);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_CREATE_AC_FUNC,
                    wrap(e.getMessage())
            );
        }

        return acFunc;
    }

    /**
     * 修改功能行为
     */
    @Override
    public AcFunc changeFunc(String guid, String guidApp, FuncType funcType, String funcCode, String funcName, String funcDesc, YON isopen, YON ischeck, String displayOrder, String guidFunc) throws AcManagementException {

        AcFunc acFunc = new AcFunc();

        try{
            BigDecimal disPlayOrderNew = BigDecimal.valueOf(Double.valueOf(displayOrder));

            acFunc.setGuid(guid);
            acFunc.setGuidApp(guidApp);
            acFunc.setFuncType(funcType);
            acFunc.setFuncCode(funcCode);
            acFunc.setFuncName(funcName);
            acFunc.setFuncDesc(funcDesc);
            acFunc.setGuidFunc(guidFunc);
            acFunc.setDisplayOrder(disPlayOrderNew);
            acFunc.setIsopen(isopen);
            acFunc.setIscheck(ischeck);

            //修改
            acFuncService.updateById(acFunc);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_UPDATE_AC_FUNC,
                    wrap(e.getMessage())
            );
        }
        return acFunc;
    }

    /**
     * 查询对应应用下的功能
     */
    @Override
    public Page<AcFunc> queryPageById(Page<AcFunc> page, Wrapper<AcFunc> wrapper, String id) throws AcManagementException {

        Page<AcFunc> pagefunc = null;

        if (null == wrapper){
            wrapper = new EntityWrapper<AcFunc>();
        }

        try {
            wrapper.eq(AcFunc.COLUMN_GUID_APP,id);
            pagefunc = acFuncService.selectPage(page,wrapper);
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNC,wrap(e.getMessage()));
        }

        return pagefunc;
    }


    @Override
    public List<AcFunc> queryAll() throws AcManagementException {
        Wrapper<AcFunc> wrapper = new EntityWrapper<AcFunc>();
        return selectList(wrapper);
    }
}

