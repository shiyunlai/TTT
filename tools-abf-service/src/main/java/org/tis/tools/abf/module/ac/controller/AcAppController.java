package org.tis.tools.abf.module.ac.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.ac.controller.request.AcAppAddRequest;
import org.tis.tools.abf.module.ac.controller.request.AcAppListRequest;
import org.tis.tools.abf.module.ac.controller.request.AcAppUpdateRequest;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.model.common.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.model.auth.TisApp;

import java.util.Date;
import java.util.List;

/**
 * acApp的Controller类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/acApp")
@Validated
public class AcAppController extends BaseController<AcApp>  {

    @Autowired
    private IAcAppService acAppService;


    /**
     * 新增应用
     * @param request
     * @return ResultVO
     */
    @OperateLog(type = OperateType.ADD, desc = "新增应用")
    @PostMapping
    public ResultVO add(@RequestBody @Validated AcAppAddRequest request){

        acAppService.creatRootApp(request.getAppCode(),request.getAppName(),request.getAppType(),request.getUrl(),request.getIpAddr(),request.getIpPort(),request.getAppDesc(),request.getIsopen(),request.getOpenDate());

        return ResultVO.success("新增成功!");
    }

    /**
     * 根据ID修改应用
     * @param updateRequest
     * @return
     */
    @OperateLog(type = OperateType.UPDATE,  desc = "修改应用")
    @PutMapping
    public ResultVO update(@RequestBody @Validated AcAppUpdateRequest updateRequest) {
        AcApp app;

        AcApp acApp = acAppService.selectById(updateRequest.getGuid());
        if (acApp == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        app = acAppService.changeById(updateRequest.getGuid(),updateRequest.getAppCode(),updateRequest.getAppName(),
                updateRequest.getAppType(),updateRequest.getIsopen(),updateRequest.getOpenDate() ,updateRequest.getUrl(), updateRequest.getIpAddr(),
                updateRequest.getIpPort(), updateRequest.getAppDesc());
        return ResultVO.success("修改成功！",app);
    }

    /**
     * 根据ID删除应用
     * @param id
     * @return ResultVO
     */
    @OperateLog(type = OperateType.DELETE, desc = "删除应用")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcApp acApp = acAppService.selectById(id);
        if (acApp == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }

        acAppService.moveApp(id);
        return ResultVO.success("删除成功");

    }

    /**
     * 开通应用
     * @param id
     * @return
     */
    @OperateLog(type = OperateType.UPDATE, desc = "开通应用")
    @PutMapping("/openApp/{id}")
    public ResultVO openApp(@PathVariable @NotBlank(message = "id不能为空") String id){

        AcApp acApp = acAppService.selectById(id);
        if (acApp == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acApp.setIsopen(YON.YES);
        acApp.setOpenDate(new Date());
        acAppService.updateById(acApp);
        return ResultVO.success("应用已开通",acApp);
    }

    /**
     * 停用应用
     * @param id
     * @return
     */
    @OperateLog(type = OperateType.UPDATE, desc = "停用应用")
    @PutMapping("/stopApp/{id}")
    public ResultVO stopApp(@PathVariable @NotBlank(message = "id不能为空") String id){

        AcApp acApp = acAppService.selectById(id);
        if (acApp == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        acApp.setIsopen(YON.NO);
        acApp.setOpenDate(null);
        acAppService.updateById(acApp);
        return ResultVO.success("应用已停用",acApp);
    }

    /**
     * 根据ID查询应用
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable @NotBlank(message = "id不能为空") String id) {
        AcApp acApp = acAppService.selectById(id);
        if (acApp == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        return ResultVO.success("查询成功", acApp);
    }

    /**
     * 根据CODE查询应用
     * @param code
     * @return
     */
    @GetMapping("/auth/{code}")
    public ResultVO detailByCode(@PathVariable @NotBlank(message = "code不能为空") String code) {
        EntityWrapper<AcApp> wrapper = new EntityWrapper<>();
        wrapper.eq(AcApp.COLUMN_APP_CODE, code);
        AcApp acApp = acAppService.selectOne(wrapper);
        if (acApp == null) {
            return ResultVO.error("404", "找不到对应记录或已经被删除！");
        }
        TisApp tisApp = new TisApp();
        BeanUtils.copyProperties(tisApp, acApp);
        return ResultVO.success("查询成功", tisApp);
    }

    /**
     * 不分页查询所有应用
     * @return
     */
    @GetMapping("/queryAll")
    public ResultVO queryAll(){

        List<AcApp> list = acAppService.queryAll();
        return ResultVO.success("查询成功",list);
    }

    /**
     * 分页查询应用
     * @param page
     * @return
     */
    @PostMapping("/list")
    public ResultVO list(@RequestBody @Validated SmartPage<AcApp> page) {
        return  ResultVO.success("查询成功", acAppService.selectPage(getPage(page), getCondition(page)));
    }

    /**
     * 批量查询应用信息
     * @param acAppListRequest
     * @return
     */
    @PostMapping("/batchQuery")
    public ResultVO batchQuery(@RequestBody @Validated AcAppListRequest acAppListRequest){
        List<AcApp> lists = acAppService.batchQuery(acAppListRequest);
        return ResultVO.success("查询成功",lists);
    }

}

