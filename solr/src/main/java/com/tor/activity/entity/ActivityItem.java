package com.tor.activity.entity;

import java.util.Date;

public class ActivityItem {
    private String id;

    private String activityId;

    private String activityItemName;

    private String itemNameSum;

    private String description;

    private Date createDt;

    private String creator;

    private Date updateDt;

    private String updator;

    private String deleteFlag;

    private Integer versionNo;

    private String indexFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getActivityItemName() {
        return activityItemName;
    }

    public void setActivityItemName(String activityItemName) {
        this.activityItemName = activityItemName == null ? null : activityItemName.trim();
    }

    public String getItemNameSum() {
        return itemNameSum;
    }

    public void setItemNameSum(String itemNameSum) {
        this.itemNameSum = itemNameSum == null ? null : itemNameSum.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public Integer getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    public String getIndexFlag() {
        return indexFlag;
    }

    public void setIndexFlag(String indexFlag) {
        this.indexFlag = indexFlag == null ? null : indexFlag.trim();
    }
}