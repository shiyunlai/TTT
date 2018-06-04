package org.tis.tools.abf.module.jnl.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.controller.request.LogAbfOperateRequest;
import org.tis.tools.abf.module.jnl.entity.LogAbfOperate;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.jnl.service.ILogAbfOperateService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * Created by chenchao
 * Created on 2018/6/1 18:03
 */
@RestController
@RequestMapping("/logOperate")
@Validated
public class LogAbfOperateController extends BaseController<LogAbfOperate> {

    @Autowired
    private ILogAbfOperateService logAbfOperateService;

    @OperateLog(type = OperateType.QUERY ,desc = "查询某天的日志")
    @ApiOperation(value = "查询某天的日志" , notes = "传入操作时间字段,传入格式yyyyMMdd")
    @PostMapping
    public ResultVO queryOneDay(@RequestBody @Validated SmartPage<LogAbfOperate> page, @RequestBody @Validated
            LogAbfOperateRequest logAbfOperateRequest){
        return ResultVO.success("查询成功",logAbfOperateService.queryByCondition(getPage(page), getCondition(page),logAbfOperateRequest.getOperateTime()));
    }

    @OperateLog(type = OperateType.QUERY ,desc = "查询某天某个操作员的日志")
    @ApiOperation(value = "查询某天某个操作员的日志" , notes = "传入操作员id字段;传入操作时间字段,传入格式yyyyMMdd")
    @PostMapping("/list")
    public ResultVO queryUserByDay(@RequestBody @Validated SmartPage<LogAbfOperate> page,@RequestBody @Validated LogAbfOperateRequest logAbfOperateRequest){
        if (null == logAbfOperateRequest.getUserId() || "".equals(logAbfOperateRequest.getUserId())){
            return ResultVO.error("404","操作员不能为空!");
        }

        return ResultVO.success("查询成功",logAbfOperateService.queryByTimeAndUser(getPage(page), getCondition(page),logAbfOperateRequest.getOperateTime(),logAbfOperateRequest.getUserId()));
    }
}
