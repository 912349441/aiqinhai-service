package com.tor.project.service;

import com.tor.project.entity.HyJzzpPhoto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 海盐测试照片表 服务类
 * </p>
 *
 * @author Tzx
 * @since 2020-08-31
 */
public interface HyJzzpPhotoService extends IService<HyJzzpPhoto> {

    void saveJzzpPhoto(HyJzzpPhoto hyJzzpPhoto);
}
