package org.tis.tools.abf.module.ac.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.RequestBuilder;
import org.tis.tools.abf.base.BaseTest;
import org.tis.tools.abf.module.ac.entity.AcMenu;
import org.tis.tools.core.web.vo.PageVO;
import org.tis.tools.core.web.vo.SmartPage;


import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ministrator on 2018/5/10.
 */
public class AcMenuControllerTest extends BaseTest {
    @Test
    public void testAddAcMenu() throws Exception {
        AcMenu acMenu = new AcMenu();
        acMenu.setMenuName("测试菜单");
        acMenu.setGuidApp("201832148213");
        acMenu.setMenuLabel("测试菜单");
        acMenu.setMenuCode("AC00001");
        acMenu.setIsleaf("Y");
        acMenu.setMenuSeq("1");
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/addAcMenu")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(acMenu)));
    }

    @Test
    public void testAddSubAcmenu() throws Exception {
        AcMenu acMenu = new AcMenu();
        acMenu.setMenuName("测试菜单2");
        acMenu.setGuidApp("2018321482212");
        acMenu.setMenuLabel("测试菜单1");
        acMenu.setMenuCode("AC00001");
        acMenu.setIsleaf("Y");
        acMenu.setMenuSeq("1");
        acMenu.setGuidParents("201832148213");
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/addSubAcmenu")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(acMenu)));
    }

    @Test
    public void testUpdate() throws Exception {
        AcMenu acMenu = new AcMenu();
        acMenu.setMenuName("测试菜单2");
        acMenu.setGuidApp("201832148212");
        acMenu.setMenuLabel("测试菜单2");
        acMenu.setMenuCode("AC00002");
        acMenu.setIsleaf("Y");
        acMenu.setMenuSeq("12");
        acMenu.setGuidParents("201832148213");
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(put("/updateAcMenu")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(acMenu)));
    }

    @Test
    public void testDeleteAcMenu() throws Exception {
        RequestBuilder request = null;
        request = delete("/deleteAcMenu/201832148212").
                contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request);
    }

    @Test
    public void testDetail() throws Exception {
    }

    @Test
    public void testList() throws Exception {
        SmartPage page = new SmartPage();
        PageVO pageVo = new PageVO();
        pageVo.setCurrent(1);
        pageVo.setSize(10);
        page.setPage(pageVo);
        RequestBuilder request = null;
        request = get("/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"page:\" page }")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request);

    }

    @Test
    public void testGetMenu() throws Exception {
        RequestBuilder request = null;
        request = get("/queryAcMenuLists/4637246")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request);
    }

    @Test
    public void testGetSubMenu() throws Exception {
        RequestBuilder request = null;
        request = get("/querySubAcMenuLists/4637246")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request);
    }

    @Test
    public void testGetPageAcMenu() throws Exception {
        RequestBuilder request = null;
        request = get("/queryPageAcMenuLists/1/10")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request);
    }


    @Test
    public void testGetMoveMenu() throws Exception {

    }
}