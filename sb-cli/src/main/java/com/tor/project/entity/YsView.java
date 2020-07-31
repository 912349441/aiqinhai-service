package com.tor.project.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * (YsView)实体类
 *
 * @author Tzx
 * @since 2020-05-08 09:11:52
 */
@Data
public class YsView implements Serializable {
    private static final long serialVersionUID = 572755927148791576L;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private String jgbm;
    /**
     * 执业证号
     */
    private String zgzbh;
    /**
     * 不知道干什么用的  视图是全是null
     */
    private String jkzbh;
    private String zc;
    private String xm;
    private String sfzh;
    private String lx;
    private String ksrq;
    private String zzrq;
    private String gxsj;
    private String ysjb;

    public void toYs(Ys ys) {

        if (StringUtils.isNotBlank(ys.getYsid()) && (
                (StringUtils.isNotBlank(getZgzbh()) && !getZgzbh().equals(ys.getZyzh())) ||
                        (StringUtils.isNotBlank(getXm()) && !getXm().replaceAll(" ","").equals(ys.getXm())))) {
            ys.setXxbbh(ys.getXxbbh() + 1);
        }
        if (StringUtils.isNotBlank(getZgzbh())) {
            ys.setZyzh(getZgzbh());
        }
        if (StringUtils.isNotBlank(getSfzh())) {
            ys.setSfzh(getSfzh().toUpperCase());
        }
        if (StringUtils.isNotBlank(getXm())) {
            ys.setXm(getXm().replaceAll(" ",""));
        }
        // 如果本地库不是医师
        if(!"C30A6AEBA06C4C3C977E1F082CECA245".equals(ys.getYszcid())){
            // 如果视图库中是医师
            if (StringUtils.isNotBlank(getLx()) && "1".equals(getLx())) {
                ys.setYszcid("C30A6AEBA06C4C3C977E1F082CECA245");
            } else {
                ys.setYszcid("A51E51CED7ED6008E050007F01006723");
            }
        }
        if (null == ys.getTjsj()) {
            ys.setTjsj(new Date());
        }
    }
}