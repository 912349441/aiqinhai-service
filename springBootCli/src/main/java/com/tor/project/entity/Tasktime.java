package com.tor.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Tzx
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("YW_TASKTIME")
public class Tasktime extends Model<Tasktime> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "timeid", type = IdType.AUTO)
    private String timeid;

    private Date tasktime;

    /**
     * 1:参保人员  2：两定机构(医院)  3：医师药师  4：住院病人 5：两定机构(药店)
     */
    private Integer bj;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
