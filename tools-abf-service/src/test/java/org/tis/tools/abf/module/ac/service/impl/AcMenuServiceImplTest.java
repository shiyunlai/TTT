package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.base.BaseTest;
import org.tis.tools.abf.module.ac.entity.AcMenu;
import org.tis.tools.abf.module.ac.service.IAcMenuService;

import java.math.BigDecimal;
import java.util.List;

public class AcMenuServiceImplTest extends BaseTest {
    @Autowired
    IAcMenuService acMenuService;

    @Test
    public void testSelectSubMenu() throws Exception {
        String GUID = "995919032385556481";
        List<AcMenu> lists = acMenuService.selectSubMenu(GUID);
        for (AcMenu acMenu : lists) {
            System.out.println(acMenu.toString());
        }
    }

    @Test
    public void testMoveMenu() throws Exception {
        AcMenu acMenu = acMenuService.moveMenu("995971755206250498", "995972158803152898", BigDecimal.ONE);
        System.out.print(acMenu.toString());
    }

    @Test
    public void testQueryPageAcMenu() throws Exception {
        Page<AcMenu> page = acMenuService.queryPageAcMenu(1, 10);
        List<AcMenu> acMenus = page.getRecords();
        for (AcMenu am : acMenus) {
            System.out.println(am.toString());
        }
    }

    @Test
    public void testDeleteAllSubAcMenu() throws Exception {
        String GUID = "995919032385556481";
        AcMenu result = acMenuService.deleteAllSubAcMenu(GUID);
        System.out.print(result);
    }

    @Test
    public void testUpdateAcMenu() throws Exception {
        AcMenu acMenu = new AcMenu();
        acMenu.setMenuName("assd");
        acMenu.setGuidApp("201832148213");
        acMenu.setMenuLabel("测试菜单");
        acMenu.setMenuCode("AC00001");
        acMenu.setIsleaf("Y");
        acMenu.setMenuSeq("1");
        acMenu.setGuidParents("201832148213");
        AcMenu result = acMenuService.updateAcMenu(acMenu);
        System.out.print(result);
    }

    @Test
    public void testCreateChildMenu() throws Exception {
        AcMenu acMenu = new AcMenu();
        acMenu.setMenuName("测试子菜单1");
        acMenu.setGuidApp("App002");
        acMenu.setMenuLabel("测试菜单2");
        acMenu.setMenuCode("AC00002");
        acMenu.setIsleaf("Y");
        acMenu.setMenuSeq("998583132815122434");
        acMenu.setImagePath("a.jpg");
        acMenu.setUpdator("测试用户1");
        acMenu.setGuidParents("998583132815122434");
        AcMenu result = acMenuService.createChildMenu(acMenu);
        System.out.print(result);
    }

    @Test
    public void testCreateRootMenu() throws Exception {
        AcMenu acMenu = new AcMenu();
        acMenu.setMenuName("测试菜单14");
        acMenu.setGuidApp("App002");
        acMenu.setMenuLabel("测试菜单2");
        acMenu.setMenuCode("AC00004");
        acMenu.setMenuSeq("0");
        acMenu.setUpdator("测试用户");
        acMenu.setImagePath("imags/2.jpg");
        AcMenu result = acMenuService.createRootMenu(acMenu);
        System.out.print(result);
    }

    @Test
    public void testQueryRootMenu() throws Exception {
        String GUID = "App002";
        List<AcMenu> lists = acMenuService.queryRootMenu(GUID);
        for (AcMenu acMenu : lists) {
            System.out.println(acMenu.toString());
        }
    }

    @Test
    public void testMove(){
        BigDecimal index = new BigDecimal("1");
        acMenuService.reorderMenu("995972687042187266", index ,"push");
        System.out.print("wr32");
    }
}