package org.tis.tools.abf.module.ac.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.validation.annotation.Validated;
import org.tis.tools.abf.module.ac.controller.request.AcOPeratorRoleUpdateGrop;
import org.tis.tools.abf.module.ac.controller.request.AcOperatorRoleAddGrop;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.abf.module.ac.service.IAcOperatorRoleService;
import org.tis.tools.core.web.vo.SmartPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.module.ac.entity.AcOperatorRole;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;

/**
 * acOperatorRole的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acOperatorRole")
public class AcOperatorRoleController extends BaseController<AcOperatorRole>  {

    @Autowired
    private IAcOperatorRoleService acOperatorRoleService;

    @OperateLog(type = OperateType.ADD,desc = "增加操作员与权限集（角色）对应关系")
    @PostMapping("/add")
    public ResultVO add(@RequestBody @Validated({AcOperatorRoleAddGrop.class}) AcOperatorRole acOperatorRole) {
        acOperatorRoleService.insert(acOperatorRole);
        EntityWrapper<AcOperatorRole> acOperatorRoleEntityWrapper = new EntityWrapper<>();
        acOperatorRoleEntityWrapper.eq(AcOperatorRole.COLUMN_GUID_OPERATOR,acOperatorRole.getGuidOperator());
        acOperatorRoleEntityWrapper.eq(AcOperatorRole.COLUMN_GUID_ROLE,acOperatorRole.getGuidRole());
        AcOperatorRole acOperatorRole1 = acOperatorRoleService.selectOne(acOperatorRoleEntityWrapper);
        return ResultVO.success("新增成功！",acOperatorRole1);
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改操作员与权限集（角色）对应关系")
    @PutMapping
    public ResultVO update(@RequestBody @Validated({AcOPeratorRoleUpdateGrop.class}) AcOperatorRole acOperatorRole) {
        acOperatorRoleService.updateById(acOperatorRole);
        AcOperatorRole acOperatorRole1 = acOperatorRoleService.selectById(acOperatorRole.getGuid());
        return ResultVO.success("修改成功！",acOperatorRole1);
    }

    @OperateLog(type = OperateType.DELETE,desc = "删除操作员与权限集（角色）对应关系")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorRole acOperatorRole = acOperatorRoleService.selectById(id);
        acOperatorRoleService.deleteById(id);
        return ResultVO.success("删除成功",acOperatorRole);
    }
    
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcOperatorRole acOperatorRole = acOperatorRoleService.selectById(id);
        if (acOperatorRoleService == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acOperatorRole);
    }
    
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcOperatorRole> page) {
        return  ResultVO.success("查询成功", acOperatorRoleService.selectPage(getPage(page), getCondition(page)));
    }
    
}

