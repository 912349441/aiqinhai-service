package com.tor.project.mapper.primary;

import com.tor.project.entity.Ldjg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 两定机构信息表 Mapper 接口
 * </p>
 *
 * @author Tzx
 * @since 2020-12-04
 */
@Repository
public interface LdjgMapper extends BaseMapper<Ldjg> {

    /**
     * 保存两定机构
     * @param ldjg
     */
    void saveLdjg(Ldjg ldjg);

    void updateLdjg(Ldjg ldjg);
}
