package org.tis.tools.senior.module.developer.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.senior.module.developer.dao.SStandardListMapper;
import org.tis.tools.senior.module.developer.entity.SStandardList;
import org.tis.tools.senior.module.developer.service.ISStandardListService;

/**
 * sStandardList的Service接口实现类
 * 
 * @author Auto Generate Tools
 * @date 2018/07/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SStandardListServiceImpl extends ServiceImpl<SStandardListMapper, SStandardList> implements ISStandardListService {

}

