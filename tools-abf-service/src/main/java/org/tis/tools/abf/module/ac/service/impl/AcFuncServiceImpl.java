package org.tis.tools.abf.module.ac.service.impl;

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
    public AcFunc creatFunc(String guidApp, String funcType, String funcCode, String funcName, String displayOrder, String funcDesc, String guidFunc,String isopen,String ischeck) throws AcManagementException {

        AcFunc acFunc = new AcFunc();

        FuncType funcTypeNew = null;
        if ("FUNCTION".equals(funcType) || "function".equals(funcType) || "F".equals(funcType) || "f".equals(funcType)){
            funcTypeNew = FuncType.FUNCTION;
        }else if ("BEHAVE".equals(funcType) || "behave".equals(funcType) || "B".equals(funcType) || "b".equals(funcType)){
            funcTypeNew = FuncType.BEHAVE;
        }

        YON isopenNew = null;
        if ("YES".equals(isopen) || "yes".equals(isopen) ||"Y".equals(isopen) ||"y".equals(isopen)){
            isopenNew = YON.YES;
        }else if ("NO".equals(isopen) || "no".equals(isopen) ||"N".equals(isopen) ||"n".equals(isopen)){
            isopenNew = YON.NO;
        }else {
            isopenNew = YON.YES;
        }

        YON ischeckNew = null;
        if ("YES".equals(ischeck) || "yes".equals(ischeck) ||"Y".equals(ischeck) ||"y".equals(ischeck)){
            ischeckNew = YON.YES;
        }else if ("NO".equals(ischeck) || "no".equals(ischeck) ||"N".equals(ischeck) ||"n".equals(ischeck)){
            ischeckNew = YON.NO;
        }else {
            ischeckNew = YON.YES;
        }


        try{
            BigDecimal displayOrderNew =  BigDecimal.valueOf(Double.valueOf(displayOrder));

            //收集参数
            acFunc.setGuidApp(guidApp);
            acFunc.setFuncCode(funcCode);
            acFunc.setFuncType(funcTypeNew);
            acFunc.setFuncName(funcName);
            acFunc.setDisplayOrder(displayOrderNew);
            acFunc.setFuncDesc(funcDesc);
            acFunc.setGuidFunc(guidFunc);

            //默认为需要验证
            acFunc.setIscheck(ischeckNew);
            //默认为应用启用
            acFunc.setIsopen(isopenNew);

            //新增
            acFuncService.insert(acFunc);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_CREATE_AC_FUNC,
                    wrap(AcFunc.COLUMN_GUID_APP,guidApp),
                    guidApp
            );
        }

        return acFunc;
    }

    /**
     * 修改功能行为
     */
    @Override
    public AcFunc changeFunc(String guid, String guidApp, String funcType, String funcCode, String funcName, String funcDesc, String isopen, String ischeck, String displayOrder, String guidFunc) throws AcManagementException {

        AcFunc acFunc = new AcFunc();


        FuncType funcTypeNew = null;
        if ("FUNCTION".equals(funcType) || "function".equals(funcType) || "F".equals(funcType) || "f".equals(funcType)){
            funcTypeNew = FuncType.FUNCTION;
        }else if ("BEHAVE".equals(funcType) || "behave".equals(funcType) || "B".equals(funcType) || "b".equals(funcType)){
            funcTypeNew = FuncType.BEHAVE;
        }

        YON isopenNew = null;
        if ("YES".equals(isopen) || "yes".equals(isopen) ||"Y".equals(isopen) ||"y".equals(isopen)){
            isopenNew = YON.YES;
        }else if ("NO".equals(isopen) || "no".equals(isopen) ||"N".equals(isopen) ||"n".equals(isopen)){
            isopenNew = YON.NO;
        }

        YON ischeckNew = null;
        if ("YES".equals(ischeck) || "yes".equals(ischeck) ||"Y".equals(ischeck) ||"y".equals(ischeck)){
            ischeckNew = YON.YES;
        }else if ("NO".equals(ischeck) || "no".equals(ischeck) ||"N".equals(ischeck) ||"n".equals(ischeck)){
            ischeckNew = YON.NO;
        }

        try{
            BigDecimal disPlayOrderNew = BigDecimal.valueOf(Double.valueOf(displayOrder));

            acFunc.setGuid(guid);
            acFunc.setGuidApp(guidApp);
            acFunc.setFuncType(funcTypeNew);
            acFunc.setFuncCode(funcCode);
            acFunc.setFuncName(funcName);
            acFunc.setFuncDesc(funcDesc);
            acFunc.setGuidFunc(guidFunc);
            acFunc.setDisplayOrder(disPlayOrderNew);
            acFunc.setIsopen(isopenNew);
            acFunc.setIscheck(ischeckNew);

            //修改
            acFuncService.updateById(acFunc);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_UPDATE_AC_FUNCGROUP,
                    wrap(AcFunc.COLUMN_GUID,guid),
                    guid
            );
        }
        return acFunc;
    }

}

