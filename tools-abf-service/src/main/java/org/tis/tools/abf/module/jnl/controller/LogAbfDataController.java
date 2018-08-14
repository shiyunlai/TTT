package org.tis.tools.abf.module.jnl.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.entity.LogAbfData;
import org.tis.tools.abf.module.jnl.service.ILogAbfDataService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.model.common.ResultVO;
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

    @PostMapping("/{id}")
    public ResultVO list(@RequestBody @Validated SmartPage<LogAbfData> page , @PathVariable @NotBlank(message = "操作日志GUID不能为空") String id) {
        return  ResultVO.success("查询成功", logAbfDataService.queryPageById(getPage(page), getCondition(page),id));
    }

    @GetMapping("/{id}")
    public ResultVO detial(@PathVariable @NotBlank(message = "操作日志GUID不能为空") String id){
        LogAbfData logAbfData = logAbfDataService.selectById(id);
        if (logAbfData == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功",logAbfData);
    }

    @PostMapping("/list/{dataGuid}")
    public ResultVO listByDataId(@RequestBody @Validated SmartPage<LogAbfData> page ,@PathVariable @NotBlank(message = "操作日志GUID不能为空") String dataGuid){
        return ResultVO.success("查询成功",logAbfDataService.queryByDataId(getPage(page), getCondition(page),dataGuid));
    }

    /**
     * 查询操作数据记录的详细信息
     * @param id
     * @return
     */
    @GetMapping("detialMessage/{id}")
    public ResultVO detialMessage(@PathVariable @NotBlank(message = "操作日志GUID不能为空") String id){
        return ResultVO.success("查询成功",logAbfDataService.queryDetialMessage(id));
    }

}
