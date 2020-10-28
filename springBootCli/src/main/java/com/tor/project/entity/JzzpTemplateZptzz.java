package com.tor.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 基准人员第二模板照特征值
 * </p>
 *
 * @author Tzx
 * @since 2020-09-02
 */
@TableName("YW_JZZP_TEMPLATE_ZPTZZ")
public class JzzpTemplateZptzz extends Model<JzzpTemplateZptzz> {

    private static final long serialVersionUID = 1L;

    /**
     * 参保人员(持卡人)编号
     * YW_JZZP.ZPID
     */
    @TableId(value = "ZPID", type = IdType.INPUT)
    private String zpid;

    /**
     * 特征值版本号
     */
    private String bbh;

    /**
     * 照片特征值
     */
    private String zptzz;


    public String getZpid() {
        return zpid;
    }

    public void setZpid(String zpid) {
        this.zpid = zpid;
    }

    public String getBbh() {
        return bbh;
    }

    public void setBbh(String bbh) {
        this.bbh = bbh;
    }

    public String getZptzz() {
        return zptzz;
    }

    public void setZptzz(String zptzz) {
        this.zptzz = zptzz;
    }

    @Override
    protected Serializable pkVal() {
        return this.zpid;
    }

    @Override
    public String toString() {
        return "JzzpTemplateZptzz{" +
                "zpid=" + zpid +
                ", bbh=" + bbh +
                ", zptzz=" + zptzz +
                "}";
    }
}
