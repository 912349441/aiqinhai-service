package com.tor.project.mapper.primary;

import com.tor.project.entity.Zybr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 住院病人表 Mapper 接口
 * </p>
 *
 * @author Tzx
 * @since 2020-12-03
 */
@Repository
public interface ZybrMapper extends BaseMapper<Zybr> {

    void saveZybr(Zybr zybr);
    void updateZybr(Zybr zybr);

}
