package com.tor.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 医师表
 * </p>
 *
 * @author Tzx
 * @since 2020-07-31
 */
@TableName("YW_YS")
public class Ys extends Model<Ys> {

    private static final long serialVersionUID = 1L;

    /**
     * 医师编号
     */
    @TableId(value = "YSID", type = IdType.AUTO)
    private String ysid;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 身份证号
     */
    private String sfzh;

    /**
     * 工号
     */
    private String gh;

    /**
     * 标准照片路径（/医疗单位编号/身份证号.jpg）
     */
    private String bzzplj;

    /**
     * 是否有效 1：有效（默认） 0：无效
     */
    private Integer sfyx;

    /**
     * 添加时间
     */
    private Date tjsj;

    /**
     * 照片id（管理YW_YSZPTZZ.ZPID）
     */
    private String zpid;

    /**
     * 信息版本号
     */
    private Integer xxbbh;

    /**
     * 照片版本号
     */
    private Integer zpbbh;

    /**
     * 乐观锁版本号
     */
    private Integer version;

    /**
     * 职称ID
     */
    private String yszcid;

    /**
     * 健康证编号
     */
    private String jkzbh;

    /**
     * 执业证号
     */
    private String zyzh;

    /**
     * 特征值状态 0：未作任何处理
     */
    private Integer tzzzt;

    /**
     * 异常信息
     */
    private String msg;


    public String getYsid() {
        return ysid;
    }

    public void setYsid(String ysid) {
        this.ysid = ysid;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getBzzplj() {
        return bzzplj;
    }

    public void setBzzplj(String bzzplj) {
        this.bzzplj = bzzplj;
    }

    public Integer getSfyx() {
        return sfyx;
    }

    public void setSfyx(Integer sfyx) {
        this.sfyx = sfyx;
    }

    public Date getTjsj() {
        return tjsj;
    }

    public void setTjsj(Date tjsj) {
        this.tjsj = tjsj;
    }

    public String getZpid() {
        return zpid;
    }

    public void setZpid(String zpid) {
        this.zpid = zpid;
    }

    public Integer getXxbbh() {
        return xxbbh;
    }

    public void setXxbbh(Integer xxbbh) {
        this.xxbbh = xxbbh;
    }

    public Integer getZpbbh() {
        return zpbbh;
    }

    public void setZpbbh(Integer zpbbh) {
        this.zpbbh = zpbbh;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getYszcid() {
        return yszcid;
    }

    public void setYszcid(String yszcid) {
        this.yszcid = yszcid;
    }

    public String getJkzbh() {
        return jkzbh;
    }

    public void setJkzbh(String jkzbh) {
        this.jkzbh = jkzbh;
    }

    public String getZyzh() {
        return zyzh;
    }

    public void setZyzh(String zyzh) {
        this.zyzh = zyzh;
    }

    public Integer getTzzzt() {
        return tzzzt;
    }

    public void setTzzzt(Integer tzzzt) {
        this.tzzzt = tzzzt;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    protected Serializable pkVal() {
        return this.ysid;
    }

    @Override
    public String toString() {
        return "Ys{" +
        "ysid=" + ysid +
        ", xm=" + xm +
        ", sfzh=" + sfzh +
        ", gh=" + gh +
        ", bzzplj=" + bzzplj +
        ", sfyx=" + sfyx +
        ", tjsj=" + tjsj +
        ", zpid=" + zpid +
        ", xxbbh=" + xxbbh +
        ", zpbbh=" + zpbbh +
        ", version=" + version +
        ", yszcid=" + yszcid +
        ", jkzbh=" + jkzbh +
        ", zyzh=" + zyzh +
        ", tzzzt=" + tzzzt +
        ", msg=" + msg +
        "}";
    }
}
