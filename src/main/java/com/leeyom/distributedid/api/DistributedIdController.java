package com.leeyom.distributedid.api;


import com.leeyom.distributedid.common.dto.ApiResponse;
import com.leeyom.distributedid.service.DistributedIdService;
import com.leeyom.distributedid.util.DistributedIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (DistributedId)表控制层
 *
 * @author Leeyom Wang
 * @since 2020-05-30 16:17:37
 */
@RestController
public class DistributedIdController {

    @Autowired
    private DistributedIdUtil distributedIdUtil;

    /**
     * 生成主键id
     *
     * @return
     */
    @GetMapping("/generateId")
    public ApiResponse<Long> generateId() {
        return ApiResponse.ofSuccess(distributedIdUtil.generateId());
    }


}