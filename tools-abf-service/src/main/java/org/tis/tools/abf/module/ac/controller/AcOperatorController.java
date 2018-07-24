package org.tis.tools.abf.module.ac.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorStatusRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorUpdateGrop;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

/**
 * acOperator的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acOperators")
public class AcOperatorController extends BaseController<AcOperator>  {

    @Autowired
    private IAcOperatorService acOperatorService;

    /**
     * @param request
     *
     * @return 新增操作员
     *
     */
    @OperateLog(type = OperateType.ADD,desc = "新增操作员")
    @PostMapping
    public ResultVO add(@RequestBody @Validated AcOperatorAddRequest request) {
        boolean isexist = acOperatorService.addAcOperator(request);
        if (!isexist){
            return ResultVO.error("404","登录用户名已存在,请重新输入!");
        }
        AcOperator acOperator = new AcOperator();
        EntityWrapper<AcOperator> acOperatorEntityWrapper = new EntityWrapper<>();
        acOperatorEntityWrapper.eq(AcOperator.COLUMN_USER_ID,request.getUserId());
        AcOperator acOperator1 = acOperatorService.selectOne(acOperatorEntityWrapper);
        return ResultVO.success("新增成功！");
    }

    /**
     *
     * @param acOperator
     * @return 修改操作员
     */
    @OperateLog(type = OperateType.UPDATE,desc = "修改操作员")
    @PutMapping
    public ResultVO update(@RequestBody @Validated({AcOperatorUpdateGrop.class}) AcOperator acOperator) {

        boolean isexist = acOperatorService.updateAcOperatorByCondition(acOperator);
        if (!isexist){
            return ResultVO.error("404","登录用户名已存在,请重新输入!");
        }

        AcOperator acOperatorQue1  = acOperatorService.selectById(acOperator.getGuid());
        return ResultVO.success("修改成功！",acOperatorQue1);
    }

    /**
     *
     * @param id
     * @return 删除操作员结果
     */
    @OperateLog(type = OperateType.DELETE,desc = "删除操作员")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperator acOperator  = acOperatorService.selectById(id);
        if (acOperator == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acOperatorService.moveOperator(id);
        return ResultVO.success("删除成功");
    }

    /**
     *  根据ID查询操作员
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        EntityWrapper<AcOperator> acOperatorEntityWrapper = new EntityWrapper<>();
        acOperatorEntityWrapper.eq(AcOperator.COLUMN_USER_ID,id);
        AcOperator acOperator = acOperatorService.selectOne(acOperatorEntityWrapper);
        if (acOperator == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperator);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperator> page) {
        return  ResultVO.success("查询成功", acOperatorService.selectPage(getPage(page), getCondition(page)));
    }

    /**
     * 不分页查询所有操作员
     * @return
     */
    @GetMapping("/queryAllOperator")
    public ResultVO queryAllOperator(){
        return ResultVO.success("查询成功",acOperatorService.queryAllOperator());
    }


    /**
     * 改变操作员状态
     * @param acOperatorStatusRequest
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,desc ="改变操作员状态")
    @PutMapping("/changeStatus")
    public ResultVO changeOperatorStatus(@RequestBody @Validated AcOperatorStatusRequest acOperatorStatusRequest){
        AcOperator acOperatorQue  = acOperatorService.selectById(acOperatorStatusRequest.getGuid());
        if (acOperatorQue == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        acOperatorQue = acOperatorService.changeOperatorStatus(acOperatorStatusRequest);

        return ResultVO.success("修改成功!",acOperatorQue);
    }
}

