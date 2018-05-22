package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcMenuManagementException;
import org.tis.tools.abf.module.ac.service.IAcMenuService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.entity.AcMenu;
import org.tis.tools.abf.module.ac.dao.AcMenuMapper;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.core.exception.ToolsRuntimeException;
import org.tis.tools.core.exception.i18.ExceptionCodes;
import org.tis.tools.core.utils.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acMenu的Service接口实现类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcMenuServiceImpl extends ServiceImpl<AcMenuMapper, AcMenu> implements IAcMenuService {


    /**
     * 重新排序： 自增
     */
    public static final String REORDER_AUTO_PLUS = "plus";
    /**
     * 重新排序： 自增
     */
    public static final String REORDER_AUTO_MINUS = "minus";
    /**
     * Yes
     */
    public static final String YES = "Y";

    /**
     * No
     */
    public static final String NO = "N";

    @Autowired
    IAcMenuService acMenuService;

    /**
     * 根据父菜单查询出子菜单
     *
     * @param gidParents 父菜单Gid
     */
    @Override
    public List<AcMenu> selectSubMenu(String gidParents) throws AcMenuManagementException {
        try {
            if (StringUtil.isEmpty(gidParents)) {
                throw new AcMenuManagementException(ExceptionCodes.LACK_PARAMETERS_WHEN_UPDATE, wrap("GUID", "AC_MENU"));
            }
            EntityWrapper wrapper = new EntityWrapper();
            wrapper.eq(AcMenu.COLUMN_GUID_PARENTS, gidParents);
            List<AcMenu> lists = selectList(wrapper);
            return lists;
        } catch (ToolsRuntimeException ae) {
            throw ae;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AcMenuManagementException(ExceptionCodes.FAILURE_WHEN_QUERY, wrap(e));
        }
    }

    /**
     * 菜单移动
     *
     * @param targetGuid 目标菜单GUID
     * @param moveGuid   移动的菜单GUID
     * @param order      排序
     * @throws AcMenuManagementException
     */
    @Override
    public AcMenu moveMenu(String targetGuid, String moveGuid, BigDecimal order) throws AcMenuManagementException {
        try {
            // 校验传入参数
            if (StringUtil.isEmpty(targetGuid)) {
                throw new AcMenuManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT,
                        wrap("GUID_GOAL_MENU", "AC_MENU"));
            }
            if (StringUtil.isEmpty(moveGuid)) {
                throw new AcMenuManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT,
                        wrap("GUID_MOVE_MENU", "AC_MENU"));
            }
            if (null == order) {
                throw new AcMenuManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_INSERT,
                        wrap("ORDER", "AC_MENU"));
            }
            // 查询移动的菜单信息及其下属的所有菜单信息
            AcMenu moveMenu = acMenuService.selectById(moveGuid);
            if (moveMenu == null) {
                throw new AcMenuManagementException(ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                        wrap("GUID '" + moveGuid + "' ", "AC_MENU"));
            }

            List<AcMenu> childMenus = acMenuService.selectList(new EntityWrapper<AcMenu>().like(AcMenu.COLUMN_MENU_SEQ, moveGuid));
            // 目标菜单节点
            AcMenu goalMenu = acMenuService.selectById(targetGuid);
            if (goalMenu == null) {
                throw new AcMenuManagementException(ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                        wrap("GUID '" + targetGuid + "' ", "AC_MENU"));
            }
            // 源菜单节点
            String sourceGuid = moveMenu.getGuidParents();
            // 源菜单GUID
            BigDecimal sourceOrder = moveMenu.getDisplayOrder();
            // 源菜单显示顺序
            String sourceSeq = moveMenu.getMenuSeq();

            // 处理移动菜单信息
            moveMenu.setGuidParents(goalMenu.getGuid());
            // 改变父菜单信息
            moveMenu.setMenuSeq(goalMenu.getMenuSeq() + "." + moveGuid);
            // 改变序列
            moveMenu.setDisplayOrder(order);
            // 改变显示顺序
            // 重新排序源菜单下的子菜单自减
            acMenuService.reorderMenu(sourceGuid, sourceOrder, REORDER_AUTO_MINUS);
            // 重新排序目标菜单下的子菜单自增
            acMenuService.reorderMenu(targetGuid, order, REORDER_AUTO_PLUS);
            // 更改移动的重组菜单信息
            acMenuService.updateById(moveMenu);

            // 如果改变了父节点需要同步改变子节点
            if (!StringUtils.equals(moveMenu.getGuidParents(), targetGuid)) {
                // 更改移动菜单下的子菜单
                for (AcMenu childMenu : childMenus) {
                    // 排除当前移动菜单
                    if (!StringUtils.equals(childMenu.getGuid(), moveGuid)) {
                        // 更新菜单序列
                        // update 表名 set 字段名=REPLACE (字段名,'原来的值','要修改的值')
                        String seq = childMenu.getMenuSeq();
                        AcMenu acMenu = new AcMenu();
                        acMenu.setGuid(childMenu.getGuid());
                        acMenu.setMenuSeq(seq.replace(sourceSeq, moveMenu.getMenuSeq()));
                        acMenuService.updateById(acMenu);
                    }
                }
            }
            return moveMenu;
        } catch (ToolsRuntimeException ae) {
            throw ae;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AcMenuManagementException(
                    ExceptionCodes.FAILURE_WHEN_UPDATE, wrap("AC_MENU", e));
        }
    }

    /**
     * <p>
     * 分页查询
     * </p>
     *
     * @param offset 当前页
     * @param limit  每页显示数
     * @return List<AcMenu> 所有子菜单值
     * @throws AcMenuManagementException
     */
    @Override
    public Page<AcMenu> queryPageAcMenu(int offset, int limit) throws AcMenuManagementException {
        if (offset < 0) {
            throw new AcMenuManagementException(ExceptionCodes.LACK_PARAMETERS_WHEN_QUERY, "当前页数不能为负数");
        }
        if (limit < 0) {
            throw new AcMenuManagementException(ExceptionCodes.LACK_PARAMETERS_WHEN_QUERY, "每页显示数不能为负数");
        }
        Page pages = new Page(offset, limit);
        Page<AcMenu> lists = selectPage(pages);
        return lists;
    }

    /**
     * 删除父菜单及其子菜单
     *
     * @param menuGuid 父菜单GUID
     * @return List<AcMenu> 所有子菜单值
     * @throws AcMenuManagementException
     */
    @Override
    public AcMenu deleteAllSubAcMenu(String menuGuid) throws AcMenuManagementException {
        try {
            if (StringUtil.isEmpty(menuGuid)) {
                throw new AcMenuManagementException(ExceptionCodes.LACK_PARAMETERS_WHEN_QUERY, wrap("GUID_MENU"));
            }
            /*查询对应菜单是否存在*/
            AcMenu acMenu = acMenuService.selectById(menuGuid);
            if (acMenu == null) {
                throw new AcMenuManagementException(ExceptionCodes.NOT_FOUND_WHEN_QUERY, wrap(menuGuid, "AC_MENU"));
            }
            /*查询子菜单，一并删除*/
            List<AcMenu> menuList = acMenuService.selectSubMenu(menuGuid);
            String parentGuid = "";
            BigDecimal index = new BigDecimal("0");
            boolean result = false;
            List<String> guidList = new ArrayList<String>(menuList.size());
            for (AcMenu menu : menuList) {
                if (StringUtils.equals(menu.getGuid(), menuGuid)) {
                    parentGuid = menu.getGuidParents();
                    index = menu.getDisplayOrder();
                }
                guidList.add(menu.getGuid());
            }
            if (guidList.size() > 0) {
                result = acMenuService.deleteBatchIds(guidList);
                // 其余兄弟菜单重新排序
                acMenuService.reorderMenu(parentGuid, index, "minus");
            }
            result = acMenuService.delete(new EntityWrapper<AcMenu>().eq(AcMenu.COLUMN_GUID,acMenu.getGuid()));
            return acMenu;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AcMenuManagementException( AcExceptionCodes.FAILURE_WHEN_DELETE_AC_MENU, wrap(e));
        }
    }

    /**
     * 修改菜单
     *
     * @param acMenu 修改菜单对象
     * @return 修改结果
     * @throws AcMenuManagementException
     */
    @Override
    public AcMenu updateAcMenu(AcMenu acMenu) throws AcMenuManagementException {
        try {
            if (null == acMenu) {
                throw new AcMenuManagementException(AcExceptionCodes.OBJECT_IS_NULL, wrap("acMenu"));
            }
            if (StringUtil.isEmpty(acMenu.getGuid())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("GUID"));
            }
            AcMenu menu = acMenuService.selectById(acMenu.getGuid());
            if (menu == null) {
                throw new AcMenuManagementException(ExceptionCodes.NOT_FOUND_WHEN_QUERY, wrap(acMenu.getGuid(), "AC_MENU"));
            }
            if (StringUtil.isEmpty(acMenu.getMenuName())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("MENU_NAME"));
            }
            if (StringUtil.isEmpty(acMenu.getMenuLabel())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("MENU_LABLE"));
            }
            if (StringUtil.isEmpty(acMenu.getMenuCode())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("MENU_CODE"));
            }
            if (StringUtil.isEmpty(acMenu.getIsleaf())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("ISLEAF"));
            }
            // 如果是叶子菜单，功能GUID不能为空
            if (StringUtils.equals(acMenu.getIsleaf(), YES)) {
                if (StringUtil.isEmpty(acMenu.getGuidFunc())) {
                    throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("GUID_FUNC"));
                }
            }
            // 如果为根菜单
            if (StringUtil.isEquals(acMenu.getGuid(), menu.getGuidRoot())) {
                acMenu.setGuidParents(null);
            } else {
                acMenu.setGuidParents(menu.getGuidParents());
            }
            acMenu.setGuidApp(menu.getGuidApp());
            acMenu.setGuidRoot(menu.getGuidRoot());
            acMenuService.updateById(acMenu);
            return acMenu;
        } catch (ToolsRuntimeException ae) {
            throw ae;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AcMenuManagementException(
                    AcExceptionCodes.FAILURE_WHEN_UPDATE_AC_MENU, wrap(e));
        }
    }

    /**
     * 新增子菜单
     *
     * @param acMenu 新增菜单对象
     * @return 新增结果
     * @throws AcMenuManagementException
     */
    @Override
    public AcMenu createChildMenu(AcMenu acMenu) throws AcMenuManagementException {
        try {
            if (null == acMenu) {
                throw new AcMenuManagementException(AcExceptionCodes.OBJECT_IS_NULL, wrap("AcMenu"));
            }
            // 校验传入参数 菜单名称 菜单显示名称 菜单代码  是否叶子菜单  应用GUID 父菜单GUID
            if (StringUtil.isEmpty(acMenu.getGuidApp())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("GUID_APP"));
            }
            if (StringUtil.isEmpty(acMenu.getMenuName())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("MENU_NAME"));
            }
            if (StringUtil.isEmpty(acMenu.getMenuLabel())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("MENU_LABLE"));
            }
            if (StringUtil.isEmpty(acMenu.getMenuCode())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("MENU_CODE"));
            }
            if (StringUtil.isEmpty(acMenu.getIsleaf())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("ISLEAF"));
            }
            if (StringUtil.isEmpty(acMenu.getGuidParents())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("GUID_PARENTS"));
            }
            // 如果是叶子菜单，功能GUID不能为空
            if (StringUtils.equals(acMenu.getIsleaf(), YES)) {
                if (StringUtil.isEmpty(acMenu.getIsleaf())) {
                    throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("GUID_FUNC"));
                }
            }
            AcMenu parMenu = acMenuService.selectById(acMenu.getGuidParents());
            /** 查询是否存在父菜单 */
            if (parMenu == null) {
                throw new AcMenuManagementException(AcExceptionCodes.MENU_NOT_EXIST_BY_GUID
                        , wrap(acMenu.getGuidParents()));
            }
            /** 添加菜单GUID和序列*/
            acMenu.setGuidRoot(parMenu.getGuidRoot());
            acMenu.setDisplayOrder(new BigDecimal(acMenuService.selectCount(
                    new EntityWrapper<AcMenu>()
                            .eq(AcMenu.COLUMN_GUID_PARENTS, acMenu.getGuidParents()))));
            acMenuService.insert(acMenu);
            return acMenu;
        } catch (ToolsRuntimeException ae) {
            throw ae;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AcMenuManagementException(
                    ExceptionCodes.FAILURE_WHEN_INSERT,
                    wrap("AC_MENU", e));
        }
    }

    /**
     * 新增根菜单
     *
     * @param acMenu 新增菜单对象
     * @return 新增结果
     * @throws AcMenuManagementException
     */
    @Override
    public AcMenu createRootMenu(AcMenu acMenu) throws AcMenuManagementException {
        try {
            if (null == acMenu) {
                throw new AcMenuManagementException(AcExceptionCodes.OBJECT_IS_NULL, wrap("AcMenu"));
            }
            // 校验传入参数 菜单名称 菜单显示名称 菜单代码  是否叶子菜单  应用GUID
            if (StringUtil.isEmpty(acMenu.getGuidApp())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("GUID_APP"));
            }
            if (StringUtil.isEmpty(acMenu.getMenuName())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("MENU_NAME"));
            }
            if (StringUtil.isEmpty(acMenu.getMenuLabel())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("MENU_LABLE"));
            }
            if (StringUtil.isEmpty(acMenu.getMenuCode())) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("MENU_CODE"));
            }
            List<AcMenu> acMenus = queryRootMenu(acMenu.getGuidApp());
            if (acMenus.size() > 0) {
                throw new AcMenuManagementException(
                        AcExceptionCodes.CURRENT_APP_ALREADY_HAVE_ROOT_MENU);
            }
