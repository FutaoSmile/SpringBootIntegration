package com.futao.springmvcdemo.service.notbusiness;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.entity.ApiControllerDescription;
import com.futao.springmvcdemo.model.system.ErrorMessage;

import java.util.ArrayList;

/**
 * @author futao
 * Created on 2019-01-14.
 */
public final class SentinelService {

    /**
     * 返回值必须与原函数一致,否则会报找不到method的错误
     *
     * @param ex
     * @return
     */
    public static ArrayList<ApiControllerDescription> handler(BlockException ex) {
        throw LogicException.le(ErrorMessage.VISIT_TOO_FREQUENTLY);
    }
}
