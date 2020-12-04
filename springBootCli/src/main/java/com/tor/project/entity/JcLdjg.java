package com.tor.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 两定机构信息表
 * </p>
 *
 * @author Tzx
 * @since 2020-08-27
 */
@Getter
@Setter
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
    private Date addtime;

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
}
