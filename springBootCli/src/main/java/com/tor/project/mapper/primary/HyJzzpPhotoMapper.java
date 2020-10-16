package com.tor.project.mapper.primary;

import com.tor.project.entity.HyJzzpPhoto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 海盐测试照片表 Mapper 接口
 * </p>
 *
 * @author Tzx
 * @since 2020-08-31
 */
public interface HyJzzpPhotoMapper extends BaseMapper<HyJzzpPhoto> {

    void saveJzzpPhoto(HyJzzpPhoto hyJzzpPhoto);
}
