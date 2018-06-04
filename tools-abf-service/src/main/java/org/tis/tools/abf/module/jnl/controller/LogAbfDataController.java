package org.tis.tools.abf.module.jnl.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.LogAbfData;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.jnl.service.ILogAbfDataService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * Created by chenchao
 * Created on 2018/6/1 19:33
 */
@RestController
@RequestMapping("/logData")
@Validated
public class LogAbfDataController extends BaseController<LogAbfData> {

    @Autowired
    private ILogAbfDataService logAbfDataService;

    @OperateLog(type = OperateType.QUERY,desc = "根据操作日志GUID查询")
    @PostMapping("/{id}")
    public ResultVO list(@RequestBody @Validated SmartPage<LogAbfData> page , @PathVariable @NotBlank(message = "操作日志GUID不能为空") String id) {
        return  ResultVO.success("查询成功", logAbfDataService.queryPageById(getPage(page), getCondition(page),id));
    }
}
