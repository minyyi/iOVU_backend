package com.iovu.iovuback.mapper;

import com.iovu.iovuback.domain.Dashboard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DashboardMapper {
    Dashboard getDashboard(@Param("reportId") Long reportId);
}
