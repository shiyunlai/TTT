package org.tis.tools.core.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.tis.tools.core.utils.StringUtil;
import org.tis.tools.core.web.vo.PageVO;
import org.tis.tools.core.web.vo.SmartPage;

import java.lang.reflect.Field;

/**
 * describe: 
 *
 * @author zhaoch
 * @date 2018/3/27
 **/
public class BaseController<T> {

    protected Page<T> getPage(SmartPage<T> smartPage) {
        PageVO vo = smartPage.getPage();
        Page<T> page = new Page<>(vo.getCurrent(), vo.getSize());
        if (vo.getOrderByField() != null) {
            page.setOrderByField(vo.getOrderByField());
            page.setAsc(vo.getAsc());
        }
        return page;
    }

    protected EntityWrapper<T> getCondition(SmartPage<T> smartPage) {
        T condition = smartPage.getCondition();
        if (condition == null) {
            return null;
        }
        return new EntityWrapper<>(condition);
    }

    protected <S> Page<S> getPage(SmartPage<S> smartPage, Class<S> clazz) {
        PageVO vo = smartPage.getPage();
        Page<S> page = new Page<>(vo.getCurrent(), vo.getSize());
        if (vo.getOrderByField() != null) {
            page.setOrderByField(vo.getOrderByField());
            page.setAsc(vo.getAsc());
        }
        return page;
    }

    protected <S> EntityWrapper<S> getWrapper(S s) {
        if(s == null){
            return null;
        }
        EntityWrapper<S> wrapper = new EntityWrapper<>();
        Field[] fields = s.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
                String name = field.getName();
                try {
                    Object o = field.get(s);
                    if (o != null) {
                        wrapper.like(StringUtil.camel2Underline(name).toLowerCase(), o.toString());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return wrapper;
    }



}
