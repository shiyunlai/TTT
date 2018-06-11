package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorMenuRequest;
import org.tis.tools.abf.module.ac.dao.AcOperatorMenuMapper;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.AcFunc;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.entity.AcOperatorMenu;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.ac.service.IAcFuncService;
import org.tis.tools.abf.module.ac.service.IAcOperatorMenuService;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;

import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acOperatorMenu的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcOperatorMenuServiceImpl extends ServiceImpl<AcOperatorMenuMapper, AcOperatorMenu> implements IAcOperatorMenuService {

    @Autowired
    private IAcOperatorService acOperatorService;

    @Autowired
    private IAcAppService acAppService;

    @Autowired
    private IAcFuncService acFuncService;

    @Override
    public void addRoot(AcOperatorMenuRequest acOperatorMenuRequest) throws AcManagementException {

        //判断操作员ID对应的操作员是否存在
        String operatorGuid = acOperatorMenuRequest.getGuidOperator();
        AcOperator acOperator = acOperatorService.selectById(operatorGuid);
        if (null == acOperator){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("操作员ID对应的操作员不存在",
                    operatorGuid));
        }

        //判断应用ID对应的应用是否存在
        String appGuid =acOperatorMenuRequest.getGuidApp();
        if (!"".equals(appGuid) || null != appGuid){
            AcApp acApp = acAppService.selectById(appGuid);
            if (null == acApp){
                throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APP,wrap("应用ID对应的应用不存在",
                        appGuid));
            }
        }

        //判断功能ID对应的功能是否存在
        String funcGuid = acOperatorMenuRequest.getGuidFunc();
        if (!"".equals(funcGuid) || null != funcGuid){
            AcFunc acFunc = acFuncService.selectById(funcGuid);
            if (acFunc == null){
                throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNC,wrap("功能ID对应的功能不存在",
                        funcGuid));
            }
        }

        AcOperatorMenu acOperatorMenu = new AcOperatorMenu();
        //根节点的父节点为空
        acOperatorMenu.setGuidParents("");
        acOperatorMenu.setGuidRoot("");

        //收集参数
        acOperatorMenu.setGuidFunc(funcGuid);
        acOperatorMenu.setGuidApp(appGuid);
        acOperatorMenu.setGuidOperator(operatorGuid);
        acOperatorMenu.setMenuName(acOperatorMenuRequest.getMenuName());
        acOperatorMenu.setMenuLabel(acOperatorMenuRequest.getMenuLabel());
        acOperatorMenu.setIsleaf(acOperatorMenuRequest.getIsleaf());
        acOperatorMenu.setUiEntry(acOperatorMenuRequest.getUiEntry());
        acOperatorMenu.setMenuLevel(acOperatorMenuRequest.getMenuLevel());
        acOperatorMenu.setDisplayOrder(acOperatorMenuRequest.getDisplayOrder());
        acOperatorMenu.setImagePath(acOperatorMenuRequest.getImagePath());
        acOperatorMenu.setExpandPath(acOperatorMenuRequest.getExpandPath());
        acOperatorMenu.setMenuSeq(acOperatorMenuRequest.getMenuSeq());
        acOperatorMenu.setOpenMode(acOperatorMenuRequest.getOpenMode());
        acOperatorMenu.setSubCount(acOperatorMenuRequest.getSubCount());

        insert(acOperatorMenu);
    }

    @Override
    public void addChild(AcOperatorMenuRequest acOperatorMenuRequest) throws AcManagementException {

        //判断操作员ID对应的操作员是否存在
        String operatorGuid = acOperatorMenuRequest.getGuidOperator();
        AcOperator acOperator = acOperatorService.selectById(operatorGuid);
        if (null == acOperator){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("操作员ID对应的操作员不存在",
                    operatorGuid));
        }

        //判断应用ID对应的应用是否存在
        String appGuid =acOperatorMenuRequest.getGuidApp();
        if (!"".equals(appGuid) || null != appGuid){
            AcApp acApp = acAppService.selectById(appGuid);
            if (null == acApp){
                throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APP,wrap("应用ID对应的应用不存在",
                        appGuid));
            }
        }

        //判断功能ID对应的功能是否存在
        String funcGuid = acOperatorMenuRequest.getGuidFunc();
        if (!"".equals(funcGuid) || null != funcGuid){
            AcFunc acFunc = acFuncService.selectById(funcGuid);
            if (acFunc == null){
                throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNC,wrap("功能ID对应的功能不存在",
                        funcGuid));
            }
        }

        AcOperatorMenu acOperatorMenu = new AcOperatorMenu();

        //收集参数
        acOperatorMenu.setGuidRoot(acOperatorMenuRequest.getGuidRoot());
        acOperatorMenu.setGuidParents(acOperatorMenuRequest.getGuidParents());
        acOperatorMenu.setGuidFunc(funcGuid);
        acOperatorMenu.setGuidApp(appGuid);
        acOperatorMenu.setGuidOperator(operatorGuid);
        acOperatorMenu.setMenuName(acOperatorMenuRequest.getMenuName());
        acOperatorMenu.setMenuLabel(acOperatorMenuRequest.getMenuLabel());
        acOperatorMenu.setIsleaf(acOperatorMenuRequest.getIsleaf());
        acOperatorMenu.setUiEntry(acOperatorMenuRequest.getUiEntry());
        acOperatorMenu.setMenuLevel(acOperatorMenuRequest.getMenuLevel());
        acOperatorMenu.setDisplayOrder(acOperatorMenuRequest.getDisplayOrder());
        acOperatorMenu.setImagePath(acOperatorMenuRequest.getImagePath());
        acOperatorMenu.setExpandPath(acOperatorMenuRequest.getExpandPath());
        acOperatorMenu.setMenuSeq(acOperatorMenuRequest.getMenuSeq());
        acOperatorMenu.setOpenMode(acOperatorMenuRequest.getOpenMode());
        acOperatorMenu.setSubCount(acOperatorMenuRequest.getSubCount());

        insert(acOperatorMenu);
    }

    @Override
    public AcOperatorMenu change(AcOperatorMenuRequest acOperatorMenuRequest) throws AcManagementException {

        //判断操作员ID对应的操作员是否存在
        String operatorGuid = acOperatorMenuRequest.getGuidOperator();
        AcOperator acOperator = acOperatorService.selectById(operatorGuid);
        if (null == acOperator){
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_QUERY_AC_OPERATOR,wrap("操作员ID对应的操作员不存在",
                    operatorGuid));
        }

        //判断应用ID对应的应用是否存在
        String appGuid =acOperatorMenuRequest.getGuidApp();
        if (!"".equals(appGuid) || null != appGuid){
            AcApp acApp = acAppService.selectById(appGuid);
            if (null == acApp){
                throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_APP,wrap("应用ID对应的应用不存在",
                        appGuid));
            }
        }

        //判断功能ID对应的功能是否存在
        String funcGuid = acOperatorMenuRequest.getGuidFunc();
        if (!"".equals(funcGuid) || null != funcGuid){
            AcFunc acFunc = acFuncService.selectById(funcGuid);
            if (acFunc == null){
                throw new AcManagementException(AcExceptionCodes.FAILURE_WHRN_QUERY_AC_FUNC,wrap("功能ID对应的功能不存在",
                        funcGuid));
            }
        }

        AcOperatorMenu acOperatorMenu = new AcOperatorMenu();

        //收集参数
        acOperatorMenu.setGuid(acOperatorMenuRequest.getGuid());
        acOperatorMenu.setGuidRoot(acOperatorMenuRequest.getGuidRoot());
        acOperatorMenu.setGuidParents(acOperatorMenuRequest.getGuidParents());
        acOperatorMenu.setGuidFunc(funcGuid);
        acOperatorMenu.setGuidApp(appGuid);
        acOperatorMenu.setGuidOperator(operatorGuid);
        acOperatorMenu.setMenuName(acOperatorMenuRequest.getMenuName());
        acOperatorMenu.setMenuLabel(acOperatorMenuRequest.getMenuLabel());
        acOperatorMenu.setIsleaf(acOperatorMenuRequest.getIsleaf());
        acOperatorMenu.setUiEntry(acOperatorMenuRequest.getUiEntry());
        acOperatorMenu.setMenuLevel(acOperatorMenuRequest.getMenuLevel());
        acOperatorMenu.setDisplayOrder(acOperatorMenuRequest.getDisplayOrder());
        acOperatorMenu.setImagePath(acOperatorMenuRequest.getImagePath());
        acOperatorMenu.setExpandPath(acOperatorMenuRequest.getExpandPath());
        acOperatorMenu.setMenuSeq(acOperatorMenuRequest.getMenuSeq());
        acOperatorMenu.setOpenMode(acOperatorMenuRequest.getOpenMode());
        acOperatorMenu.setSubCount(acOperatorMenuRequest.getSubCount());

        updateById(acOperatorMenu);

        return acOperatorMenu;
    }

    @Override
    public void deleteAllOperatorMenu(String id) throws AcManagementException {

        try {
            //删除父节点下的所有子节点
            deleteAllChild(id);

        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(AcExceptionCodes.FAILURE_WHEN_DELETE_OPERATORMENU,wrap(e.getMessage()));
        }
    }

    //删除父节点下的所有子节点
    public void deleteAllChild(String id){
        //删除父节点下的所有子节点
        Wrapper<AcOperatorMenu> wrapper = new EntityWrapper<AcOperatorMenu>();
        wrapper.eq(AcOperatorMenu.COLUMN_GUID_PARENTS,id);

        //获取子节点列表
        List<AcOperatorMenu> lists = selectList(wrapper);

        if (0 == lists.size() || null == lists){
            deleteById(id);
        }else {
            for (AcOperatorMenu acOperatorMenu :lists){
                deleteAllChild(acOperatorMenu.getGuid());
            }
            deleteById(id);
        }
    }

    @Override
    public Page<AcOperatorMenu> queryByOperatorId(Page<AcOperatorMenu> page, Wrapper<AcOperatorMenu> wrapper, String
            id) throws AcManagementException {

        if(null == wrapper){
            wrapper = new EntityWrapper<AcOperatorMenu>();
        }
        wrapper.eq(AcOperatorMenu.COLUMN_GUID_OPERATOR,id);

        Page<AcOperatorMenu> pages = selectPage(page,wrapper);

        return pages;
    }
}

