package com.tor.project.mapper.primary;

import com.tor.project.entity.HyJzzpPhoto;
import com.tor.project.entity.Jzzp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 参保人员信息表-基准照片表 Mapper 接口
 * </p>
 *
 * @author Tzx
 * @since 2020-08-27
 */
@Repository
public interface JzzpMapper extends BaseMapper<Jzzp> {

    /**
     * 获取海盐的照片
     * @param sfzh
     * @param xm
     * @return
     */
    List<HyJzzpPhoto> getHyJzzpZpBlobBySfzhAndXm(@Param("sfz") String sfz, @Param("xm") String xm);

}
