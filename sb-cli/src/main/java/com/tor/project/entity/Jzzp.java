package com.tor.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 参保人员信息表-基准照片表
 * </p>
 *
 * @author Tzx
 * @since 2020-07-31
 */
@TableName("YW_JZZP")
public class Jzzp extends Model<Jzzp> {

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
     * 添加时间,添加时的系统时间
     */
    private Date tjsj;

    /**
     * 是否有效1：有效，默认；0：无效，即属于删除的情况
     */
    private Integer sfyx;

    /**
     * 是否有关系人0：没有，默认；1：有
     */
    private Integer sfygxr;

    /**
     * 备注,记录照片更新情况，包括更新时间、原照片路径
     */
    private String bz;

    /**
     * 更新照片路径
     */
    private String gxzplj;

    /**
     * 更新时间
     */
    private Date gxsj;

    /**
     * 特征值状态0未提取;1已提取;-1无特征值
     */
    private Integer tzzzt;

    /**
     * 参保地区
     */
    private String cbdq;

    /**
     * 第二标准模板照路径
     */
    private String lszplj;

    /**
     * 第二标准模板照比对分数
     */
    private Integer lszpscore;

    /**
     * 无照片原因(0:未丢失1:未满16周岁2:身份证丢失)
     */
    private Integer reasonofphotomissing;

    /**
     * 照片来源(-1:无照片0：其他系统导入1:住院病人读身份证2:住院病人选照片3:无照片、无身份证自动将现场照作为模板照)
     */
    private Integer photosourcetype;

    /**
     * 线程标志
     */
    private Integer fzzd;

    /**
     * 个人编号匹配标志
     */
    private Integer tzzztTemp;

    /**
     * 特征值提取失败信息
     */
    private String msg;

    /**
     * 个人编号
     */
    private String personalnumber;

    /**
     * 计数器
     */
    private Integer notoneself;

    /**
     * 第二模板照更新时间
     */
    private Date lszpUpdateTime;

    /**
     * 修改建模状态（0允许修改 1不允许修改）
     */
    private Integer modelingState;


    public String getZpid() {
        return zpid;
    }

    public void setZpid(String zpid) {
        this.zpid = zpid;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getZplj() {
        return zplj;
    }

    public void setZplj(String zplj) {
        this.zplj = zplj;
    }

    public Date getTjsj() {
        return tjsj;
    }

    public void setTjsj(Date tjsj) {
        this.tjsj = tjsj;
    }

    public Integer getSfyx() {
        return sfyx;
    }

    public void setSfyx(Integer sfyx) {
        this.sfyx = sfyx;
    }

    public Integer getSfygxr() {
        return sfygxr;
    }

    public void setSfygxr(Integer sfygxr) {
        this.sfygxr = sfygxr;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getGxzplj() {
        return gxzplj;
    }

    public void setGxzplj(String gxzplj) {
        this.gxzplj = gxzplj;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public Integer getTzzzt() {
        return tzzzt;
    }

    public void setTzzzt(Integer tzzzt) {
        this.tzzzt = tzzzt;
    }

    public String getCbdq() {
        return cbdq;
    }

    public void setCbdq(String cbdq) {
        this.cbdq = cbdq;
    }

    public String getLszplj() {
        return lszplj;
    }

    public void setLszplj(String lszplj) {
        this.lszplj = lszplj;
    }

    public Integer getLszpscore() {
        return lszpscore;
    }

    public void setLszpscore(Integer lszpscore) {
        this.lszpscore = lszpscore;
    }

    public Integer getReasonofphotomissing() {
        return reasonofphotomissing;
    }

    public void setReasonofphotomissing(Integer reasonofphotomissing) {
        this.reasonofphotomissing = reasonofphotomissing;
    }

    public Integer getPhotosourcetype() {
        return photosourcetype;
    }

    public void setPhotosourcetype(Integer photosourcetype) {
        this.photosourcetype = photosourcetype;
    }

    public Integer getFzzd() {
        return fzzd;
    }

    public void setFzzd(Integer fzzd) {
        this.fzzd = fzzd;
    }

    public Integer getTzzztTemp() {
        return tzzztTemp;
    }

    public void setTzzztTemp(Integer tzzztTemp) {
        this.tzzztTemp = tzzztTemp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPersonalnumber() {
        return personalnumber;
    }

    public void setPersonalnumber(String personalnumber) {
        this.personalnumber = personalnumber;
    }

    public Integer getNotoneself() {
        return notoneself;
    }

    public void setNotoneself(Integer notoneself) {
        this.notoneself = notoneself;
    }

    public Date getLszpUpdateTime() {
        return lszpUpdateTime;
    }

    public void setLszpUpdateTime(Date lszpUpdateTime) {
        this.lszpUpdateTime = lszpUpdateTime;
    }

    public Integer getModelingState() {
        return modelingState;
    }

    public void setModelingState(Integer modelingState) {
        this.modelingState = modelingState;
    }

    @Override
    protected Serializable pkVal() {
        return this.zpid;
    }

    @Override
    public String toString() {
        return "Jzzp{" +
        "zpid=" + zpid +
        ", sfzh=" + sfzh +
        ", xm=" + xm +
        ", sbkh=" + sbkh +
        ", zplj=" + zplj +
        ", tjsj=" + tjsj +
        ", sfyx=" + sfyx +
        ", sfygxr=" + sfygxr +
        ", bz=" + bz +
        ", gxzplj=" + gxzplj +
        ", gxsj=" + gxsj +
        ", tzzzt=" + tzzzt +
        ", cbdq=" + cbdq +
        ", lszplj=" + lszplj +
        ", lszpscore=" + lszpscore +
        ", reasonofphotomissing=" + reasonofphotomissing +
        ", photosourcetype=" + photosourcetype +
        ", fzzd=" + fzzd +
        ", tzzztTemp=" + tzzztTemp +
        ", msg=" + msg +
        ", personalnumber=" + personalnumber +
        ", notoneself=" + notoneself +
        ", lszpUpdateTime=" + lszpUpdateTime +
        ", modelingState=" + modelingState +
        "}";
    }
}
