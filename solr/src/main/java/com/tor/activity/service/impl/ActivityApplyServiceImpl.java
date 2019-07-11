package com.tor.activity.service.impl;

import com.tor.activity.entity.Activity;
import com.tor.activity.entity.ActivityApply;
import com.tor.activity.mapper.ActivityApplyMapper;
import com.tor.activity.mapper.ActivityMapper;
import com.tor.activity.service.ActivityApplyService;
import com.tor.activity.service.ActivityService;
import com.tor.common.Pageinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

// @Service要加在Impl实现类上，而调用的时候只需要调用接口就行
@Service
public class ActivityApplyServiceImpl implements ActivityApplyService {

    @Autowired
    private ActivityApplyMapper activityApplyMapper;

    @Override
    public List<ActivityApply> selectByParams(Map<String, Object> map) {
        return activityApplyMapper.selectByParams(map);
    }
}
