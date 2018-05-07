package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.tis.tools.abf.module.ac.service.IAcMenuService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.entity.AcMenu;
import org.tis.tools.abf.module.ac.dao.AcMenuMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * acMenu的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcMenuServiceImpl extends ServiceImpl<AcMenuMapper, AcMenu> implements IAcMenuService {
    AcMenuMapper acMenuMapper;
    /**
     * 根据父菜单查询出子菜单
     * @param  gidParents 父菜单Gid
     */
    @Override
    public List<Object> selectSubMenu(String gidParents) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("gidParents",gidParents);
        List lists = acMenuMapper.selectList(wrapper);
        return lists;
    }
}

