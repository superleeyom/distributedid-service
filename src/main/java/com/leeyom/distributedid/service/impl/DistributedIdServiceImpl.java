package com.leeyom.distributedid.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leeyom.distributedid.common.enums.Status;
import com.leeyom.distributedid.common.exception.BizException;
import com.leeyom.distributedid.domain.entity.DistributedId;
import com.leeyom.distributedid.mapper.DistributedIdMapper;
import com.leeyom.distributedid.service.DistributedIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (DistributedId)表服务实现类
 *
 * @author Leeyom Wang
 * @since 2020-05-30 16:17:36
 */
@Service
@Slf4j
public class DistributedIdServiceImpl extends ServiceImpl<DistributedIdMapper, DistributedId> implements DistributedIdService {

    @Override
    @DS("master_1")
    public Long addDistributedIdToMaster1() {
        Long distributedId = generate();
        log.info("从master_1生成主键id：{}", distributedId);
        return distributedId;
    }

    private Long generate() {
        DistributedId distributedId = new DistributedId();
        distributedId.setExtra(IdUtil.fastSimpleUUID());
        if (save(distributedId)) {
            return distributedId.getId();
        }
        throw new BizException(Status.DISTRIBUTED_ID_GENERATE_EXCEPTION);
    }

    @Override
    @DS("master_2")
    public Long addDistributedIdToMaster2() {
        Long distributedId = generate();
        log.info("从master_2生成主键id：{}", distributedId);
        return distributedId;
    }
}