package org.tis.tools.senior.module.developer.service;

import com.baomidou.mybatisplus.service.IService;
import org.tis.tools.senior.module.developer.entity.SProject;

import java.util.List;

/**
 * sProject的Service接口类
 * 
 * @author Auto Generate Tools
 * @date 2018/06/20
 */
public interface ISProjectService extends IService<SProject>  {

    List<SProject> selectProjectAll();
}

