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
 * Created by chenchao
 * Created on 2018/5/16 15:30
 */
public class AcFuncAttrControllerTest extends BaseTest {

    /**
     * 新增功能属性
     * @throws Exception
     */
    @Test
    public void add()throws Exception{
        RequestBuilder requestBuilder = null;
        requestBuilder = post("/acFuncAttr/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guidFunc\":\"995937631999619074\",\"attrType\": \"4的类型\", \"attrKey\": \"004\", \"attrValue\":\"004的值\", \"memo\":\"004的描述\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    /**
     * 修改功能属性
     * @throws Exception
     */
    @Test
    public void update()throws Exception{
        RequestBuilder requestBuilder = null;
        requestBuilder = put("/acFuncAttr")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"guidFunc\":\"995937631999619074\",\"attrType\": \"3的类型\", \"attrKey\": \"003\", \"attrValue\":\"003的值\", \"memo\":\"003的描述\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }


    /**
     * 删除功能属性
     * @throws Exception
     */
    @Test
    public void delete()throws Exception{
        RequestBuilder requestBuilder = null;
        requestBuilder = MockMvcRequestBuilders.delete("/acFuncAttr")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"guidFunc\":\"995937631999619074\",\"attrType\": \"3的类型\", \"attrKey\": \"003\", \"attrValue\":\"003的值\", \"memo\":\"003的描述\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    /**
     * 根据条件查询功能属性
     * @throws Exception
     */
    @Test
    public void query()throws Exception{
        RequestBuilder requestBuilder = null;
        requestBuilder = get("/acFuncAttr/detail")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"guidFunc\":\"995937631999619074\",\"attrType\": \"3的类型\", \"attrKey\": \"004\", \"attrValue\":\"003的值\", \"memo\":\"003的描述\"}")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }

    /**
     * 根据功能GUID查询功能属性列表
     * @throws Exception
     */
    @Test
    public void queryList()throws Exception{
        RequestBuilder requestBuilder = null;
        requestBuilder = get("/acFuncAttr/detailList/996295466776137729")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder)
                .andExpect(content().string(startsWith("{\"code\":\"200\",")));
    }




}
