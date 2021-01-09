package com.leeyom.distributedid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leeyom.distributedid.domain.entity.DistributedId;

/**
 * (DistributedId)表服务接口
 *
 * @author Leeyom Wang
 * @since 2020-05-30 16:17:36
 */
public interface DistributedIdService extends IService<DistributedId> {

    /**
     * 往主库1增加一条记录
     *
     * @return
     */
    Long addDistributedIdToMaster1();

    /**
     * 往主库2增加一条记录
     *
     * @return
     */
    Long addDistributedIdToMaster2();

}