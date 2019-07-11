package com.tor.activity.service.impl;

import com.tor.activity.entity.Activity;
import com.tor.activity.entity.ActivityItem;
import com.tor.activity.mapper.ActivityItemMapper;
import com.tor.activity.mapper.ActivityMapper;
import com.tor.activity.service.ActivityItemService;
import com.tor.activity.service.ActivityService;
import com.tor.common.Pageinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

// @Service要加在Impl实现类上，而调用的时候只需要调用接口就行
@Service
public class ActivityItemServiceImpl implements ActivityItemService {

    @Autowired
    private ActivityItemMapper activityItemMapper;

    @Override
    public List<ActivityItem> selectByParams(Map<String, Object> map) {
        return activityItemMapper.selectByParams(map);
    }
}
