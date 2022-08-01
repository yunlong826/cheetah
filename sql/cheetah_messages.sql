-- cheetah_messages.message_template definition

CREATE TABLE `message_template` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '标题',
  `audit_status` tinyint NOT NULL DEFAULT '0' COMMENT '当前消息审核状态： 10.待审核 20.审核成功 30.被拒绝',
  `flow_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工单ID',
  `msg_status` tinyint NOT NULL DEFAULT '0' COMMENT '当前消息状态：10.新建 20.停用 30.启用 40.等待发送 50.发送中 60.发送成功 70.发送失败',
  `cron_task_id` bigint DEFAULT NULL COMMENT '定时任务Id (xxl-job-admin返回)',
  `cron_crowd_path` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '定时发送人群的文件路径',
  `expect_push_time` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '期望发送时间：0:立即发送 定时任务以及周期任务:cron表达式',
  `id_type` tinyint NOT NULL DEFAULT '0' COMMENT '消息的发送ID类型：10. userId 20.did 30.手机号 40.openId 50.email 60.企业微信userId',
  `send_channel` tinyint NOT NULL DEFAULT '0' COMMENT '消息发送渠道：10.IM 20.Push 30.短信 40.Email 50.公众号 60.小程序 70.企业微信',
  `template_type` tinyint NOT NULL DEFAULT '0' COMMENT '10.运营类 20.技术类接口调用',
  `msg_type` tinyint NOT NULL DEFAULT '0' COMMENT '10.通知类消息 20.营销类消息 30.验证码类消息',
  `shield_type` tinyint NOT NULL DEFAULT '0' COMMENT '10.夜间不屏蔽 20.夜间屏蔽 30.夜间屏蔽(次日早上9点发送)',
  `msg_content` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '消息内容 占位符用{$var}表示',
  `send_account` tinyint NOT NULL DEFAULT '0' COMMENT '发送账号 一个渠道下可存在多个账号',
  `creator` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `updator` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '更新者',
  `auditor` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '审核人',
  `team` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '业务方团队',
  `proposer` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '业务方',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除：0.不删除 1.删除',
  `created` int NOT NULL DEFAULT '0' COMMENT '创建时间',
  `updated` int NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_channel` (`send_channel`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息模板信息';

INSERT INTO cheetah_messages.message_template (name, audit_status, flow_id, msg_status, cron_crowd_path, expect_push_time, id_type, send_channel, template_type, msg_type, shield_type, msg_content, send_account, creator, updator, auditor, team, proposer, is_deleted, created, updated)
VALUES('买一送十活动', 10, '', 10, '', '', 30, 80, 20, 10, 0, '{"content":"我终于发送成功了呀","url":"","title":"","sendType":"10"}', 10, 'yun', 'yun', 'yun', 'yunyun', 'yunyun', 0, 0, 0);
