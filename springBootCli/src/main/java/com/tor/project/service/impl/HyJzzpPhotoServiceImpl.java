package com.tor.project.service.impl;

import com.tor.project.entity.HyJzzpPhoto;
import com.tor.project.mapper.primary.HyJzzpPhotoMapper;
import com.tor.project.service.HyJzzpPhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 海盐测试照片表 服务实现类
 * </p>
 *
 * @author Tzx
 * @since 2020-08-31
 */
@Service
public class HyJzzpPhotoServiceImpl extends ServiceImpl<HyJzzpPhotoMapper, HyJzzpPhoto> implements HyJzzpPhotoService {

    @Override
    public void saveJzzpPhoto(HyJzzpPhoto hyJzzpPhoto) {
        baseMapper.saveJzzpPhoto(hyJzzpPhoto);
    }
}
