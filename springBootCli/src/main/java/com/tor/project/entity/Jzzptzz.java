package com.tor.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 参保人员特征值表
 * </p>
 *
 * @author Tzx
 * @since 2020-08-31
 */
@TableName("YW_JZZPTZZ")
public class Jzzptzz extends Model<Jzzptzz> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ZPID", type = IdType.INPUT)
    private String zpid;

    private String zptzz;

    private String gxzptzz;

    /**
     * 版本号
     */
    private String bbh;

    private Double tzzseq;


    public String getZpid() {
        return zpid;
    }

    public void setZpid(String zpid) {
        this.zpid = zpid;
    }

    public String getZptzz() {
        return zptzz;
    }

    public void setZptzz(String zptzz) {
        this.zptzz = zptzz;
    }

    public String getGxzptzz() {
        return gxzptzz;
    }

    public void setGxzptzz(String gxzptzz) {
        this.gxzptzz = gxzptzz;
    }

    public String getBbh() {
        return bbh;
    }

    public void setBbh(String bbh) {
        this.bbh = bbh;
    }

    public Double getTzzseq() {
        return tzzseq;
    }

    public void setTzzseq(Double tzzseq) {
        this.tzzseq = tzzseq;
    }

    @Override
    protected Serializable pkVal() {
        return this.zpid;
    }

    @Override
    public String toString() {
        return "Jzzptzz{" +
                "zpid=" + zpid +
                ", zptzz=" + zptzz +
                ", gxzptzz=" + gxzptzz +
                ", bbh=" + bbh +
                ", tzzseq=" + tzzseq +
                "}";
    }
}
