package com.kaishengit.tms.mapper;

import com.kaishengit.tms.entity.Premission;
import com.kaishengit.tms.entity.PremissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PremissionMapper {
    long countByExample(PremissionExample example);

    int deleteByExample(PremissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Premission record);

    int insertSelective(Premission record);

    List<Premission> selectByExample(PremissionExample example);

    Premission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Premission record, @Param("example") PremissionExample example);

    int updateByExample(@Param("record") Premission record, @Param("example") PremissionExample example);

    int updateByPrimaryKeySelective(Premission record);

    int updateByPrimaryKey(Premission record);
}