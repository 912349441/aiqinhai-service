package com.tor.project.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tcz_activity")
public class TczActivity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 创建者ID
     */
    @Column(name = "MEMBER_ID")
    private String memberId;

    /**
     * 所属采购商ID
     */
    @Column(name = "OWNER_ID")
    private String ownerId;

    /**
     * 所属采购商名称
     */
    @Column(name = "OWNER_NAME")
    private String ownerName;

    @Column(name = "CHANNEL_CODE")
    private String channelCode;

    /**
     * 所属系统code
     */
    @Column(name = "SYSTEM")
    private String system;

    /**
     * 活动类型A招募集采B集采采购C采购商会务D供应商会务E折扣活动
     */
    @Column(name = "ACTIVITY_TYPE")
    private String activityType;

    /**
     * 活动标题(会议标题)
     */
    @Column(name = "ACTIVITY_TITLE")
    private String activityTitle;

    /**
     * 活动标签（逗号分隔开）
     */
    @Column(name = "TITLE_TAG")
    private String titleTag;

    /**
     * 活动总金额,会议费用,采购金额
     */
    @Column(name = "ACTIVITY_SUM")
    private String activitySum;

    /**
     * 活动介绍(主办方介绍,补充说明,店铺要求）
     */
    @Column(name = "ACTIVITIES")
    private String activities;

    /**
     * 活动状态0初始状态1进行中，2结束，3暂停，4,待审核5,审核不通过
     */
    @Column(name = "ACTIVITY_STATUS")
    private String activityStatus;

    /**
     * 开始时间
     */
    @Column(name = "ACTIVITY_START_DT")
    private Date activityStartDt;

    /**
     * 结束时间
     */
    @Column(name = "ACTIVITY_END_DT")
    private Date activityEndDt;

    /**
     * 简介图片(会议封面）
     */
    @Column(name = "ACTIVITIES_PIC")
    private String activitiesPic;

    /**
     * 是否免费(暂时无用)
     */
    @Column(name = "FREE_FLAG")
    private String freeFlag;

    /**
     * 联系人
     */
    @Column(name = "CONTACTS")
    private String contacts;

    /**
     * 省
     */
    @Column(name = "PROVINCE")
    private String province;

    /**
     * 市
     */
    @Column(name = "CITY")
    private String city;

    /**
     * 会议地点
     */
    @Column(name = "SITE")
    private String site;

    /**
     * 详细地址
     */
    @Column(name = "ADDR")
    private String addr;

    /**
     * 官网
     */
    @Column(name = "WEBSITE")
    private String website;

    /**
     * 联系电话
     */
    @Column(name = "TEL")
    private String tel;

    /**
     * 发票要求(增值税专用发票, 增值税普通发票,不用发票)
     */
    @Column(name = "INVOICE")
    private String invoice;

    /**
     * 经营模式:厂家  代理商/经销商
     */
    @Column(name = "BUSINESS_MODEL")
    private String businessModel;

    /**
     * 服务区域
     */
    @Column(name = "SERVICE_AREA")
    private String serviceArea;

    /**
     * 注册资金
     */
    @Column(name = "REGISTERED_FUND")
    private String registeredFund;

    /**
     * 报名二维码
     */
    @Column(name = "APPLY_QR_CODE")
    private String applyQrCode;

    /**
     * 签到二维码
     */
    @Column(name = "SIGN_QR_CODE")
    private String signQrCode;

    /**
     * 页面访问量
     */
    @Column(name = "PAGEVIEWS")
    private Integer pageviews;

    /**
     * 审核状态 为Y时显示
     */
    @Column(name = "AUDIT_STATE")
    private String auditState;

    /**
     * 审核人
     */
    @Column(name = "AUDIT_BY")
    private String auditBy;

    /**
     * 审核时间
     */
    @Column(name = "AUDIT_DT")
    private Date auditDt;

    /**
     * 审核意见
     */
    @Column(name = "AUDIT_OPINION")
    private String auditOpinion;

    /**
     * 发布时间
     */
    @Column(name = "CREATE_DT")
    private Date createDt;

    @Column(name = "CREATOR")
    private String creator;

    @Column(name = "UPDATE_DT")
    private Date updateDt;

    @Column(name = "UPDATOR")
    private String updator;

    @Column(name = "DELETE_FLAG")
    private String deleteFlag;

    @Column(name = "VERSION_NO")
    private Integer versionNo;

    @Column(name = "INDEX_FLAG")
    private String indexFlag;

    @Column(name = "DEMO_FLAG")
    private String demoFlag;

    /**
     * 活动详细(会议详情)
     */
    @Column(name = "ACTIVITY_BAK")
    private String activityBak;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取创建者ID
     *
     * @return MEMBER_ID - 创建者ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 设置创建者ID
     *
     * @param memberId 创建者ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取所属采购商ID
     *
     * @return OWNER_ID - 所属采购商ID
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * 设置所属采购商ID
     *
     * @param ownerId 所属采购商ID
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取所属采购商名称
     *
     * @return OWNER_NAME - 所属采购商名称
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 设置所属采购商名称
     *
     * @param ownerName 所属采购商名称
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return CHANNEL_CODE
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * @param channelCode
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * 获取所属系统code
     *
     * @return SYSTEM - 所属系统code
     */
    public String getSystem() {
        return system;
    }

    /**
     * 设置所属系统code
     *
     * @param system 所属系统code
     */
    public void setSystem(String system) {
        this.system = system;
    }

    /**
     * 获取活动类型A招募集采B集采采购C采购商会务D供应商会务E折扣活动
     *
     * @return ACTIVITY_TYPE - 活动类型A招募集采B集采采购C采购商会务D供应商会务E折扣活动
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * 设置活动类型A招募集采B集采采购C采购商会务D供应商会务E折扣活动
     *
     * @param activityType 活动类型A招募集采B集采采购C采购商会务D供应商会务E折扣活动
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    /**
     * 获取活动标题(会议标题)
     *
     * @return ACTIVITY_TITLE - 活动标题(会议标题)
     */
    public String getActivityTitle() {
        return activityTitle;
    }

    /**
     * 设置活动标题(会议标题)
     *
     * @param activityTitle 活动标题(会议标题)
     */
    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    /**
     * 获取活动标签（逗号分隔开）
     *
     * @return TITLE_TAG - 活动标签（逗号分隔开）
     */
    public String getTitleTag() {
        return titleTag;
    }

    /**
     * 设置活动标签（逗号分隔开）
     *
     * @param titleTag 活动标签（逗号分隔开）
     */
    public void setTitleTag(String titleTag) {
        this.titleTag = titleTag;
    }

    /**
     * 获取活动总金额,会议费用,采购金额
     *
     * @return ACTIVITY_SUM - 活动总金额,会议费用,采购金额
     */
    public String getActivitySum() {
        return activitySum;
    }

    /**
     * 设置活动总金额,会议费用,采购金额
     *
     * @param activitySum 活动总金额,会议费用,采购金额
     */
    public void setActivitySum(String activitySum) {
        this.activitySum = activitySum;
    }

    /**
     * 获取活动介绍(主办方介绍,补充说明,店铺要求）
     *
     * @return ACTIVITIES - 活动介绍(主办方介绍,补充说明,店铺要求）
     */
    public String getActivities() {
        return activities;
    }

    /**
     * 设置活动介绍(主办方介绍,补充说明,店铺要求）
     *
     * @param activities 活动介绍(主办方介绍,补充说明,店铺要求）
     */
    public void setActivities(String activities) {
        this.activities = activities;
    }

    /**
     * 获取活动状态0初始状态1进行中，2结束，3暂停，4,待审核5,审核不通过
     *
     * @return ACTIVITY_STATUS - 活动状态0初始状态1进行中，2结束，3暂停，4,待审核5,审核不通过
     */
    public String getActivityStatus() {
        return activityStatus;
    }

    /**
     * 设置活动状态0初始状态1进行中，2结束，3暂停，4,待审核5,审核不通过
     *
     * @param activityStatus 活动状态0初始状态1进行中，2结束，3暂停，4,待审核5,审核不通过
     */
    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    /**
     * 获取开始时间
     *
     * @return ACTIVITY_START_DT - 开始时间
     */
    public Date getActivityStartDt() {
        return activityStartDt;
    }

    /**
     * 设置开始时间
     *
     * @param activityStartDt 开始时间
     */
    public void setActivityStartDt(Date activityStartDt) {
        this.activityStartDt = activityStartDt;
    }

    /**
     * 获取结束时间
     *
     * @return ACTIVITY_END_DT - 结束时间
     */
    public Date getActivityEndDt() {
        return activityEndDt;
    }

    /**
     * 设置结束时间
     *
     * @param activityEndDt 结束时间
     */
    public void setActivityEndDt(Date activityEndDt) {
        this.activityEndDt = activityEndDt;
    }

    /**
     * 获取简介图片(会议封面）
     *
     * @return ACTIVITIES_PIC - 简介图片(会议封面）
     */
    public String getActivitiesPic() {
        return activitiesPic;
    }

    /**
     * 设置简介图片(会议封面）
     *
     * @param activitiesPic 简介图片(会议封面）
     */
    public void setActivitiesPic(String activitiesPic) {
        this.activitiesPic = activitiesPic;
    }

    /**
     * 获取是否免费(暂时无用)
     *
     * @return FREE_FLAG - 是否免费(暂时无用)
     */
    public String getFreeFlag() {
        return freeFlag;
    }

    /**
     * 设置是否免费(暂时无用)
     *
     * @param freeFlag 是否免费(暂时无用)
     */
    public void setFreeFlag(String freeFlag) {
        this.freeFlag = freeFlag;
    }

    /**
     * 获取联系人
     *
     * @return CONTACTS - 联系人
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * 设置联系人
     *
     * @param contacts 联系人
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * 获取省
     *
     * @return PROVINCE - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return CITY - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取会议地点
     *
     * @return SITE - 会议地点
     */
    public String getSite() {
        return site;
    }

    /**
     * 设置会议地点
     *
     * @param site 会议地点
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * 获取详细地址
     *
     * @return ADDR - 详细地址
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 设置详细地址
     *
     * @param addr 详细地址
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * 获取官网
     *
     * @return WEBSITE - 官网
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 设置官网
     *
     * @param website 官网
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 获取联系电话
     *
     * @return TEL - 联系电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置联系电话
     *
     * @param tel 联系电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取发票要求(增值税专用发票, 增值税普通发票,不用发票)
     *
     * @return INVOICE - 发票要求(增值税专用发票, 增值税普通发票,不用发票)
     */
    public String getInvoice() {
        return invoice;
    }

    /**
     * 设置发票要求(增值税专用发票, 增值税普通发票,不用发票)
     *
     * @param invoice 发票要求(增值税专用发票, 增值税普通发票,不用发票)
     */
    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    /**
     * 获取经营模式:厂家  代理商/经销商
     *
     * @return BUSINESS_MODEL - 经营模式:厂家  代理商/经销商
     */
    public String getBusinessModel() {
        return businessModel;
    }

    /**
     * 设置经营模式:厂家  代理商/经销商
     *
     * @param businessModel 经营模式:厂家  代理商/经销商
     */
    public void setBusinessModel(String businessModel) {
        this.businessModel = businessModel;
    }

    /**
     * 获取服务区域
     *
     * @return SERVICE_AREA - 服务区域
     */
    public String getServiceArea() {
        return serviceArea;
    }

    /**
     * 设置服务区域
     *
     * @param serviceArea 服务区域
     */
    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    /**
     * 获取注册资金
     *
     * @return REGISTERED_FUND - 注册资金
     */
    public String getRegisteredFund() {
        return registeredFund;
    }

    /**
     * 设置注册资金
     *
     * @param registeredFund 注册资金
     */
    public void setRegisteredFund(String registeredFund) {
        this.registeredFund = registeredFund;
    }

    /**
     * 获取报名二维码
     *
     * @return APPLY_QR_CODE - 报名二维码
     */
    public String getApplyQrCode() {
        return applyQrCode;
    }

    /**
     * 设置报名二维码
     *
     * @param applyQrCode 报名二维码
     */
    public void setApplyQrCode(String applyQrCode) {
        this.applyQrCode = applyQrCode;
    }

    /**
     * 获取签到二维码
     *
     * @return SIGN_QR_CODE - 签到二维码
     */
    public String getSignQrCode() {
        return signQrCode;
    }

    /**
     * 设置签到二维码
     *
     * @param signQrCode 签到二维码
     */
    public void setSignQrCode(String signQrCode) {
        this.signQrCode = signQrCode;
    }

    /**
     * 获取页面访问量
     *
     * @return PAGEVIEWS - 页面访问量
     */
    public Integer getPageviews() {
        return pageviews;
    }

    /**
     * 设置页面访问量
     *
     * @param pageviews 页面访问量
     */
    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }

    /**
     * 获取审核状态 为Y时显示
     *
     * @return AUDIT_STATE - 审核状态 为Y时显示
     */
    public String getAuditState() {
        return auditState;
    }

    /**
     * 设置审核状态 为Y时显示
     *
     * @param auditState 审核状态 为Y时显示
     */
    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    /**
     * 获取审核人
     *
     * @return AUDIT_BY - 审核人
     */
    public String getAuditBy() {
        return auditBy;
    }

    /**
     * 设置审核人
     *
     * @param auditBy 审核人
     */
    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    /**
     * 获取审核时间
     *
     * @return AUDIT_DT - 审核时间
     */
    public Date getAuditDt() {
        return auditDt;
    }

    /**
     * 设置审核时间
     *
     * @param auditDt 审核时间
     */
    public void setAuditDt(Date auditDt) {
        this.auditDt = auditDt;
    }

    /**
     * 获取审核意见
     *
     * @return AUDIT_OPINION - 审核意见
     */
    public String getAuditOpinion() {
        return auditOpinion;
    }

    /**
     * 设置审核意见
     *
     * @param auditOpinion 审核意见
     */
    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    /**
     * 获取发布时间
     *
     * @return CREATE_DT - 发布时间
     */
    public Date getCreateDt() {
        return createDt;
    }

    /**
     * 设置发布时间
     *
     * @param createDt 发布时间
     */
    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    /**
     * @return CREATOR
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return UPDATE_DT
     */
    public Date getUpdateDt() {
        return updateDt;
    }

    /**
     * @param updateDt
     */
    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    /**
     * @return UPDATOR
     */
    public String getUpdator() {
        return updator;
    }

    /**
     * @param updator
     */
    public void setUpdator(String updator) {
        this.updator = updator;
    }

    /**
     * @return DELETE_FLAG
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @return VERSION_NO
     */
    public Integer getVersionNo() {
        return versionNo;
    }

    /**
     * @param versionNo
     */
    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * @return INDEX_FLAG
     */
    public String getIndexFlag() {
        return indexFlag;
    }

    /**
     * @param indexFlag
     */
    public void setIndexFlag(String indexFlag) {
        this.indexFlag = indexFlag;
    }

    /**
     * @return DEMO_FLAG
     */
    public String getDemoFlag() {
        return demoFlag;
    }

    /**
     * @param demoFlag
     */
    public void setDemoFlag(String demoFlag) {
        this.demoFlag = demoFlag;
    }

    /**
     * 获取活动详细(会议详情)
     *
     * @return ACTIVITY_BAK - 活动详细(会议详情)
     */
    public String getActivityBak() {
        return activityBak;
    }

    /**
     * 设置活动详细(会议详情)
     *
     * @param activityBak 活动详细(会议详情)
     */
    public void setActivityBak(String activityBak) {
        this.activityBak = activityBak;
    }
}