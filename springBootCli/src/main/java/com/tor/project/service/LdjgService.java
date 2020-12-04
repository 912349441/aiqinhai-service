package com.tor.project.service;

import com.tor.project.entity.Ldjg;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 两定机构信息表 服务类
 * </p>
 *
 * @author Tzx
 * @since 2020-12-04
 */
public interface LdjgService extends IService<Ldjg> {

    void saveLdjg(Ldjg ldjg);

    void updateLdjg(Ldjg ldjg);
}
