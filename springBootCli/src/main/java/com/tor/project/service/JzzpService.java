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
     * 迁移金华参保人员照片作业
     */
    public void migrateJzppJhPhotosJob();

    /**
     * 定时同步海盐参保人员信息
     */
    public void migrateJzppHyInfoJob();
    /**
     * 迁移海盐参保人员照片作业
     */
    public void migrateJzppHyPhotosJob();

    /**
     * 迁移江阴参保人员照片作业
     */
    public void migrateJzppJyPhotosJob();

    /**
     * 迁移江阴参保人员信息
     */
    public void migrateJyJzppInfo();
}
