package com.tor.project.mapper.primary;

import com.tor.project.entity.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
     * 同步海盐参保人员信息
     * @return
     */
    List<HyJzzpPhoto> migrateJzppHyInfoJob();

    /**
     * 同步海盐照片到我们库
     * @return
     */
    List<HyJzzpPhoto> migrateJzppHyPhotoJob();
    /**
     * 获取海盐的照片
     * @param sfzh
     * @param xm
     * @return
     */
    List<HyJzzpPhoto> getHyJzzpZpBlobBySfzhAndXm(@Param("sfz") String sfz, @Param("xm") String xm);

    /**
     * 同步江阴参保人员、两定机构、住院病人信息
     */
    void migrateJyJzppInfo();
    void migrateJyLdjgInfo();
    void migrateJyZybrkSInfo();
    void migrateJyZybrChInfo();
    void migrateJyZybrInfo();
    void migrateJyZybrCyInfo();

    /**
     * 青海参保人员信息
     * @param mykey
     * @param maxSize
     * @return
     */
    List<QhJzzpInfo> getQhJzzpInfoByGxsj(@Param("gxsj") String gxsj, @Param("maxSize") Integer maxSize);

    /**
     * 青海两定机构信息
     * @param gxsj
     * @param maxSize
     * @return
     */
    List<Ldjg> getQhLdjgInfoByGxsj(@Param("gxsj") String gxsj, @Param("maxSize") Integer maxSize);

    /**
     * 入院
     * @return
     */
    List<QhZybrInfo> getQhZybrInfo();

    /**
     * 出院
     * @param jgdm
     * @param grbh
     * @param rysj
     * @return
     */
    List<QhZybrInfo> getQhZybrCyInfo(@Param("jgdm") String jgdm,@Param("grbh") String grbh, @Param("rysj") Date rysj);


    void saveJzzp(Jzzp jzzp);
}
