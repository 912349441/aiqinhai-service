package com.tor.activity.entity;

import java.util.Date;
import java.util.List;

public class Activity {
    private String id;

    private String activities;

    private String activitiesPic;

    private String activityBak;

    private Date activityEndDt;

    private Date activityStartDt;

    private String activityStatus;

    private String activitySum;

    private String activityTitle;

    private String activityType;

    private String addr;

    private String applyQrCode;

    private String auditBy;

    private Date auditDt;

    private String auditOpinion;

    private String auditState;

    private String businessModel;

    private String city;

    private String contacts;

    private Date createDt;

    private String creator;

    private String deleteFlag;

    private String demoFlag;

    private String freeFlag;

    private String indexFlag;

    private String invoice;

    private String memberId;

    private String ownerId;

    private String ownerName;

    private Integer pageviews;

    private String province;

    private String registeredFund;

    private String serviceArea;

    private String signQrCode;

    private String site;

    private String system;

    private String tel;

    private String titleTag;

    private Date updateDt;

    private String updator;

    private Integer versionNo;

    private String website;

    private List<ActivityItem> activityItems;

    private List<ActivityApply> activityApplyList;

    public List<ActivityApply> getActivityApplyList() {
        return activityApplyList;
    }

    public void setActivityApplyList(List<ActivityApply> activityApplyList) {
        this.activityApplyList = activityApplyList;
    }

    public List<ActivityItem> getActivityItems() {
        return activityItems;
    }

    public void setActivityItems(List<ActivityItem> activityItems) {
        this.activityItems = activityItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities == null ? null : activities.trim();
    }

    public String getActivitiesPic() {
        return activitiesPic;
    }

    public void setActivitiesPic(String activitiesPic) {
        this.activitiesPic = activitiesPic == null ? null : activitiesPic.trim();
    }

    public String getActivityBak() {
        return activityBak;
    }

    public void setActivityBak(String activityBak) {
        this.activityBak = activityBak == null ? null : activityBak.trim();
    }

    public Date getActivityEndDt() {
        return activityEndDt;
    }

    public void setActivityEndDt(Date activityEndDt) {
        this.activityEndDt = activityEndDt;
    }

    public Date getActivityStartDt() {
        return activityStartDt;
    }

    public void setActivityStartDt(Date activityStartDt) {
        this.activityStartDt = activityStartDt;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus == null ? null : activityStatus.trim();
    }

    public String getActivitySum() {
        return activitySum;
    }

    public void setActivitySum(String activitySum) {
        this.activitySum = activitySum == null ? null : activitySum.trim();
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle == null ? null : activityTitle.trim();
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType == null ? null : activityType.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getApplyQrCode() {
        return applyQrCode;
    }

    public void setApplyQrCode(String applyQrCode) {
        this.applyQrCode = applyQrCode == null ? null : applyQrCode.trim();
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy == null ? null : auditBy.trim();
    }

    public Date getAuditDt() {
        return auditDt;
    }

    public void setAuditDt(Date auditDt) {
        this.auditDt = auditDt;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion == null ? null : auditOpinion.trim();
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState == null ? null : auditState.trim();
    }

    public String getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(String businessModel) {
        this.businessModel = businessModel == null ? null : businessModel.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public String getDemoFlag() {
        return demoFlag;
    }

    public void setDemoFlag(String demoFlag) {
        this.demoFlag = demoFlag == null ? null : demoFlag.trim();
    }

    public String getFreeFlag() {
        return freeFlag;
    }

    public void setFreeFlag(String freeFlag) {
        this.freeFlag = freeFlag == null ? null : freeFlag.trim();
    }

    public String getIndexFlag() {
        return indexFlag;
    }

    public void setIndexFlag(String indexFlag) {
        this.indexFlag = indexFlag == null ? null : indexFlag.trim();
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice == null ? null : invoice.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public Integer getPageviews() {
        return pageviews;
    }

    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getRegisteredFund() {
        return registeredFund;
    }

    public void setRegisteredFund(String registeredFund) {
        this.registeredFund = registeredFund == null ? null : registeredFund.trim();
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea == null ? null : serviceArea.trim();
    }

    public String getSignQrCode() {
        return signQrCode;
    }

    public void setSignQrCode(String signQrCode) {
        this.signQrCode = signQrCode == null ? null : signQrCode.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system == null ? null : system.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getTitleTag() {
        return titleTag;
    }

    public void setTitleTag(String titleTag) {
        this.titleTag = titleTag == null ? null : titleTag.trim();
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public Integer getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }
}