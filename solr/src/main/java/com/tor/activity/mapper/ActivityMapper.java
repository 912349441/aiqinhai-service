package com.tor.activity.mapper;

import com.tor.activity.entity.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityMapper {
    int deleteByPrimaryKey(String id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    int getTotalCount(@Param("params") Map<String, Object> map);

    List<Activity> findAll();

    List<Activity> selectByParams(@Param("params") Map<String, Object> map, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

}