package org.tis.tools.abf.module.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tis.tools.abf.module.common.log.OperateLog;
import org.tis.tools.abf.module.common.log.OperateType;
import org.tis.tools.abf.module.common.log.ReturnType;
import org.tis.tools.abf.module.sys.controller.request.SysRunConfigAddRequest;
import org.tis.tools.abf.module.sys.controller.request.SysRunConfigRequest;
import org.tis.tools.abf.module.sys.entity.SysRunConfig;
import org.tis.tools.abf.module.sys.service.ISysRunConfigService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;

import java.util.*;

@RestController
@RequestMapping("/SysConfig")
@Validated
public class SysRunConfigController extends BaseController {
    @Autowired
    private ISysRunConfigService ISysRunConfigService;
    /**
     * 新增系统参数
     *
     * @param request
     * @return
     */
    @OperateLog(
            operateType = OperateType.ADD,  // 操作类型
            operateDesc = "新增系统参数机构", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "guidApp", // 操作对象标识
            name = "groupName", // 操作对象名
            keys = {"guidApp","groupName"}
    )
    @PostMapping("/add_Config")
    public ResultVO add_Config(@RequestBody @Validated SysRunConfigAddRequest request){
        SysRunConfig sysRunConfig = ISysRunConfigService.createSysRunConfig(request.getGuidApp(),request.getGroupName(),
                request.getKeyName(),request.getValueFrom(),request.getValue(),request.getDescription());
        return ResultVO.success("添加成功",sysRunConfig);
    }
    /**
     * 修改系统参数
     *
     * @param request
     * @return
     */
    @OperateLog(
            operateType = OperateType.UPDATE,  // 操作类型
            operateDesc = "修改系统参数机构", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "guid", // 操作对象标识
            name = "groupName", // 操作对象名
            keys = "guidApp"
    )
    @PostMapping("/update_Config")
    public ResultVO update_Config(@RequestBody @Validated SysRunConfigRequest request){
        SysRunConfig sysRunConfig = ISysRunConfigService.editSysRunConfig(request.getGuid(),request.getGuidApp(),request.getGroupName(),
                request.getKeyName(),request.getValueFrom(),request.getValue(),request.getDescription());
        return ResultVO.success("修改成功",sysRunConfig);
    }
    /**
     * 查询系统参数
     *
     * @param id
     * @return
     */
    @OperateLog(
            operateType = OperateType.QUERY,  // 操作类型
            operateDesc = "系统参数机构", // 操作描述
            retType = ReturnType.List, // 返回类型，对象或数组
            id = "guid", // 操作对象标识
            name = "guid", // 操作对象名
            keys = "guid"
    )
    @PostMapping("/query_Config")
    public ResultVO query_Config(@RequestBody @Validated String id){
        List list = ISysRunConfigService.queryAllSysRunConfig(id);
        return ResultVO.success("查询成功",list);
    }
    /**
     * 删除系统参数
     *
     * @param guid
     * @return
     */
    @OperateLog(
            operateType = OperateType.DELETE,  // 操作类型
            operateDesc = "系统参数机构", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "guid", // 操作对象标识
            name = "groupName", // 操作对象名
            keys = {"guidApp","groupName"}
    )
    @PostMapping("/delete_Config")
    public ResultVO delete_Config(@RequestBody @Validated String guid){
        return ResultVO.success("删除成功",ISysRunConfigService.deleteSysRunConfig(guid));
    }
}