//            acMenu.setGuid(GUID.menu());
//            acMenu.setMenuSeq(acMenu.getGuid());
            acMenu.setGuidParents(null);
            acMenu.setIsleaf(NO);
            acMenu.setGuidRoot(acMenu.getGuid());
            acMenu.setDisplayOrder(new BigDecimal("0"));
            acMenuService.insert(acMenu);
            return acMenu;
        } catch (ToolsRuntimeException ae) {
            throw ae;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AcMenuManagementException(
                    ExceptionCodes.FAILURE_WHEN_INSERT,
                    wrap("AC_MENU", e));
        }
    }

    /**
     * 查询应用下根菜单
     *
     * @param GUID_APP 应用id
     * @return 新增结果
     * @throws AcMenuManagementException
     */
    @Override
    public List<AcMenu> queryRootMenu(String GUID_APP) throws AcMenuManagementException {
        try {
            // 校验传入参数
            if (StringUtil.isEmpty(GUID_APP)) {
                throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("GUID_APP"));
            }
            //查询应用下父节点字段为空的菜单，即为根菜单
            EntityWrapper wrapper = new EntityWrapper();
            wrapper.eq(AcMenu.COLUMN_GUID_APP, GUID_APP).or(AcMenu.COLUMN_GUID_PARENTS,"")
                    .isNull(AcMenu.COLUMN_GUID_PARENTS);
            return acMenuService.selectList(wrapper);
        } catch (ToolsRuntimeException ae) {
            throw ae;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AcMenuManagementException(
                    AcExceptionCodes.FAILURE_WHEN_QUERY_ROOT_MENU,
                    wrap(e));
        }
    }

    /**
     * 重新排序菜单下的子菜单
     * @param identityGuid 目标菜单GUID
     * @param index 起始位置
     */
    @Override
    public void reorderMenu(String identityGuid, BigDecimal index , String flag) {
        if(StringUtil.isEmpty(identityGuid)){
            throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("GUID"));
        }
        if(StringUtil.isEmpty(index.toString())){
            throw new AcMenuManagementException(AcExceptionCodes.PARMS_NOT_ALLOW_EMPTY, wrap("GUID"));
        }
        AcMenu acMenu = acMenuService.selectById(identityGuid);

        BigDecimal oreder = acMenu.getDisplayOrder();
        BigDecimal ore = new BigDecimal("1");
        if(flag.equals("plus")){
            oreder = oreder.add(ore);
        }else{
            oreder = oreder.subtract(ore);
        }
        acMenu.setDisplayOrder(oreder);
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq(AcMenu.COLUMN_GUID_PARENTS ,identityGuid)
                .where(AcMenu.COLUMN_GUID_PARENTS + " >= " + index);
        boolean result =  acMenuService.update(acMenu ,wrapper);
    }
}

