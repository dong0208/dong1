package com.dong.mapper;

import com.dong.entity.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MovieMapper {
    List<Movie> findList(@Param("title") String title);
    List<Movie> findByParams(Map<String,Object> parmas);
}
