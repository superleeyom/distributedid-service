package com.leeyom.distributedid.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (DistributedId)
 *
 * @author Leeyom Wang
 * @since 2020-05-30 16:17:33
 */
@Data
public class DistributedId extends Model<DistributedId> implements Serializable {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 额外字段
     */
    @TableField("extra")
    private String extra;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}