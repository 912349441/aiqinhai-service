package com.tor.sys.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_config")
public class Config {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 键
     */
    private String k;

    /**
     * 值
     */
    private String v;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    @Column(name = "kvType")
    private Integer kvtype;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取键
     *
     * @return k - 键
     */
    public String getK() {
        return k;
    }

    /**
     * 设置键
     *
     * @param k 键
     */
    public void setK(String k) {
        this.k = k;
    }

    /**
     * 获取值
     *
     * @return v - 值
     */
    public String getV() {
        return v;
    }

    /**
     * 设置值
     *
     * @param v 值
     */
    public void setV(String v) {
        this.v = v;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间
     *
     * @return createTime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return kvType
     */
    public Integer getKvtype() {
        return kvtype;
    }

    /**
     * @param kvtype
     */
    public void setKvtype(Integer kvtype) {
        this.kvtype = kvtype;
    }
}