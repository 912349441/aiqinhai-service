package com.tor.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 海盐测试照片表
 * </p>
 *
 * @author Tzx
 * @since 2020-08-31
 */
@Data
@TableName("YW_HY_JZZP_PHOTO")
public class HyJzzpPhoto extends Model<HyJzzpPhoto> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private String id;

    private String sfz;

    private String xm;

    private byte[] zp;
}
