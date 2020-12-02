package com.tor.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 海盐测试照片表
 * </p>
 *
 * @author Tzx
 * @since 2020-08-31
 */
@Data
@TableName("YW_JY_PHOTO_VIEW")
public class JyJzzpPhoto extends Model<JyJzzpPhoto> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private String id;

    private String cert_no;

    private String name;

    private byte[] photo;

    private Date update_time;
}
