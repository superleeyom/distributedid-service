package com.leeyom.distributedid.util;

import cn.hutool.core.util.RandomUtil;
import com.leeyom.distributedid.common.enums.Status;
import com.leeyom.distributedid.common.exception.BizException;
import com.leeyom.distributedid.service.DistributedIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分布式id生成工具
 *
 * @author leeyom wang
 * @date 2021/1/8 5:28 下午
 */
@Service
public class DistributedIdUtil {

    @Autowired
    DistributedIdService distributedIdService;

    /**
     * 生成id
     *
     * @return
     */
    public Long generateId() {
        // 从两个数据源进行随机
        int i = RandomUtil.randomInt(1, 3);
        if (i == 1) {
            return distributedIdService.addDistributedIdToMaster1();
        } else if (i == 2) {
            return distributedIdService.addDistributedIdToMaster2();
        }
        throw new BizException(Status.DISTRIBUTED_ID_GENERATE_EXCEPTION);
    }

}
