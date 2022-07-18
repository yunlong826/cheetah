package com.cheetah.message.handler.api;

import com.cheetah.message.handler.api.handler.Handler;

/**
 * @author jack_yun
 * @version 1.0
 * @description: channel->Handler的映射关系
 * @date 2022/7/18 20:20
 */
public interface HandlerHolderApi {

    void putHandler(Integer channelCode, Handler handler);


    Handler route(Integer channelCode);
}
