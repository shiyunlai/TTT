package org.tis.tools.abf.module.ac.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorIdentityRequest;
import org.tis.tools.abf.module.ac.entity.AcOperatorIdentity;
import org.tis.tools.abf.module.ac.service.IAcOperatorIdentityService;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * acOperatorIdentity的Controller类
 * 操作员身份
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acOperatorIdentity")
public class AcOperatorIdentityController extends BaseController<AcOperatorIdentity>  {

    @Autowired
    private IAcOperatorIdentityService acOperatorIdentityService;

    /**
     * 新增操作员身份
     * @param acOperatorIdentityRequest
     * @return
     */
    @OperateLog(type = OperateType.ADD,desc = "新增操作员身份")
    @PostMapping
    public ResultVO add(@RequestBody @Validated AcOperatorIdentityRequest acOperatorIdentityRequest) {
        acOperatorIdentityService.add(acOperatorIdentityRequest);
        return ResultVO.success("新增成功！");
    }

    /**
     * 修改操作员身份
     * @param acOperatorIdentityRequest
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,desc = "修改操作员身份")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcOperatorIdentityRequest acOperatorIdentityRequest) {
        AcOperatorIdentity acOperatorIdentity = acOperatorIdentityService.selectById(acOperatorIdentityRequest.getGuid());
        if (acOperatorIdentity == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorIdentity =acOperatorIdentityService.change(acOperatorIdentityRequest);
        return ResultVO.success("修改成功！",acOperatorIdentity);
    }

    /**
     * 删除操作员身份
     * @param id
     * @return
     */
    @OperateLog(type = OperateType.DELETE,desc = "删除操作员身份")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorIdentity acOperatorIdentity = acOperatorIdentityService.selectById(id);
        if (acOperatorIdentity == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorIdentityService.moveIdentity(id);
        return ResultVO.success("删除成功");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorIdentity acOperatorIdentity = acOperatorIdentityService.selectById(id);
        if (acOperatorIdentity == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperatorIdentity);
    }

    /**
     * 分页查询操作员身份信息
     * @param page
     * @return
     */
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperatorIdentity> page) {
        return  ResultVO.success("查询成功", acOperatorIdentityService.selectPage(getPage(page), getCondition(page)));
    }

    /**
     * 根据操作员id分页查询操作员身份信息
     * @param page
     * @param id
     * @return
     */
    @PostMapping("/queryByOperator/{id}")
    public ResultVO queryByOperator(@RequestBody @Validated SmartPage<AcOperatorIdentity> page,@PathVariable
    @NotBlank(message = "操作员ID不能为空") String id){
        return ResultVO.success("查询成功",acOperatorIdentityService.queryByOperator(getPage(page),getCondition(page),id));
    }
}

