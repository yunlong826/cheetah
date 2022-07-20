package com.cheetah.message.handler.provider.utils;

import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.enums.ChannelType;
import com.cheetah.message.handler.provider.enums.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 标识着每一个消费者组
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 11:58
 */
public class GroupIdMappingUtils {



    /**
     * 获取所有的groupIds
     * （不同的渠道不同的消息类型拥有自己的groupId）
     */
    public static List<String> getAllGroupIds(){
        List<String> groupIds = new ArrayList<>();
        for(ChannelType channelType:ChannelType.values()){
            for(MessageType messageType:MessageType.values()){
                groupIds.add(channelType.getCodeEn() + "." + messageType.getCodeEn());
            }
        }
        return groupIds;
    }

    /**
     * 根据TaskInfo获取当前消息的groupId
     * @param taskInfo
     * @return
     */
    public static String getGroupIdByTaskInfo(TaskInfo taskInfo) {
        String channelCodeEn = ChannelType.getEnumByCode(taskInfo.getSendChannel()).getCodeEn();
        String msgCodeEn = MessageType.getEnumByCode(taskInfo.getMsgType()).getCodeEn();
        return channelCodeEn + "." + msgCodeEn;
    }
}
