package com.cheetah.message.handler.provider.service;

import com.cheetah.message.handler.api.HandlerHolderApi;
import com.cheetah.message.handler.api.handler.Handler;
import org.apache.dubbo.config.annotation.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/18 20:24
 */
@Service
public class HandlerHolderProvider implements HandlerHolderApi {
    private Map<Integer,Handler> handlers = new HashMap<>(128);
    @Override
    public void putHandler(Integer channelCode, Handler handler) {
        handlers.put(channelCode,handler);
    }

    @Override
    public Handler route(Integer channelCode) {
        return handlers.get(channelCode);
    }
}
