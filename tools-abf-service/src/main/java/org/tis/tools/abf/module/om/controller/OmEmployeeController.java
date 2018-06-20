package org.tis.tools.abf.module.om.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeAddRequest;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeByOrgAndPositionRequest;
import org.tis.tools.abf.module.om.controller.request.OmEmployeeUpdateRequest;
import org.tis.tools.abf.module.om.entity.OmEmployee;
import org.tis.tools.abf.module.om.entity.enums.OmEmployeeStatus;
import org.tis.tools.abf.module.om.service.IOmEmployeeService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.PageVO;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

import java.util.List;

/**
 * omEmployee的Controller类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/omEmployee")
public class OmEmployeeController extends BaseController<OmEmployee>  {

    @Autowired
    private IOmEmployeeService omEmployeeService;

    @OperateLog(type = OperateType.ADD,desc = "新增员工")
    @PostMapping
    public ResultVO add(@RequestBody @Validated OmEmployeeAddRequest omEmployeeAddRequest) {
        boolean isexist = omEmployeeService.add(omEmployeeAddRequest);
        if (!isexist){
            return ResultVO.error("404","员工代码已存在,请重新输入!");
        }
        return ResultVO.success("新增成功！");
    }

    @OperateLog(type = OperateType.UPDATE,desc = "修改员工")
    @PutMapping
    public ResultVO update(@RequestBody @Validated OmEmployeeUpdateRequest omEmployeeUpdateRequest) {
        OmEmployee omEmployee = omEmployeeService.selectById(omEmployeeUpdateRequest.getGuid());
        if (omEmployee == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        boolean isexist = omEmployeeService.change(omEmployeeUpdateRequest);
        if (!isexist){
            return ResultVO.error("404","员工代码已存在,请重新输入!");
        }

        OmEmployee omEmployeeQuery = omEmployeeService.selectById(omEmployeeUpdateRequest.getGuid());
        return ResultVO.success("修改成功！",omEmployeeQuery);
    }

    @OperateLog(type = OperateType.DELETE,desc = "删除员工")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmployee omEmployee = omEmployeeService.selectById(id);
        if (omEmployee == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        omEmployeeService.moveEmp(id);
        return ResultVO.success("删除成功");
    }

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        OmEmployee omEmployee = omEmployeeService.selectById(id);
        if (omEmployee == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", omEmployee);
    }

    /**
     * 分页查询所有员工
     * @param page
     * @return
     */
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<OmEmployee> page) {
        return  ResultVO.success("查询成功", omEmployeeService.selectPage(getPage(page), getCondition(page)));
    }


    /**
     * 根据机构ID分页查询员工
     * @param page
     * @param id
     * @return
     */
    @PostMapping("/queryEmpByOrg/{id}")
    public ResultVO queryEmpByOrg(@RequestBody @Validated SmartPage<OmEmployee> page,@PathVariable @NotBlank(message
            = "id不能为空") String id){
        return ResultVO.success("查询成功",omEmployeeService.queryEmpByOrg(getPage(page),getCondition(page),id));
    }

    /**
     * 根据机构ID不分页查询员工
     * @param id
     * @return
     */
    @GetMapping("/listsByOrg/{id}")
    public ResultVO queryEmpByOrg(@PathVariable @NotBlank(message = "id不能为空") String id){

        List<OmEmployee> list = omEmployeeService.queryEmpListByOrg(id);

        return ResultVO.success("查询成功",list);
    }


    /**
     * 根据机构和岗位ID分页查询员工信息
     * @param page
     * @return
     */
    @PostMapping("/queryByOrgPosition")
    public ResultVO queryByOrgPosition(@RequestBody @Validated  SmartPage<OmEmployeeByOrgAndPositionRequest> page){
            return ResultVO.success("查询成功",omEmployeeService.queryByOrgPosition(getThisPage(page),page.getCondition().getGuidOrg(),page.getCondition().getGuidPosition()));
    }

    /**
     * 为根据机构和岗位ID分页查询员工信息接口写的获取page的方法
     * @param smartPage
     * @return
     */
    private Page<OmEmployee> getThisPage(SmartPage<OmEmployeeByOrgAndPositionRequest> smartPage) {
        PageVO vo = smartPage.getPage();
        Page<OmEmployee> page = new Page<>(vo.getCurrent(), vo.getSize());
        if (vo.getOrderByField() != null) {
            page.setOrderByField(vo.getOrderByField());
            page.setAsc(vo.getAsc());
        }
        return page;
    }

    /**
     * 获取同机构下不再某岗位下的员工
     * @param om
     * @return
     */
    @PostMapping("/otherEmployee")
    public ResultVO getOtherEmp(@RequestBody @Validated OmEmployeeByOrgAndPositionRequest om){
        return ResultVO.success("查询成功",omEmployeeService.getOtherEmp(om));
    }


    @OperateLog(type = OperateType.UPDATE,desc = "员工入职")
    @PutMapping("/onJob")
    public ResultVO onJob(@RequestBody @Validated OmEmployee omEmployee) {
        OmEmployee omEmployeeQue = omEmployeeService.selectById(omEmployee.getGuid());
        if (omEmployeeQue == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        if (OmEmployeeStatus.OFFER != omEmployeeQue.getEmpstatus()){
            return ResultVO.error("404", "员工不是在招状态不能办理入职");
        }

        omEmployeeQue = omEmployeeService.onJob(omEmployee);
        return ResultVO.success("入职成功！",omEmployeeQue);
    }

    @OperateLog(type = OperateType.UPDATE,desc = "员工离职")
    @PutMapping("/outJob")
    public ResultVO outJob(@RequestBody @Validated OmEmployee omEmployee) {
        OmEmployee omEmployeeQue = omEmployeeService.selectById(omEmployee.getGuid());
        if (omEmployeeQue == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        if (OmEmployeeStatus.ONJOB != omEmployeeQue.getEmpstatus()){
            return ResultVO.error("404", "员工不是在职状态不能办理离职");
        }

        omEmployeeQue = omEmployeeService.outJob(omEmployee);
        return ResultVO.success("离职成功！",omEmployeeQue);
    }

    /**
     * 为岗位,机构和员工的机构表里添加员工
     * @param omEmployee
     * @return
     */
    @PostMapping("/addByOrgPosition")
    public ResultVO addInOrgAndPosition(@RequestBody @Validated OmEmployee omEmployee){
        omEmployeeService.addInOrgAndPosition(omEmployee);
        return ResultVO.success("添加成功");
    }
    
}

