package com.tor.project.service;

import com.tor.project.entity.Jzzp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 参保人员信息表-基准照片表 服务类
 * </p>
 *
 * @author Tzx
 * @since 2020-08-27
 */
public interface JzzpService extends IService<Jzzp> {

    /**
     * 迁移参保人员照片作业
     */
    public void migrateJzppJhPhotosJob();

    public void migrateJzppHyPhotosJob();
}
