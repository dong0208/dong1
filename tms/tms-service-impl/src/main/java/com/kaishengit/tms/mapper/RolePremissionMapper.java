package com.kaishengit.tms.mapper;

import com.kaishengit.tms.entity.RolePremissionExample;
import com.kaishengit.tms.entity.RolePremissionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePremissionMapper {
    long countByExample(RolePremissionExample example);

    int deleteByExample(RolePremissionExample example);

    int deleteByPrimaryKey(RolePremissionKey key);

    int insert(RolePremissionKey record);

    int insertSelective(RolePremissionKey record);

    List<RolePremissionKey> selectByExample(RolePremissionExample example);

    int updateByExampleSelective(@Param("record") RolePremissionKey record, @Param("example") RolePremissionExample example);

    int updateByExample(@Param("record") RolePremissionKey record, @Param("example") RolePremissionExample example);
}