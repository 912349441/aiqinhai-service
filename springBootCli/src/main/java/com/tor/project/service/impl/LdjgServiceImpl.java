package com.tor.project.service.impl;

import com.tor.project.entity.Ldjg;
import com.tor.project.mapper.primary.LdjgMapper;
import com.tor.project.service.LdjgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 两定机构信息表 服务实现类
 * </p>
 *
 * @author Tzx
 * @since 2020-08-27
 */
@Service
public class LdjgServiceImpl extends ServiceImpl<LdjgMapper, Ldjg> implements LdjgService {

    @Autowired
    private LdjgMapper ldjgMapper;

    @Override
    public void saveLdjg(Ldjg ldjg) {
        ldjgMapper.saveLdjg(ldjg);
    }

    @Override
    public void updateLdjg(Ldjg ldjg) {
        ldjgMapper.updateLdjg(ldjg);
    }
}
