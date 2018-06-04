package org.tis.tools.abf.module.jnl.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.entity.LogAbfOperate;
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

   @PostMapping("/list")
   public ResultVO list(@RequestBody @Validated SmartPage<LogAbfOperate> page){
            return ResultVO.success("查询成功",logAbfOperateService.selectPage(getPage(page),getCondition(page)));
   }

   @GetMapping("/{id}")
   public ResultVO detail(@PathVariable @NotBlank(message = "GUID不能为空") String id){
       LogAbfOperate logAbfOperate = logAbfOperateService.selectById(id);
       if (logAbfOperate == null) {
           return ResultVO.error("404", "找不到对应记录或已经被删除！");
       }

       return ResultVO.success("查询成功",logAbfOperate);
   }

   @PostMapping("/{id}")
   public ResultVO listByUser(@RequestBody @Validated SmartPage<LogAbfOperate> page,@PathVariable @NotBlank(message =
           "操作员ID不能为空") String id){
       return ResultVO.success("查询成功",logAbfOperateService.queryListByUser(getPage(page),getCondition(page),id));
   }

}
