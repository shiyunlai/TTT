package org.tis.tools.abf.module.sys.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.tis.tools.abf.module.common.log.OperateLog;
import org.tis.tools.abf.module.common.log.OperateType;
import org.tis.tools.abf.module.common.log.ReturnType;
import org.tis.tools.abf.module.sys.controller.request.SysDictQueryRequest;
import org.tis.tools.abf.module.sys.controller.request.SysDictRequest;
import org.tis.tools.abf.module.sys.entity.enums.DictFromType;
import org.tis.tools.abf.module.sys.entity.enums.DictType;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.SmartPage;
import org.tis.tools.abf.module.sys.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.sys.entity.SysDict;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.abf.module.sys.exception.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * sysDict的Controller类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@RestController
@RequestMapping("/sysDict")
@Validated
public class SysDictController  extends BaseController {
    @Autowired
    private ISysDictService iSysDictService;

    /**
     * 新增业务字典
     * @param request
     * @return
     */
    @OperateLog(
            operateType = OperateType.ADD,  // 操作类型
            operateDesc = "新增系统参数机构", // 操作描述
            retType = ReturnType.Object, // 返回类型，对象或数组
            id = "guidApp", // 操作对象标识
            name = "groupName", // 操作对象名
            keys = {"guidApp","groupName"}
    )

    @RequestMapping("/addSysDict")
    public ResultVO createSysDict(@RequestBody @Validated SysDictRequest request) {
        SysDict sysDict = new SysDict();
        sysDict.setDictKey(request.getDictKey());
        sysDict.setDictType(DictType.valueOf(request.getDictType()));
        sysDict.setDictName(request.getDictName());
        sysDict.setDictDesc(request.getDictDesc());
        sysDict.setGuidParents(request.getGuidParents());
        sysDict.setDefaultValue(request.getDefaultValue());
        sysDict.setFromTable(request.getFromTable());
        sysDict.setUseForKey(request.getUseForKey());
        sysDict.setUseForName(request.getUseForName());
        sysDict.setSeqno(request.getSeqNo());
        sysDict.setSqlFilter(request.getSqlFilter());
        sysDict.setFromType(DictFromType.valueOf(request.getFromType()));
        sysDict =  iSysDictService.addDict(sysDict);
        return ResultVO.success("添加成功",sysDict);
    }

    /**
     * 删除业务字典
     * @param id
     * @return
     * @throws SysManagementException
     * @throws java.text.ParseException;
     */
    @OperateLog(
            operateType = OperateType.DELETE,
            operateDesc = "删除业务字典",
            retType = ReturnType.Object,
            id = "guid",
            name = "dictName",
            keys = {"dictKey", "dictType"}
    )

    @RequestMapping("/deleteSysDict")
    public ResultVO deleteSysDict(@RequestBody @Validated String id) {
        SysDict sysDict = iSysDictService.deleteDict(id);
        return ResultVO.success("删除成功",sysDict);
    }

    /**
     * 修改业务字典
     * @param request
     */
    @OperateLog(
            operateType = OperateType.UPDATE,
            operateDesc = "修改业务字典",
            retType = ReturnType.Object,
            id = "guid",
            name = "dictName",
            keys = {"dictKey", "dictType"}
    )

    @RequestMapping("/updateSysDict")
    public ResultVO editSysDict(@RequestBody @Validated SysDictRequest request) {
        SysDict sysDict = new SysDict();
        sysDict.setGuid(request.getGuid());
        sysDict.setDictKey(request.getDictKey());
        sysDict.setDictType(DictType.valueOf(request.getDictType()));
        sysDict.setDictName(request.getDictName());
        sysDict.setDictDesc(request.getDictDesc());
        sysDict.setGuidParents(request.getGuidParents());
        sysDict.setDefaultValue(request.getDefaultValue());
        sysDict.setFromTable(request.getFromTable());
        sysDict.setUseForKey(request.getUseForKey());
        sysDict.setUseForName(request.getUseForName());
        sysDict.setSeqno(request.getSeqNo());
        sysDict.setSqlFilter(request.getSqlFilter());
        sysDict.setFromType(DictFromType.valueOf(request.getFromType()));
        iSysDictService.editSysDict(sysDict);
        return ResultVO.success("修改成功",sysDict);
    }

    /**
     * 查询单个业务字典
     * @param id
     */

