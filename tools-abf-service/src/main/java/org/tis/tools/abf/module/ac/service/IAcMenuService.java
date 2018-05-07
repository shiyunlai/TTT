package org.tis.tools.abf.module.ac.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.abf.module.ac.entity.AcMenu;

import java.util.List;

/**
 * acMenu的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
public interface IAcMenuService extends IService<AcMenu>  {
    /**
     * <p>
     * 根据 id 条件，查询该菜单的下一级所有的子菜单
     * </p>
     *
     * @param gidParents  菜单Gid
     * @return List<Object>
     */
    List<Object> selectSubMenu(String gidParents);
}

