package com.example.demo.mapper;

import com.example.demo.domain.ClickLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClickLogMapper {
    int insert(@Param("label") String label);
    List<ClickLog> findAll();
}
