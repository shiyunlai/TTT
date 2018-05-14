package org.tis.tools.abf.module.sys.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/sysConfig")
@Validated
@Api(description = "系统运行参数表")
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
    @ApiOperation(value = "新增系统参数机构", notes = "实际参数以下面DataType为准")
    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated SysRunConfigAddRequest request){
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
    @ApiOperation(value = "修改系统参数机构", notes = "实际参数以下面DataType为准")
    @PutMapping
    public ResultVO updateConfig(@RequestBody @Validated SysRunConfigRequest request){
        SysRunConfig sysRunConfig = ISysRunConfigService.editSysRunConfig(request.getGuid(),request.getGuidApp(),request.getGroupName(),
                request.getKeyName(),request.getValueFrom(),request.getValue(),request.getDescription());
        return ResultVO.success("修改成功",sysRunConfig);
    }
    /**
     * 查询系统参数
     *
     * @param id
     * @return List
     */
    @OperateLog(
            operateType = OperateType.QUERY,  // 操作类型
            operateDesc = "系统参数机构", // 操作描述
            retType = ReturnType.List, // 返回类型，对象或数组
            id = "guid", // 操作对象标识
            name = "guid", // 操作对象名
            keys = "guid"
    )
    @ApiOperation(value = "查询系统参数机构", notes = "根据guid查询，输入则根据guid查询数据信息未输入则全查")
    @GetMapping("/{id}")
    public ResultVO queryConfig(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id){
        List<SysRunConfig> list = ISysRunConfigService.queryAllSysRunConfig(id);
        return ResultVO.success("查询成功",list);
    }
    /**
     * 删除系统参数
     *
     * @param id
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
    @ApiOperation(value = "删除系统参数机构", notes = "根据guid删除对应数据信息")
    @DeleteMapping("/{id}")
    public ResultVO deleteConfig(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id){
        return ResultVO.success("删除成功",ISysRunConfigService.deleteSysRunConfig(id));
    }
}
