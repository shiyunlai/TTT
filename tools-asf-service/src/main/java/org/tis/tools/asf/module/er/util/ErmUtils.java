package org.tis.tools.asf.module.er.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.XML;
import org.springframework.beans.BeanUtils;
import org.tis.tools.asf.core.exception.CodeBuilderException;
import org.tis.tools.asf.module.er.entity.*;
import org.tis.tools.asf.module.er.entity.enums.ERColumnType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class ErmUtils {

    public static String xml2String(String filepath) throws JSONException {
        File f= new File(filepath);
        String xmlstr;
        try {
             xmlstr = FileUtils.readFileToString(f, Charset.defaultCharset());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return null;
        }
        return XML.toJSONObject(xmlstr).toString();
    }

    public static String xml2Json(String xmlStr) throws JSONException {
        return XML.toJSONObject(xmlStr).toString();
    }

    public static ERApp parse(String xmlString) {

        ParserConfig.getGlobalInstance().putDeserializer(ERColumnType.class, new CommonEnumDeserializer());
        com.alibaba.fastjson.JSONObject diagram = JSON.parseObject(xmlString).getJSONObject("diagram");
        List<ERTable> erTables = JSONArray.parseArray(diagram.getJSONObject("contents").getString("table"), ERTable.class);
        Map<String, List<ERColumnGroup>> columnGroupMap = diagram.getJSONObject("column_groups") == null ?
                Collections.EMPTY_MAP : JSONArray.parseArray(diagram.getJSONObject("column_groups")
                .getString("column_group"), ERColumnGroup.class)
                .stream().collect(Collectors.groupingBy(ERColumnGroup::getId));
        Map<String, ERWord> wordMap = JSONArray.parseArray(diagram.getJSONObject("dictionary")
                .getString("word"), ERWord.class)
                .stream().collect(Collectors.toMap(ERWord::getId, w -> w));
        JSONObject categoryJB = diagram
                .getJSONObject("settings")
                .getJSONObject("category_settings")
                .getJSONObject("categories");
        List<ERCategory> erCategories = null;
        boolean defaultCategory = false;
        if (categoryJB != null) {
            String categoryString = categoryJB.getString("category");
            if (categoryString.charAt(0) == '{') {
                erCategories = Collections.singletonList(JSONObject.parseObject(categoryString, ERCategory.class));
            } else if (categoryString.charAt(0) == '[') {
                erCategories = JSONArray.parseArray(categoryString, ERCategory.class);
            }
        } else {
            // 没有分类创建默认分类
            ERCategory c = new ERCategory();
            c.setName("default");
            erCategories = Collections.singletonList(c);
            defaultCategory = true;
        }
        Map<String, List<ERColumn>> refColumnsMap = new HashMap<>(16);
        Map<String, ERColumn> columnMap = new HashMap<>(16);
        List<ERCategory> finalErCategories = erCategories;
        boolean finalDefaultCategory = defaultCategory;
        erTables.forEach(t -> {
            for (ERCategory c : finalErCategories) {
                if (c.getTableIds().contains(t.getId()) || finalDefaultCategory) {
                    t.setCategoryId(c.getId());
                    if (c.getTableList() == null) {
                        List<ERTable> tables = new ArrayList<>();
                        c.setTableList(tables);
                    }
                    c.getTableList().add(t);
                    break;
                }
            }
            if (CollectionUtils.isNotEmpty(t.getColumns().getColumnGroupList())) {
                for (String columnGroup : t.getColumns().getColumnGroupList()) {
                    if (columnGroupMap.get(columnGroup) != null) {
                        columnGroupMap.get(columnGroup).get(0).getColumns().getNormalColumnList().forEach(column -> {
                            ERColumn erColumn = new ERColumn();
                            BeanUtils.copyProperties(column, erColumn);
                            t.getColumns().getNormalColumnList().add(erColumn);
                        });
                    }
                }
            }
            for (ERColumn c : t.getColumns().getNormalColumnList()) {
                columnMap.put(c.getId(), c);
                c.setTableId(t.getId());
                String wordId = c.getWordId();
                if (wordId != null && wordMap.get(wordId) != null) {
                    ERWord w = wordMap.get(wordId);
                    c.setLogicalName(w.getLogicalName());
                    c.setPhysicalName(w.getPhysicalName());
                    c.setType(ERColumnType.getColumnType(w.getType()));
                    c.setDesc(w.getDescription());
                    c.setLength(w.getLength());
                }
                String refColumnId = c.getReferencedColumn();
                if (StringUtils.isNotBlank(refColumnId)) {
                    ERColumn refColumn = columnMap.get(refColumnId);
                    if (refColumn != null) {
                        ERWord word = wordMap.get(refColumn.getWordId());
                        c.setLength(word.getLength());
                        c.setDesc(word.getDescription());
                    } else {
                        refColumnsMap.computeIfAbsent(c.getReferencedColumn(), k -> new ArrayList<>());
                        refColumnsMap.get(c.getReferencedColumn()).add(c);
                    }
                }
                t.setColumnList(t.getColumns().getNormalColumnList());
            }
        });
        // 处理引用
        refColumnsMap.forEach((id, list) -> list.forEach(c -> {
            ERWord word = wordMap.get(columnMap.get(id).getWordId());
            c.setLength(word.getLength());
            c.setDesc(word.getDescription());
        }));
        List<ERColumn> columns = erTables.stream()
                .map(ERTable::getColumns)
                .map(ERColumns::getNormalColumnList)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        ERApp erApp = new ERApp();
        erApp.setColumnList(columns);
        erApp.setCategoryList(erCategories);
        erApp.setTableList(erTables);

        return erApp;
    }

}
