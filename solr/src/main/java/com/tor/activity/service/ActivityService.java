package com.tor.activity.service;

import com.tor.activity.entity.Activity;
import com.tor.common.Pageinfo;

import java.util.List;
import java.util.Map;


public interface ActivityService {

    public List<Activity> findAll();

    public List<Activity> findAllByName(String activityTitle);

    Pageinfo<Activity> getActivityList(Map<String, Object> params, int pageNo, int pageSize);

    /**
     * 根据活动id拿到活动的详情和活动Item以及Apply
     * @param id
     * @return
     */
    Activity selectActivityEdit(String id);

    /**
     * 根据参数Map查询出符合条件的List<Activity>集合
     * @param map
     * @return
     */
    List<Activity> getByParams(Map<String, Object> map);
}
