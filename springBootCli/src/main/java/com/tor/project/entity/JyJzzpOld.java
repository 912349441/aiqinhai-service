package com.tor.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 参保人员信息表-基准照片表
 * </p>
 *
 * @author Tzx
 * @since 2020-08-27
 */
@Data
@TableName("YW_JZZP")
public class JyJzzpOld extends Model<JyJzzpOld> {

    private static final long serialVersionUID = 1L;

    /**
     * 照片编号（自增）
     */
    @TableId(value = "ZPID", type = IdType.AUTO)
    private String zpid;

    /**
     * 身份证号
     */
    private String sfzh;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 社保卡号或医保号
     */
    private String sbkh;

    /**
     * (基准)照片路径
     */
    private String zplj;

    /**
     * 第二标准模板照路径
     */
    private String lszplj;

}
