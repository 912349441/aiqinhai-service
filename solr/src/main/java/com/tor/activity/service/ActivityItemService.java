package com.tor.activity.service;

import com.tor.activity.entity.Activity;
import com.tor.activity.entity.ActivityItem;
import com.tor.common.Pageinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ActivityItemService {

    List<ActivityItem> selectByParams(@Param("params") Map<String, Object> map);
}
