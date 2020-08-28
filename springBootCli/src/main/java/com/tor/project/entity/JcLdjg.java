package com.tor.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 两定机构信息表
 * </p>
 *
 * @author Tzx
 * @since 2020-08-27
 */
public class JcLdjg extends Model<JcLdjg> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;

    /**
     * 两定机构代码
     */
    private String jgdm;

    /**
     * 两定机构名称
     */
    private String jgmc;

    /**
     * 两定机构类型
     * 1其他医疗机构
     * 2零售药店3三级医疗机构
     */
    private Double jglx;

    /**
     * 机构类别代码(JC_YLDWLX.LXDM)
     */
    private String jglbdm;

    /**
     * 机构级别代码(JC_YLDWJB.JBDM)
     */
    private String jgjbdm;

    /**
     * 所属区县代码(JC_QX.QXDM)
     */
    private String qxdm;

    /**
     * 两定机构联系地址
     */
    private String lxdz;

    /**
     * 负责人/联系人
     */
    private String lxr;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 备注
     */
    private String bz;

    /**
     * 添加或修改时间
     */
    private LocalDateTime addtime;

    /**
     * 药店是否开通医保监管
     * 0未开通
     * 1已开通
     */
    private Double supervise;

    /**
     * 开通医保监管时间
     */
    private LocalDateTime openingTime;

    /**
     * 是否监管购药0未开通
     * 1已开通
     */
    private Double buyingMedicine;

    /**
     * 是否监管理疗0未开通
     * 1已开通
     */
    private Double physiotherapy;

    /**
     * 是否监管考勤(医师药师)0未开通
     * 1已开通
     */
    private Double attendance;

    /**
     * 机构状态 0：正常 1：暂停医保 2：取消医保 3：关门
     */
    private Double jgzt;

    /**
     * 暂停起时间 JGZT为1时保存该时间
     */
    private LocalDateTime starttime;

    /**
     * 暂停止时间 JGZT为1时保存该时间
     */
    private LocalDateTime endtime;

    /**
     * 暂停状态下的时间间隔
     */
    private String msg;

    /**
     * 最近购药刷卡时间
     */
    private LocalDateTime lastswipecarddate;

    /**
     * 最近连接记录上传时间
     */
    private LocalDateTime lastconnectiondate;

    /**
     * 是否监管住院病人0未开通
     * 1已开通
     */
    private Double inpatient;

    /**
     * 强监管模式（0：弱监管 1：强监管）
     */
    private Double regulatoryModel;

    /**
     * 医保下发的两定机构代码字段
     */
    private String institutionNo;

    /**
     * 厂商id
     */
    private String factoryId;

    /**
     * 厂商名称
     */
    private String factoryName;

    /**
     * 安全key
     */
    private String securityKey;

    /**
     * 是否试点 默认0：否 1：是
     */
    private Double pilot;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJgdm() {
        return jgdm;
    }

    public void setJgdm(String jgdm) {
        this.jgdm = jgdm;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public Double getJglx() {
        return jglx;
    }

    public void setJglx(Double jglx) {
        this.jglx = jglx;
    }

    public String getJglbdm() {
        return jglbdm;
    }

    public void setJglbdm(String jglbdm) {
        this.jglbdm = jglbdm;
    }

    public String getJgjbdm() {
        return jgjbdm;
    }

    public void setJgjbdm(String jgjbdm) {
        this.jgjbdm = jgjbdm;
    }

    public String getQxdm() {
        return qxdm;
    }

    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }

    public String getLxdz() {
        return lxdz;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public LocalDateTime getAddtime() {
        return addtime;
    }

    public void setAddtime(LocalDateTime addtime) {
        this.addtime = addtime;
    }

    public Double getSupervise() {
        return supervise;
    }

    public void setSupervise(Double supervise) {
        this.supervise = supervise;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalDateTime openingTime) {
        this.openingTime = openingTime;
    }

    public Double getBuyingMedicine() {
        return buyingMedicine;
    }

    public void setBuyingMedicine(Double buyingMedicine) {
        this.buyingMedicine = buyingMedicine;
    }

    public Double getPhysiotherapy() {
        return physiotherapy;
    }

    public void setPhysiotherapy(Double physiotherapy) {
        this.physiotherapy = physiotherapy;
    }

    public Double getAttendance() {
        return attendance;
    }

    public void setAttendance(Double attendance) {
        this.attendance = attendance;
    }

    public Double getJgzt() {
        return jgzt;
    }

    public void setJgzt(Double jgzt) {
        this.jgzt = jgzt;
    }

    public LocalDateTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    public LocalDateTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalDateTime endtime) {
        this.endtime = endtime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getLastswipecarddate() {
        return lastswipecarddate;
    }

    public void setLastswipecarddate(LocalDateTime lastswipecarddate) {
        this.lastswipecarddate = lastswipecarddate;
    }

    public LocalDateTime getLastconnectiondate() {
        return lastconnectiondate;
    }

    public void setLastconnectiondate(LocalDateTime lastconnectiondate) {
        this.lastconnectiondate = lastconnectiondate;
    }

    public Double getInpatient() {
        return inpatient;
    }

    public void setInpatient(Double inpatient) {
        this.inpatient = inpatient;
    }

    public Double getRegulatoryModel() {
        return regulatoryModel;
    }

    public void setRegulatoryModel(Double regulatoryModel) {
        this.regulatoryModel = regulatoryModel;
    }

    public String getInstitutionNo() {
        return institutionNo;
    }

    public void setInstitutionNo(String institutionNo) {
        this.institutionNo = institutionNo;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public Double getPilot() {
        return pilot;
    }

    public void setPilot(Double pilot) {
        this.pilot = pilot;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "JcLdjg{" +
                "id=" + id +
                ", jgdm=" + jgdm +
                ", jgmc=" + jgmc +
                ", jglx=" + jglx +
                ", jglbdm=" + jglbdm +
                ", jgjbdm=" + jgjbdm +
                ", qxdm=" + qxdm +
                ", lxdz=" + lxdz +
                ", lxr=" + lxr +
                ", lxdh=" + lxdh +
                ", bz=" + bz +
                ", addtime=" + addtime +
                ", supervise=" + supervise +
                ", openingTime=" + openingTime +
                ", buyingMedicine=" + buyingMedicine +
                ", physiotherapy=" + physiotherapy +
                ", attendance=" + attendance +
                ", jgzt=" + jgzt +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", msg=" + msg +
                ", lastswipecarddate=" + lastswipecarddate +
                ", lastconnectiondate=" + lastconnectiondate +
                ", inpatient=" + inpatient +
                ", regulatoryModel=" + regulatoryModel +
                ", institutionNo=" + institutionNo +
                ", factoryId=" + factoryId +
                ", factoryName=" + factoryName +
                ", securityKey=" + securityKey +
                ", pilot=" + pilot +
                "}";
    }
}
