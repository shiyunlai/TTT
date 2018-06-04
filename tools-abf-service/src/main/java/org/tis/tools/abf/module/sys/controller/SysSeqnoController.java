package org.tis.tools.abf.module.sys.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.sys.controller.request.SysSeqnoResetRequest;
import org.tis.tools.abf.module.sys.entity.SysSeqno;
import org.tis.tools.abf.module.sys.service.ISysSeqnoService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * sysSeqno的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/sysSeqno")
@Validated
public class SysSeqnoController extends BaseController<SysSeqno>  {

    @Autowired
    private ISysSeqnoService sysSeqnoService;

    @OperateLog(type = OperateType.ADD, desc = "新增序号资源")
    @PostMapping
    public ResultVO add(@RequestBody @Validated SysSeqno sysSeqno) {
        sysSeqnoService.insert(sysSeqno);
        return ResultVO.success("添加成功");
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改序号资源")
    @PutMapping
    public ResultVO update(@RequestBody @Validated SysSeqnoResetRequest sysSeqnoResetRequest) {
        SysSeqno sysSeqno = sysSeqnoService.selectById(sysSeqnoResetRequest.getSeqKey());
        if (sysSeqno == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        SysSeqno sysSeqnoChange = sysSeqnoService.change(sysSeqnoResetRequest);
        return ResultVO.success("修改成功！",sysSeqnoChange);
    }

    @OperateLog(type = OperateType.DELETE,desc = "删除序号资源")
    @DeleteMapping("/{seqKey}")
    public ResultVO delete(@PathVariable @NotBlank(message = "seqKey不能为空") String seqKey) {
        SysSeqno sysSeqno = sysSeqnoService.selectById(seqKey);
        if (sysSeqno == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        sysSeqnoService.deleteById(seqKey);
        return ResultVO.success("删除成功");
    }
    
    @GetMapping("/{seqKey}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String seqKey) {
        SysSeqno sysSeqno = sysSeqnoService.selectById(seqKey);
        if (sysSeqno == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", sysSeqno);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<SysSeqno> page) {
        return  ResultVO.success("查询成功", sysSeqnoService.selectPage(getPage(page), getCondition(page)));
    }
}

