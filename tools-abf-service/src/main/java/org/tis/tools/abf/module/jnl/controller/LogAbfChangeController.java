package org.tis.tools.abf.module.jnl.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.LogAbfChange;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.jnl.service.ILogAbfChangeService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * Created by chenchao
 * Created on 2018/6/1 20:33
 */
@RestController
@RequestMapping("/logChange")
@Validated
public class LogAbfChangeController extends BaseController<LogAbfChange> {

    @Autowired
    private ILogAbfChangeService logAbfChangeService;

    @OperateLog(type = OperateType.QUERY,desc = "根据操作日志GUID查询")
    @PostMapping("/{id}")
    public ResultVO page(@RequestBody @Validated SmartPage<LogAbfChange> page , @PathVariable @NotBlank(message = "操作数据GUID不能为空") String id) {
        return  ResultVO.success("查询成功", logAbfChangeService.queryPageById(getPage(page), getCondition(page),id));
    }

    @GetMapping("/{id}")
    public ResultVO detial(@PathVariable @NotBlank(message = "操作日志GUID不能为空") String id){
        LogAbfChange logAbfChange = logAbfChangeService.selectById(id);
        if (logAbfChange == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功",logAbfChange);
    }
}
