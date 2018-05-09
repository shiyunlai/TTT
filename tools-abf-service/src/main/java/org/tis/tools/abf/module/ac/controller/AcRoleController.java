package org.tis.tools.abf.module.ac.controller;

import org.apache.commons.lang.StringUtils;
import org.tis.tools.abf.module.ac.controller.request.AcRoleAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncAddRequest;
import org.tis.tools.abf.module.ac.entity.AcRole;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.tis.tools.abf.module.ac.exception.AcRoleManagementException;
import org.tis.tools.abf.module.common.log.OperateLog;
import org.tis.tools.abf.module.common.log.OperateType;
import org.tis.tools.abf.module.common.log.ReturnType;
import org.tis.tools.core.exception.i18.ExceptionCodes;
import org.tis.tools.core.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.service.IAcRoleService;

import java.util.List;

/**
 * acRole的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acRole")
public class AcRoleController extends BaseController<AcRole>  {

    @Autowired
    private IAcRoleService acRoleService;


    @OperateLog(
            operateType = OperateType.QUERY,  // 操作类型
            operateDesc = "查询所有角色", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "roleCode", // 操作对象标识
            name = "roleName") // 操作对象名
    @PostMapping("/queryAllRole")
    public ResultVO queryAllRole() {
        List<AcRole> acRoleList =  acRoleService.queryAllRole();
        return  ResultVO.success("查询成功", acRoleList);
    }

    @OperateLog(
            operateType = OperateType.QUERY,  // 操作类型
            operateDesc = "根据角色代码查询角色", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "roleCode", // 操作对象标识
            name = "roleName", // 操作对象名
            keys = {"roleCode", "roleName"}) // 操作对象的关键值的键值名
    @GetMapping("/selectByRoleCode")
    public ResultVO selectByRoleCode(@PathVariable @NotBlank(message = "roleCode不能为空") String roleCode) {
        AcRole acRole = acRoleService.queryByCode(roleCode);
        if (acRoleService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acRole);
    }

    @OperateLog(
            operateType = OperateType.QUERY,  // 操作类型
            operateDesc = "根据角色名字查询角色", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "roleCode", // 操作对象标识
            name = "roleName")
    @GetMapping("/selectByRoleName")
    public ResultVO selectByRoleName(String roleName){
        if(StringUtils.isNotBlank(roleName)){
            AcRole acRole = acRoleService.queryByName(roleName);
            return ResultVO.success("查询成功",acRole);
        }else{
            return ResultVO.error("角色名字不能为空");
        }

    }

    @OperateLog(
            operateType = OperateType.QUERY,  // 操作类型
            operateDesc = "根据角色ID查询角色", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "guid")
    @GetMapping("/selectByRoleGuid")
    public ResultVO selectByRoleGuid(String guid){
        if(StringUtils.isNotBlank(guid)){
            AcRole acRole = acRoleService.queryByGuid(guid);
            return ResultVO.success("查询成功",acRole);
        }else{
            return ResultVO.error("角色Guid不能为空");
        }

    }

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
        return ResultVO.success("新增成功",bolen);
    }


    @OperateLog(
            operateType = OperateType.UPDATE,  // 操作类型
            operateDesc = "根据角色ID修改角色", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "roleCode", // 操作对象标识
            name = "roleName", // 操作对象名
            keys = {"roleCode", "roleName"}) // 操作对象的关键值的键值名
    @PutMapping("/update")
    public ResultVO update(@RequestBody @Validated AcRole acRole) {
        boolean bolen = false;
        if(StringUtils.isNotBlank(acRole.getGuid())){
            bolen = acRoleService.updateAcRole(acRole);
            return ResultVO.success("修改成功！",bolen);
        }else{
            return ResultVO.error("GUID不能为空");
        }

    }


    @OperateLog(
            operateType = OperateType.DELETE,  // 操作类型
            operateDesc = "根据角色代码删除角色", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "roleCode")
    @DeleteMapping("/deleteRoleByRoleCode")
    public ResultVO deleteRoleByRoleCode(@PathVariable @NotBlank(message = "roleCode不能为空") String roleCode) {
        boolean bolen = acRoleService.deleteByRoleCode(roleCode);
        return ResultVO.success("删除成功",bolen);
    }


    @OperateLog(
            operateType = OperateType.ADD,  // 操作类型
            operateDesc = "增加角色功能", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "roleCode")
    @PutMapping("/addRoleFunc")
    public ResultVO addRoleFunc(@RequestBody @Validated AcRoleFuncAddRequest acRoleFuncAddRequest){
        AcRoleFunc acRoleFunc = new AcRoleFunc();
        acRoleFunc.setGuidApp(acRoleFuncAddRequest.getGuidApp());
        acRoleFunc.setGuidFunc(acRoleFuncAddRequest.getGuidFunc());
        acRoleFunc.setGuidRole(acRoleFuncAddRequest.getGuidRole());
        boolean bolen = acRoleService.addRoleFunc(acRoleFunc);
        return ResultVO.success("增加成功",bolen);
    }


    @OperateLog(
            operateType = OperateType.DELETE,
            operateDesc = "删除角色的某个功能",
            retType = ReturnType.Object,
            id = "roleCode"
    )
    @DeleteMapping("/removeSomeRoleFunc")
    public ResultVO removeSomeRoleFunc(String guidRole,String guidFunc){
        if(StringUtils.isBlank(guidRole)){
            throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE,"角色GUID不能为空");
        }
        if(StringUtils.isBlank(guidFunc)){
            throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE,"功能GUID不能为空");
        }
        boolean bolen = acRoleService.removeRoleFunc(guidRole,guidFunc) ;
        return ResultVO.success("删除成功",bolen);
    }


    @OperateLog(
            operateType = OperateType.DELETE,
            operateDesc = "删除角色的某个应用的所有功能",
            retType = ReturnType.Object,
            id = "roleCode"
    )
    @DeleteMapping("/removeRoleFuncByApp")
    public ResultVO removeRoleFuncByApp(String guidRole,String guidApp){
        if(StringUtils.isBlank(guidRole)){
            throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE,"角色GUID不能为空");
        }
        if(StringUtils.isBlank(guidApp)){
            throw new AcRoleManagementException(ExceptionCodes.NOT_ALLOW_NULL_WHEN_DELETE,"功能GUID不能为空");
        }
        boolean bolen = acRoleService.removeRoleFuncWithApp(guidRole,guidApp) ;
        return ResultVO.success("删除成功",bolen);
    }


}

