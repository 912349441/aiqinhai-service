package com.tor.activity.mapper;

import com.tor.activity.entity.Activity;
import com.tor.activity.entity.ActivityApply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityApplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActivityApply record);

    int insertSelective(ActivityApply record);

    ActivityApply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActivityApply record);

    int updateByPrimaryKey(ActivityApply record);

    List<ActivityApply> selectByParams(@Param("params") Map<String, Object> map);
}