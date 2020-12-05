package com.tor.project.service.impl;

import com.tor.project.entity.Zybr;
import com.tor.project.mapper.primary.ZybrMapper;
import com.tor.project.service.ZybrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 住院病人表 服务实现类
 * </p>
 *
 * @author Tzx
 * @since 2020-12-03
 */
@Service
public class ZybrServiceImpl extends ServiceImpl<ZybrMapper, Zybr> implements ZybrService {

    @Autowired
    private ZybrMapper zybrMapper;

    @Override
    public void saveZybr(Zybr zybr) {
        zybrMapper.saveZybr(zybr);
    }

    @Override
    public void updateZybr(Zybr zybr) {
        zybrMapper.updateZybr(zybr);
    }
}
