package com.tor.project.mapper.second;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tor.project.entity.*;
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
     * 同步江阴参保人员照片视图
     */
    void migrateJzppJyPhotoJob();
    List<JyJzzpPhoto> getJyJzzpZpBlobBySfzhAndXm(@Param("cert_no") String cert_no, @Param("name") String name);

    /**
     * 青海参保人员信息
     * @param mykey
     * @param maxSize
     * @return
     */
    List<QhJzzpInfo> getQhJzzpInfoByMykeyStart(@Param("mykey") Integer mykey,@Param("maxSize") Integer maxSize);
}
