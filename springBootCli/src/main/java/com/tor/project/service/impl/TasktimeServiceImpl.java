package com.tor.project.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.tor.project.entity.Tasktime;
import com.tor.project.mapper.primary.TasktimeMapper;
import com.tor.project.service.TasktimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tzx
 * @since 2020-12-02
 */
@Service
public class TasktimeServiceImpl extends ServiceImpl<TasktimeMapper, Tasktime> implements TasktimeService {

    public boolean updateById(Tasktime tasktime){
        if(ObjectUtil.isNull(tasktime) || StringUtils.isBlank(tasktime.getTimeid()) || ObjectUtil.isNull(tasktime.getTasktime())){
            return false;
        }
        if(tasktime.getTasktime().getTime() > new Date().getTime()){
            return false;
        }
        return super.updateById(tasktime);
    }
}
