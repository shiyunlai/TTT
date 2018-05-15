package org.tis.tools.abf.module.ac.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.tis.tools.abf.module.ac.entity.AcMenu;

import java.math.BigDecimal;

/**
 * acMenu的Mapper类
 * 
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Mapper
@Repository
public interface AcMenuMapper extends BaseMapper<AcMenu>  {

    void reorderMenu(@Param("targetGuid")String identityGuid, @Param("index") BigDecimal index, @Param("flag") String flag);

}

