package com.tor.activity.mapper;

import com.tor.activity.entity.Activity;
import com.tor.activity.entity.ActivityItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActivityItem record);

    int insertSelective(ActivityItem record);

    ActivityItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActivityItem record);

    List<ActivityItem> selectByParams(@Param("params") Map<String, Object> map);
}