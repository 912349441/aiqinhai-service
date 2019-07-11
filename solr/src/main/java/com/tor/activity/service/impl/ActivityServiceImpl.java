package com.tor.activity.service.impl;

import com.tor.activity.entity.Activity;
import com.tor.activity.entity.ActivityApply;
import com.tor.activity.entity.ActivityItem;
import com.tor.activity.mapper.ActivityMapper;
import com.tor.activity.service.ActivityService;
import com.tor.common.Pageinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static sun.misc.Version.print;

// @Service要加在Impl实现类上，而调用的时候只需要调用接口就行
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityItemServiceImpl activityItemService;
    @Autowired
    private ActivityApplyServiceImpl activityApplyService;

    @Override
    public List<Activity> findAll() {
        return activityMapper.findAll();
    }

    @Override
    public List<Activity> findAllByName(String activityTitle) {
        return null;
    }

    @Override
    public Pageinfo<Activity> getActivityList(Map<String, Object> params,int pageNo,int pageSize) {
        int totalCount = activityMapper.getTotalCount(params);
        List<Activity> activityList = activityMapper.selectByParams(params,pageNo,pageSize);
        return new Pageinfo<Activity>(pageNo,pageSize,totalCount,activityList);
    }

    @Override
    public Activity selectActivityEdit(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("activityId",id);
        List<ActivityItem> activityItems = activityItemService.selectByParams(map);
        List<ActivityApply> activityApplies = activityApplyService.selectByParams(map);
        Activity activity = activityMapper.selectByPrimaryKey(id);
        activity.setActivityApplyList(activityApplies);
        activity.setActivityItems(activityItems);
        return activity;
    }

    @Override
    public List<Activity> getByParams(Map<String, Object> map) {
        return activityMapper.selectByParams(map,1,99999999);
    }

}
