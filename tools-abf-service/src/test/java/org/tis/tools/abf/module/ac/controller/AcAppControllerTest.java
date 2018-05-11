package org.tis.tools.abf.module.ac.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tis.tools.abf.base.BaseTest;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * 应用的controller测试类
 * Created by chenchao
 * Created on 2018/5/2 14:08
 */
public class AcAppControllerTest extends BaseTest {

   //测试新增
   @Test
    public void add() throws Exception {
        RequestBuilder request = null;
        request = post("/acApp/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"appCode\":\"005\",\"appName\": \"5\", \"appType\": \"local\", \"url\":\"http://123.com\", \"ipAddr\": \"5\", \"ipPort\":\"5\", \"appDesc\":\"5\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

   //测试修改
   @Test
    public void update() throws Exception{
       RequestBuilder request = null;
       request = put("/acApp")
               .contentType(MediaType.APPLICATION_JSON_UTF8)
               .content("{ \"guid\":\"994473754745049090\",\"appCode\":\"001\",\"appName\":\"应用1\",\"appType\":\"AcAppType.REMOTE\",\"isopen\":\"YES\",\"openDate\":\"2018-04-26 15:48:59\",\"url\":\"http://127.0.0.1:18080/acApp/update\",\"ipAddr\":\"127.0.0.2\",\"ipPort\":\"18080\",\"appDesc\":\"应用1的描述之修改\"}")
               .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

   //测试删除
    @Test
    public void delete() throws Exception{
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/acApp/994477946838581249")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("")
        .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    //测试开通应用
    @Test
    public void openApp() throws Exception{
        RequestBuilder request = null;
        request = put("/acApp/openApp/989411000669696001")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    //测试停用应用
    @Test
    public void stopApp() throws Exception{
        RequestBuilder request = null;
        request = put("/acApp/stopApp/989411000669696001")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    //测试根据ID查询应用
    @Test
    public void detail() throws Exception{
        RequestBuilder request = null;
        request = get("/acApp/994495262758481921")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    //测试应用列表
    @Test
    public void appList() throws Exception{
        RequestBuilder request = null;
        request = post("/acApp/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }
}
