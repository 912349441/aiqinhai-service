package com.tor.project.entity;

import java.util.Date;
import javax.persistence.*;

public class Essay {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 文章标题
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 分类ID
     */
    @Column(name = "CATEGORY_ID")
    private String categoryId;

    /**
     * 分类名称
     */
    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    /**
     * 标签名称
     */
    @Column(name = "LABEL")
    private String label;

    /**
     * 状态（0：草稿 ， 1：发布）
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 文章创建时间
     */
    @Column(name = "CREATE_DT")
    private Date createDt;

    /**
     * 最近修改时间
     */
    @Column(name = "UPDATE_DT")
    private Date updateDt;

    /**
     * 文章内容
     */
    @Column(name = "CONTENT")
    private String content;

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
     * 获取文章标题
     *
     * @return TITLE - 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取分类ID
     *
     * @return CATEGORY_ID - 分类ID
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类ID
     *
     * @param categoryId 分类ID
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取分类名称
     *
     * @return CATEGORY_NAME - 分类名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置分类名称
     *
     * @param categoryName 分类名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取标签名称
     *
     * @return LABEL - 标签名称
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置标签名称
     *
     * @param label 标签名称
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取状态（0：草稿 ， 1：发布）
     *
     * @return STATUS - 状态（0：草稿 ， 1：发布）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态（0：草稿 ， 1：发布）
     *
     * @param status 状态（0：草稿 ， 1：发布）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取文章创建时间
     *
     * @return CREATE_DT - 文章创建时间
     */
    public Date getCreateDt() {
        return createDt;
    }

    /**
     * 设置文章创建时间
     *
     * @param createDt 文章创建时间
     */
    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    /**
     * 获取最近修改时间
     *
     * @return UPDATE_DT - 最近修改时间
     */
    public Date getUpdateDt() {
        return updateDt;
    }

    /**
     * 设置最近修改时间
     *
     * @param updateDt 最近修改时间
     */
    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    /**
     * 获取文章内容
     *
     * @return CONTENT - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}