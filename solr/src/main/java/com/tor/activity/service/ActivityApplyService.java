package com.tor.activity.service;

import com.tor.activity.entity.Activity;
import com.tor.activity.entity.ActivityApply;
import com.tor.common.Pageinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ActivityApplyService {

    List<ActivityApply> selectByParams(@Param("params") Map<String, Object> map);
}
