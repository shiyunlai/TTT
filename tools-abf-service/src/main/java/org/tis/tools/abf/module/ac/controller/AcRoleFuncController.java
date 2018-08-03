package org.tis.tools.abf.module.ac.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncBatchAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncDeleteRequest;
import org.tis.tools.abf.module.ac.controller.request.AcRoleFuncQueConditionRequest;
import org.tis.tools.abf.module.ac.entity.AcRoleFunc;
import org.tis.tools.abf.module.ac.service.IAcRoleFuncService;
import org.tis.tools.abf.module.ac.service.IAcRoleService;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.model.common.ResultVO;

/**
 * acRoleFunc的Controller类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acRoleFuncs")
public class AcRoleFuncController extends BaseController<AcRoleFunc> {

    @Autowired
    private IAcRoleFuncService acRoleFuncService;

    @Autowired
    private IAcRoleService acRoleService;

    /**
     * @param acRoleFuncAddRequest
     * @return 增加角色功能结果
     * 增加角色功能，角色跟功能连接表中增加一条记录，角色跟功能都是已经存在的
     */
    @OperateLog(type = OperateType.ADD, desc = "增加角色功能")
    @PostMapping
    public ResultVO add(@RequestBody @Validated AcRoleFuncAddRequest acRoleFuncAddRequest) {
        boolean bolen = acRoleFuncService.addRoleFunc(acRoleFuncAddRequest);
        return ResultVO.success("增加成功");
    }

    /**
     * @param acRoleFuncAddRequest
     * @return 修改角色功能结果
     */
    @OperateLog(type = OperateType.UPDATE, desc = "修改角色功能")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcRoleFuncAddRequest acRoleFuncAddRequest) {
        AcRoleFunc acRoleFunc = acRoleFuncService.update(acRoleFuncAddRequest);
        //AcRoleFunc acRoleFunc1 = acRoleFuncService.queryRoleFunByCondition(acRoleFuncAddRequest);
        return ResultVO.success("修改成功！", acRoleFunc);
    }

    /**
     * @param id
     * @return 删除角色功能结果
     */
    @OperateLog(type = OperateType.DELETE, desc = "根据角色代码删除角色功能")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcRoleFunc acRoleFunc = new AcRoleFunc();
        acRoleFunc.setGuidRole(id);
        AcRoleFunc acRoleFunc1 = acRoleFuncService.queryRoleFunByCondition(acRoleFunc);
        acRoleFuncService.deleteById(id);
        return ResultVO.success("删除成功", acRoleFunc1);
    }

    /**
     * @param id
     * @return 查询角色功能结果
     */
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcRoleFunc acRoleFunc = new AcRoleFunc();
        acRoleFunc.setGuidRole(id);
        AcRoleFunc acRoleFunc1 = acRoleFuncService.queryRoleFunByCondition(acRoleFunc);
        if (acRoleFunc1 == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acRoleFunc);
    }

    /**
     * @param page
     * @return 查询角色功能结果
     */
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcRoleFunc> page) {
        return ResultVO.success("查询成功", acRoleFuncService.selectPage(getPage(page), getCondition(page)));
    }


    /**
     * @param acRoleFuncDeleteRequest
     * @return 移除角色功能结果
     * 移除某个角色的某个功能
     */
    @OperateLog(type = OperateType.DELETE, desc = "删除角色的某个功能")
    @DeleteMapping("/removeSomeRoleFunc")
    public ResultVO removeSomeRoleFunc(@RequestBody @Validated AcRoleFuncDeleteRequest acRoleFuncDeleteRequest) {
        AcRoleFunc acRoleFunc = new AcRoleFunc();
        acRoleFunc.setGuidRole(acRoleFuncDeleteRequest.getGuidRole());
        acRoleFunc.setGuidApp(acRoleFuncDeleteRequest.getGuidApp());
        acRoleFunc.setGuidApp(acRoleFuncDeleteRequest.getGuidFunc());
        AcRoleFunc acRoleFunc1 = acRoleFuncService.queryRoleFunByCondition(acRoleFunc);
        boolean bolen = acRoleFuncService.deleteAcRoleFuncByCondition(acRoleFunc);
        return ResultVO.success("删除成功", acRoleFunc1);
    }

    /**
     * 批量新增和删除角色功能
     * @param batchRequest
     * @return
     */
    @OperateLog(type = OperateType.ADD,desc = "批量新增角色功能信息")
    @PostMapping("/batchAdd")
    public ResultVO batchAddClear(@RequestBody @Validated AcRoleFuncBatchAddRequest batchRequest){
        acRoleFuncService.batchAdd(batchRequest);
        return ResultVO.success("处理成功");
    }

    /**
     * 查询所有应用和角色下已有应用
     * @param roleId
     * @return
     */
    @GetMapping("/queryAppByRole/{roleId}")
    public ResultVO queryAppByRole (@PathVariable @NotBlank(message = "角色id不能为空") String roleId){
        return ResultVO.success("查询成功",acRoleFuncService.queryAppByRole(roleId));
    }

    /**
     * 查询应用下所有功能和角色下已有功能
     * @param conditionRequest
     * @return
     */
    @PostMapping("/queryFuncByRole")
    public ResultVO queryFuncByRole (@RequestBody @Validated AcRoleFuncQueConditionRequest conditionRequest){
        return ResultVO.success("查询成功",acRoleFuncService.queryFuncByRole(conditionRequest));
    }

    /**
     * 查询功能下所有行为和角色下已有行为
     * @param conditionRequest
     * @return
     */
    @PostMapping("/queryBehaveByRole")
    public ResultVO queryBehaveByRole (@RequestBody @Validated AcRoleFuncQueConditionRequest conditionRequest){
        return ResultVO.success("查询成功",acRoleFuncService.queryBehaveByRole(conditionRequest));
    }
}

