package org.tis.tools.abf.module.ac.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.tis.tools.abf.module.ac.controller.request.AcRoleAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleUpdateValidateGrop;
import org.tis.tools.abf.module.ac.entity.AcRole;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.tis.tools.abf.module.ac.service.IAcRoleFuncService;
import org.tis.tools.abf.module.common.log.OperateLog;
import org.tis.tools.abf.module.common.log.OperateType;
import org.tis.tools.abf.module.common.log.ReturnType;
import org.tis.tools.core.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.service.IAcRoleService;
import org.tis.tools.core.web.vo.SmartPage;

import java.util.List;

/**
 * acRole的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acRoles")
public class AcRoleController extends BaseController<AcRole>  {

    @Autowired
    private IAcRoleService acRoleService;

    @Autowired
    IAcRoleFuncService acRoleFuncService;


/**
 * @param page
 * @return 分页内容
 *
 */

    @PostMapping("/list")
    public ResultVO queryRoleWithPage(@RequestBody @Validated SmartPage<AcRole> page) {
        Page page1  =  acRoleService.selectPage(getPage(page),getCondition(page));
        return  ResultVO.success("查询成功", page1);
    }

    /**
     * @param roleCode
     * @return 角色信息
     * 根据角色代码查询角色信息
     *
     */
    @GetMapping("/{roleCode}")
    public ResultVO selectByRoleCode(@PathVariable @NotBlank(message = "roleCode不能为空") String roleCode) {
        AcRole acRole = new AcRole();
        acRole.setRoleCode(roleCode);
        AcRole acRole1 = acRoleService.queryByCondition(acRole);
        if (acRoleService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acRole1);
    }


    /**
     * @param request
     * @return 新增角色
     * 新增角色信息
     */
    @OperateLog(
            operateType = OperateType.ADD,  // 操作类型
            operateDesc = "新增角色", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "roleCode", // 操作对象标识
            name = "roleName", // 操作对象名
            keys = {"roleCode", "roleName"}) // 操作对象的关键值的键值名
    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcRoleAddRequest request) {
        boolean bolen = acRoleService.createAcRole(request.getRoleCode(),request.getRoleName(),request.getEnabled(),request.getRoleDesc());
        AcRole acRole = new AcRole();
        acRole.setRoleCode(request.getRoleCode());
        AcRole acRole1 = acRoleService.queryByCondition(acRole);
        return ResultVO.success("新增成功",acRole1);
    }


    /**
     * @param acRole
     * @return 修改角色结果
     * 根据角色ID修改角色信息
     */
    @OperateLog(
            operateType = OperateType.UPDATE,  // 操作类型
            operateDesc = "根据角色ID修改角色", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "roleCode", // 操作对象标识
            name = "roleName", // 操作对象名
            keys = {"roleCode", "roleName","enabled","roleDesc"}) // 操作对象的关键值的键值名
    @PutMapping("/update")
    public ResultVO update(@RequestBody @Validated({AcRoleUpdateValidateGrop.class}) AcRole acRole) {
        boolean  bolen = acRoleService.updateAcRole(acRole);
        return ResultVO.success("修改成功！");

    }

    /**
     * @param roleCode
     * @return 删除角色结果
     * 根据角色代码修改角色信息
     */
    @OperateLog(
            operateType = OperateType.DELETE,  // 操作类型
            operateDesc = "根据角色代码删除角色", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "roleCode",
            name = "roleName",
            keys = {"roleCode", "roleName","enabled","roleDesc"})
    @DeleteMapping("/{roleCode}")
    public ResultVO deleteRoleByRoleCode(@PathVariable @NotBlank(message = "roleCode不能为空") String roleCode) {
        AcRole acRole = new AcRole();
        acRole.setRoleCode(roleCode);
        AcRole acRole1 = acRoleService.queryByCondition(acRole);
        boolean bolen = acRoleService.deleteByRoleCode(roleCode);
        return ResultVO.success("删除成功",acRole1);
    }





}