    @RequestMapping("/queryOneSysDict")
    public ResultVO querySysDict(@RequestBody @Validated String id) {
        return ResultVO.success("查询成功",iSysDictService.queryOneSysDictByGuid(id));
    }

    /**
     * 查询所有业务字典
     */

    @RequestMapping("/queryAllSysDictList")
    public ResultVO querySysDictList(){
        return ResultVO.success("查询成功",iSysDictService.querySysDicts());
    }
    /**
     * 导出所有业务字典为excel
     *
     */
//
//    @RequestMapping("/exportDictExcel")
//    public void exportDictExcel(HttpServletRequest request, HttpServletResponse response) {
//        OutputStream out = null;
//        try {
//            if (logger.isInfoEnabled()) {
//                logger.info("exportDictExcel request ");
//            }
//            // 生成提示信息，
//            response.setContentType("application/vnd.ms-excel");
//            // 进行转码，使其支持中文文件名
//            String codedFileName = URLEncoder.encode("业务字典数据", "UTF-8");
//            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xlsx");
//
//            List<SysDict> sysDicts = dictRService.querySysDicts(null);
//            List<SysDictItem> sysDictItems = dictRService.querySysDictItemList();
//
//            XSSFWorkbook wb = new XSSFWorkbook();  //--->创建了一个excel文件
//            XSSFSheet sheet = wb.createSheet("业务字典");   //--->创建了一个工作簿
//
//            sheet.setColumnWidth((short)0, 20* 256);    //---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
//            sheet.setColumnWidth((short)1, 30* 256);    //---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
//            sheet.setColumnWidth((short)2, 10* 256);    //---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
//            sheet.setColumnWidth((short)3, 20* 256);    //---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
//            sheet.setColumnWidth((short)4, 10* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
//            sheet.setColumnWidth((short)5, 50* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
//            sheet.setColumnWidth((short)6, 20* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
//            sheet.setColumnWidth((short)7, 20* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
//            sheet.setColumnWidth((short)8, 20* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
//            sheet.setColumnWidth((short)9, 20* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
//            sheet.setColumnWidth((short)10, 20* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
//            sheet.setColumnWidth((short)11, 20* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
//            sheet.setColumnWidth((short)12, 20* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
//            sheet.setDefaultRowHeight((short)300);    // ---->设置统一单元格的高度，就用这个方法
//
//            //表格第一行
//            XSSFRow row1 = sheet.createRow(0);   //--->创建一行
//            String[] columnList = {
//                    SysDict.COLUMN_GUID, SysDict.COLUMN_DICT_KEY, SysDict.COLUMN_DICT_TYPE, SysDict.COLUMN_DICT_NAME,
//                    SysDict.COLUMN_FROM_TYPE, SysDict.COLUMN_DICT_DESC, SysDict.COLUMN_GUID_PARENTS, SysDict.COLUMN_DEFAULT_VALUE,
//                    SysDict.COLUMN_FROM_TABLE, SysDict.COLUMN_USE_FOR_KEY, SysDict.COLUMN_USE_FOR_NAME, SysDict.COLUMN_SQL_FILTER,
//                    SysDict.COLUMN_SEQNO };
//            for (short i = 0; i < columnList.length; i++) {
//                row1.createCell(i).setCellValue(columnList[i]);//--->创建单元格
//            }
//            int j = 1;
//            for (SysDict dict : sysDicts) {
//                XSSFRow row = sheet.createRow(j);   //--->创建一行
//                row.createCell(0).setCellValue(dict.getGuid());
//                row.createCell(1).setCellValue(dict.getDictKey());
//                row.createCell(2).setCellValue(dict.getDictType());
//                row.createCell(3).setCellValue(dict.getDictName());
//                row.createCell(4).setCellValue(dict.getFromType());
//                row.createCell(5).setCellValue(dict.getDictDesc());
//                row.createCell(6).setCellValue(dict.getGuidParents());
//                row.createCell(7).setCellValue(dict.getDefaultValue());
//                row.createCell(8).setCellValue(dict.getFromTable());
//                row.createCell(9).setCellValue(dict.getUseForKey());
//                row.createCell(10).setCellValue(dict.getUseForName());
//                row.createCell(11).setCellValue(dict.getSqlFilter());
//                row.createCell(12).setCellValue(dict.getSeqno().toString());
//                j++;
//            }
//
//            XSSFSheet sheet1 = wb.createSheet("业务字典项");   //--->创建了一个工作簿
//
//            sheet1.setColumnWidth((short)0, 30* 256);    //---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
//            sheet1.setColumnWidth((short)1, 30* 256);    //---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
//            sheet1.setColumnWidth((short)2, 60* 256);    //---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
//            sheet1.setColumnWidth((short)3, 20* 256);    //---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
//            sheet1.setColumnWidth((short)4, 20* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
//            sheet1.setColumnWidth((short)5, 20* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
//            sheet1.setDefaultRowHeight((short)300);    // ---->设置统一单元格的高度，就用这个方法
//
//            //表格第一行
//            XSSFRow row2 = sheet1.createRow(0);   //--->创建一行
//            String[] columnList1 = {
//                    SysDictItem.COLUMN_GUID, SysDictItem.COLUMN_GUID_DICT, SysDictItem.COLUMN_ITEM_NAME,
//                    SysDictItem.COLUMN_ITEM_VALUE, SysDictItem.COLUMN_SEND_VALUE, SysDictItem.COLUMN_SEQNO};
//            for (short i = 0; i < columnList1.length; i++) {
//                row2.createCell(i).setCellValue(columnList1[i]);//--->创建单元格
//            }
//            int k = 1;
//            for (SysDictItem dict : sysDictItems) {
//                XSSFRow row = sheet1.createRow(k);   //--->创建一行
//                row.createCell(0).setCellValue(dict.getGuid());
//                row.createCell(1).setCellValue(dict.getGuidDict());
//                row.createCell(2).setCellValue(dict.getItemName());
//                row.createCell(3).setCellValue(dict.getItemValue());
//                row.createCell(4).setCellValue(dict.getSendValue());
//                row.createCell(5).setCellValue(dict.getSeqno().toString());
//                k ++;
//            }
//            out = response.getOutputStream();
//            wb.write(out);
//            //样式1
////            XSSFCellStyle style = wb.createCellStyle(); // 样式对象
////            style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
////            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 水平
////            //设置标题字体格式
////            Font font = wb.createFont();
////            //设置字体样式
////            font.setFontHeightInPoints((short)20);   //--->设置字体大小
////            font.setFontName("Courier New");   //---》设置字体，是什么类型例如：宋体
////            font.setItalic(true);     //--->设置是否是加粗
////
////            XSSFCellStyle style1 = wb.createCellStyle(); // 样式对象
////            style1.setFont(font);     //--->将字体格式加入到style1中
////
////
////            XSSFCell cell1 = row1.createCell((short)0);   //--->创建一个单元格
////            cell1.setCellStyle(style);
////            cell1.setCellValue("");
//
//        } catch (ToolsRuntimeException e) {
//            AjaxUtils.ajaxJsonErrorMessage(response,e.getCode(), e.getMessage());
//            logger.error("exportDictExcel exception : ", e);
//        }catch (Exception e) {
//            AjaxUtils.ajaxJsonErrorMessage(response,"SYS_0001", e.getMessage());
//            logger.error("exportDictExcel exception : ", e);
//        } finally {
//            try {
//                out.flush();
//                out.close();
//            } catch (IOException e) {}
//        }
//    }

//    /**
//     * 根据key查询业务字典信息
//     *
//     */
//    @RequestMapping("/queryDict")
//    public ResultVO queryDict(@RequestBody @Validated String DictKey) {
//        return ResultVO.success("查询成功",iSysDictService.dictKeyQuery(DictKey,""));
//    }

    /**
     * 修改字典项默认值
     *
     */
    @OperateLog(
            operateType = OperateType.UPDATE,
            operateDesc = "设置字典默认字典项",
            retType = ReturnType.Object,
            id = "dictKey",
            name = "dictName",
            keys = "defaultValue"
    )

//    @RequestMapping("/setDefaultDictValue")
//    public ResultVO setDefaultDictValue(@RequestBody @Validated SysDictQueryRequest request) {
//        return ResultVO.success("添加成功",iSysDictService.dictKeyQuery(request.getDictKey(),request.getDictName()));
//    }

    @RequestMapping("/dictKeyOrNameQuery")
    public ResultVO dictKeyOrQuery(@RequestBody @Validated SysDictQueryRequest request) {
        return ResultVO.success("添加成功",iSysDictService.dictKeyQuery(request.getDictKey(),request.getDictName()));
    }
}
