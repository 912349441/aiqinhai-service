package com.tor.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 住院病人表
 * </p>
 *
 * @author Tzx
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("YW_ZYBR")
public class Zybr extends Model<Zybr> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "ZYBRID", type = IdType.AUTO)
    private String zybrid;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 身份证号
     */
    private String sfzh;

    /**
     * 社保卡号
     */
    private String sbkh;

    /**
     * 参保人员照片编号
     */
    private String zpid;

    /**
     * 入住时间
     */
    private Date rzsj;

    /**
     * 床号
     */
    private String ch;

    /**
     * 是否出院 0：否 （默认）1：是
     */
    private Double sfcy;

    /**
     * 出院时间
     */
    private Date cysj;

    /**
     * （入住的）科室编号
     */
    private String ksid;

    /**
     * （入住的）医疗单位编号
     */
    private String yldwid;

    /**
     * 汉语拼音首字母，多个文字以逗号隔开
     */
    private String pinyin;

    /**
     * 最近巡查时间
     */
    private Date lastpatroltime;

    /**
     * 第二模板照路径
     */
    private String secondtemplatepath;

    /**
     * 第二模板照比对分数
     */
    private Double secondtemplatescore;

    /**
     * 日常巡查总次数
     */
    private Double patroldailytotal;

    /**
     * 日常巡查失败次数
     */
    private Double patroldailyfailed;

    /**
     * 任务巡查总次数
     */
    private Double patroltasktotal;

    /**
     * 任务巡查失败次数
     */
    private Double patroltaskfailed;

    /**
     * 补录标记-1:待补录0:正常1:已补录
     */
    private Double addflag;

    /**
     * 是否需要巡查0:否1:是(注:未满7周岁无需巡查)
     */
    private Double compareflag;

    /**
     * 病人照片是否存在
     */
    private Double photoexists;

    /**
     * 病人住院类别：
	1-普通住院
	2-外伤
	3-生育
	4-老年护理
     */
    private Integer hospitalcategory;

    /**
     * 疾病诊断
     */
    private String diseaseDiagnosis;

    /**
     * 人员性质
     */
    private String naturePersonnel;

    /**
     * 参保单位
     */
    private String insuredUnit;


    @Override
    protected Serializable pkVal() {
        return this.zybrid;
    }

}
