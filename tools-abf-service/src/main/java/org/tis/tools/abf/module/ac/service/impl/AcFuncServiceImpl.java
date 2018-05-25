package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 新增功能父节点
     */
    @Override
    public AcFunc creatRootFunc(String guidApp, String funcType, String funcCode, String funcName, String displayOrder,
                                String funcDesc,String isopen,String ischeck) throws AcManagementException {

        AcFunc acFunc = new AcFunc();


        FuncType funcTypeNew = null;
        if ("PAGEPROCESS".equals(funcType) || "pageprocess".equals(funcType)){
            funcTypeNew = FuncType.PAGEPROCESS;
        }else if ("TRADEPROCESS".equals(funcType) || "tradeprocess".equals(funcType)){
            funcTypeNew = FuncType.TRADEPROCESS;
        }else if ("RESTFUL".equals(funcType) || "restful".equals(funcType)){
            funcTypeNew = FuncType.RESTFUL;
        }else if ("TWSTX".equals(funcType) || "twstx".equals(funcType)){
            funcTypeNew = FuncType.TWSTX;
        }

        //默认为应用启用
        YON isopenNew = null;
         if ("YES".equals(isopen) || "yes".equals(isopen) ||"Y".equals(isopen) ||"y".equals(isopen)){
            isopenNew = YON.YES;
        }else if ("NO".equals(isopen) || "no".equals(isopen) ||"N".equals(isopen) ||"n".equals(isopen)){
            isopenNew = YON.NO;
        }else {
             isopenNew = YON.YES;
         }

        //默认需要验证
        YON ischeckNew = null;
        if ("YES".equals(ischeck) || "yes".equals(ischeck) ||"Y".equals(ischeck) ||"y".equals(ischeck)){
            ischeckNew = YON.YES;
        }else if ("NO".equals(ischeck) || "no".equals(ischeck) ||"N".equals(ischeck) ||"n".equals(ischeck)){
            ischeckNew = YON.NO;
        }else {
            ischeckNew = YON.YES;
        }

        try{
            BigDecimal displayOrderNew = BigDecimal.valueOf(Double.valueOf(displayOrder));
            //收集参数
            acFunc.setGuidApp(guidApp);
            acFunc.setFuncCode(funcCode);
            acFunc.setFuncType(funcTypeNew);
            acFunc.setFuncName(funcName);
            acFunc.setDisplayOrder(displayOrderNew);
            acFunc.setFuncDesc(funcDesc);

            acFunc.setIscheck(ischeckNew);

            acFunc.setIsopen(isopenNew);

            //没有父类,所以默认父类为0
            acFunc.setGuidFunc("0");

            //新增
            acFuncService.insert(acFunc);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_CREATE_AC_FUNCGROUP,
                    wrap(AcFunc.COLUMN_GUID_APP,guidApp),
                    guidApp
            );
        }
        return acFunc;
    }


    /**
     * 新增功能子节点
     */
    @Override
    public AcFunc creatFunc(String guidApp, String funcType, String funcCode, String funcName, String displayOrder, String funcDesc, String guidFunc,String isopen,String ischeck) throws AcManagementException {

        AcFunc acFunc = new AcFunc();

        FuncType funcTypeNew = null;

        if ("PAGEPROCESS".equals(funcType) || "pageprocess".equals(funcType)){
            funcTypeNew = FuncType.PAGEPROCESS;
        }else if ("TRADEPROCESS".equals(funcType) || "tradeprocess".equals(funcType)){
            funcTypeNew = FuncType.TRADEPROCESS;
        }else if ("RESTFUL".equals(funcType) || "restful".equals(funcType)){
            funcTypeNew = FuncType.RESTFUL;
        }else if ("TWSTX".equals(funcType) || "twstx".equals(funcType)){
            funcTypeNew = FuncType.TWSTX;
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
            BigDecimal displayOrderNew = BigDecimal.valueOf(Double.valueOf(displayOrder));

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
     * 修改功能
     */
    @Override
    public AcFunc changeFunc(String guid, String guidApp, String funcType, String funcCode, String funcName, String funcDesc, String isopen, String ischeck, String displayOrder, String guidFunc) throws AcManagementException {

        AcFunc acFunc = new AcFunc();


        FuncType funcTypeNew = null;
        if ("PAGEPROCESS".equals(funcType) || "pageprocess".equals(funcType)){
            funcTypeNew = FuncType.PAGEPROCESS;
        }else if ("TRADEPROCESS".equals(funcType) || "tradeprocess".equals(funcType)){
            funcTypeNew = FuncType.TRADEPROCESS;
        }else if ("RESTFUL".equals(funcType) || "restful".equals(funcType)){
            funcTypeNew = FuncType.RESTFUL;
        }else if ("TWSTX".equals(funcType) || "twstx".equals(funcType)){
            funcTypeNew = FuncType.TWSTX;
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


    /**
     * 删除根功能
     */
    @Override
    public Boolean clearRootFunc(String id,String statusData)throws AcManagementException {

        Boolean isDel = false;
        try{
            //根据id查询出该父功能下的子功能
            Wrapper<AcFunc> wrapper = new EntityWrapper<AcFunc>();
            wrapper.eq(AcFunc.COLUMN_GUID_FUNC,id)
            .eq(AcFunc.COLUMN_DATA_STATUS,statusData);

            //需要改成自己写的查询以便异常捕获
            List<AcFunc> acFuncList = acFuncService.selectList(wrapper);

            //当根功能下没有子功能时可以直接删除
            if (null == acFuncList && 0 == acFuncList.size()){
                isDel = acFuncService.deleteById(id);
            }else{
                //当根功能下有子功能,首先获取子功能的guid
                ArrayList<String> funcIdList = new ArrayList<String>();
                for (AcFunc acFunc :acFuncList) {
                    funcIdList.add(acFunc.getGuid());
                }
                //删除所有的子功能
                Boolean isDelAll = acFuncService.deleteBatchIds(funcIdList);
                if (true == isDelAll){
                    //如果所有的子功能全部删除了,再删除根功能
                    isDel = acFuncService.deleteById(id);
                }else{
                    throw new AcManagementException(
                            AcExceptionCodes.FAILURE_WHRN_DELETE_AC_FUNC,
                            wrap(AcFunc.COLUMN_GUID_FUNC,id),
                            id
                    );
                }
            }
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_DELETE_AC_FUNCGROUP,
                    wrap(AcFunc.COLUMN_GUID_FUNC,id),
                    id
            );
        }
        return isDel;
    }

    /**
     * 查询某个根功能列表
     */
    @Override
    public List<AcFunc> queryFuncList(String id)throws AcManagementException {

        List<AcFunc> funcList = new ArrayList<AcFunc>();
        try{
            AcFunc acFunc = acFuncService.selectById(id);
            if ("0".equals(acFunc.getGuidFunc())){
                //添加查询条件
                Wrapper<AcFunc> wrapper = new EntityWrapper<AcFunc>();
                wrapper.eq(AcFunc.COLUMN_GUID_FUNC,id)
                .eq(AcFunc.COLUMN_DATA_STATUS,"0");

                //查询出该根功能下的子功能
                funcList = acFuncService.selectList(wrapper);
            }else{
                throw new AcManagementException(
                        AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNCGROUP,
                        wrap(AcFunc.COLUMN_GUID,id),id
                );
            }
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNC,
                    wrap(AcFunc.COLUMN_GUID_FUNC,id),id
            );
        }
        return funcList;
    }


    /**
     * 查询列表
     */
    @Override
    public  List<Object>  getlist(Page<AcFunc> page) {

        //创建一个返回对象listMap
        List<Object> listMap = new ArrayList<Object>();

        try{
            //默认查询顺序为升序
            Boolean isAsc = true;

            //获取当前页和每页数目
            Integer current = page.getCurrent() - 1;
            Integer  size = page.getSize();

            //如果当前页为0或者为null,默认设置为 0
            if (current <= 0 || current == null){
                current = 0;
            }
            //如果每页数目为空,默认设置为 10
            if (size == null){
                size = 10;
            }

            //拼接分页查询的 sql
            String limtSql = "limit "+(current * size)+","+size;
            Wrapper<AcFunc> acFuncWrapper = new EntityWrapper<AcFunc>();
            acFuncWrapper.eq(AcFunc.COLUMN_GUID_FUNC,"0")
                    .orderBy(AcFunc.COLUMN_DISPLAY_ORDER,isAsc)
                    .last(limtSql);

            //计算总数
            Integer total = acFuncService.selectCount(acFuncWrapper);

            //计算总页数
            int pages = total / size;
            if (total % size != 0) {
                pages++;
            }

            //查询所有功能组的guid
            List<Object> listRootFuncGuid = null;
            //创建一个Pagination
            Pagination pagination = new Pagination();

            //如果当前页码大于等于总页数时,默认查询最后一页
            if(current >= pages) {
                limtSql = "limit " + ((pages - 1) * size) + "," + size;
                Wrapper<AcFunc> acFuncWrapper1 = new EntityWrapper<AcFunc>();
                acFuncWrapper.eq(AcFunc.COLUMN_GUID_FUNC, "0")
                        .orderBy(AcFunc.COLUMN_DISPLAY_ORDER, isAsc)
                        .last(limtSql);
                listRootFuncGuid = acFuncService.selectObjs(acFuncWrapper1);
                pagination.setCurrent(pages);
            } else {
                pagination.setCurrent(current+1);
                listRootFuncGuid = acFuncService.selectObjs(acFuncWrapper);
            }
            pagination.setSize(size);
            pagination.setTotal(total);
            pages = pagination.getPages();

            Map<AcFunc,List<AcFunc>> map = new HashMap<AcFunc,List<AcFunc>>();

            //遍历所有的功能组,查询该功能组和功能组下对应的功能
            for (Object ob : listRootFuncGuid) {
                AcFunc acFunc = acFuncService.selectById((Serializable) ob);
                if ("0".equals(acFunc.getGuid()) ){
                    continue;
                }
                List<AcFunc> list = queryFuncList((String) ob);
                map.put(acFunc,list);
            }

            Map<String,Pagination> map1 = new HashMap<String, Pagination>();
            map1.put("Pagination",pagination);
            //将所有功能组和功能组下的功能set到list中
            listMap.add(map1);
            listMap.add(map);
        }catch (AcManagementException ae) {
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNCGROUP
            );
        }
        return listMap;
    }
}

