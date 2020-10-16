package com.tor.project.mapper.second;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tor.project.entity.HyJzzpPhoto;
import com.tor.project.entity.JyJzzpOld;
import com.tor.project.entity.Jzzp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 参保人员信息表-基准照片表 Mapper 接口
 * </p>
 *
 * @author Tzx
 * @since 2020-08-27
 */
@Repository
public interface JyJzzpOldMapper extends BaseMapper<JyJzzpOld> {

    /**
     * 获取江阴的照片
     * @param sfzh
     * @param xm
     * @return
     */
    List<JyJzzpOld> getJyJzzpBySfzhAndXm(@Param("sfzh") String sfzh, @Param("xm") String xm);
}
