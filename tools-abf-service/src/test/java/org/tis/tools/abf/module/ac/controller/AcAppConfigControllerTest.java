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
 * 配置
 * Created by chenchao
 * Created on 2018/5/10 17:04
 */
public class AcAppConfigControllerTest extends BaseTest {

    //测试新增
    @Test
    public void add() throws Exception{
        RequestBuilder request = null;
        request = post("/acAppConfig/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guidApp\":\"989411000669696001\",\"configType\": \"003\", \"configName\": \"应用3\", \"configDict\":\"local\", \"configStyle\": \"3\", \"configValue\":\"默认值03\", \"enabled\":\"Y\", \"displayOrder\": \"10\", \"configDesc\": \"应用3的描述新增\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    //测试修改
    @Test
    public void update() throws Exception{
        RequestBuilder request = null;
        request = put("/acAppConfig")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guid\":\"992049405471744001\",\"guidApp\":\"989411000669696001\",\"configType\": \"001\", \"configName\": \"应用1\", \"configDict\":\"local\", \"configStyle\": \"1\", \"configValue\":\"默认值01\", \"enabled\":\"Y\", \"displayOrder\": \"9\", \"configDesc\": \"应用1的描述修改\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    //测试删除
    @Test
    public void delete() throws Exception{
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.delete("/acAppConfig/994556311977201666")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    //测试根据ID查配置
    @Test
    public void detail() throws Exception{
        RequestBuilder request = null;
        request = get("/acAppConfig/994556807718817793")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    //测试分页配置列表
   /* @Test
    public void list() throws Exception{
        RequestBuilder request = null;
        request = post("/acAppConfig/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"current\":\"0\",\"size\":\"10\",\"orderByField\": \"\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }*/

}
