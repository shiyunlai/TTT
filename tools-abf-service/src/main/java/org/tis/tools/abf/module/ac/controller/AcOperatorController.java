package org.tis.tools.abf.module.ac.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorUpdateGrop;
import org.tis.tools.abf.module.common.log.OperateLog;
import org.tis.tools.abf.module.common.log.OperateType;
import org.tis.tools.abf.module.common.log.ReturnType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.ac.entity.AcOperator;
import org.tis.tools.abf.module.ac.service.IAcOperatorService;

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
    @OperateLog(
            operateType = OperateType.ADD,  // 操作类型
            operateDesc = "新增操作员", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "userId", // 操作对象标识
            name = "operatorName", // 操作对象名
            keys = {"userId", "operatorName"}) // 操作对象的关键值的键值名
    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated AcOperatorAddRequest request) {
        acOperatorService.addAcOperator(request);
        AcOperator acOperator = new AcOperator();
        EntityWrapper<AcOperator> acOperatorEntityWrapper = new EntityWrapper<>();
        acOperatorEntityWrapper.eq(AcOperator.COLUMN_USER_ID,request.getUserId());
        AcOperator acOperator1 = acOperatorService.selectOne(acOperatorEntityWrapper);
        return ResultVO.success("新增成功！",acOperator1);
    }

    /**
     *
     * @param acOperator
     * @return 修改操作员
     */
    @OperateLog(
            operateType = OperateType.UPDATE,
            operateDesc = "修改操作员",
            retType = ReturnType.Object,
            id = "userId",
            keys = {"userId", "operatorName","operatorStatus","invalDate","authMode"}
    )
    @PutMapping
    public ResultVO update(@RequestBody @Validated({AcOperatorUpdateGrop.class}) AcOperator acOperator) {
        acOperatorService.updateAcOperatorByCondition(acOperator);
        EntityWrapper<AcOperator> acOperatorEntityWrapper = new EntityWrapper<>();
        acOperatorEntityWrapper.eq(AcOperator.COLUMN_USER_ID,acOperator.getUserId());
        AcOperator acOperator1 = acOperatorService.selectOne(acOperatorEntityWrapper);
        return ResultVO.success("修改成功！",acOperator1);
    }

    /**
     *
     * @param id
     * @return 删除操作员结果
     */
    @OperateLog(
            operateType = OperateType.DELETE,
            operateDesc = "删除操作员",
            retType = ReturnType.Object,
            id = "userId",
            keys = {"userId", "operatorName","operatorStatus","invalDate","authMode"}

    )
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperator acOperator = new AcOperator();
        acOperator.setUserId(id);
        EntityWrapper<AcOperator> acOperatorEntityWrapper = new EntityWrapper<>();
        acOperatorEntityWrapper.eq(AcOperator.COLUMN_USER_ID,id);
        AcOperator acOperator1 = acOperatorService.selectOne(acOperatorEntityWrapper);
        acOperatorService.deleteAcOperator(acOperator);
        return ResultVO.success("删除成功",acOperator1);
    }

    /**
     *
     * @param id
     * @return
     */
    @OperateLog(
            operateType = OperateType.QUERY,
            operateDesc = "查询操作员",
            retType = ReturnType.Object,
            id = "userId",
            keys = {"userId", "operatorName","operatorStatus","invalDate","authMode"}
    )
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
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperator> page) {
        return  ResultVO.success("查询成功", acOperatorService.selectPage(getPage(page), getCondition(page)));
    }
    
}

