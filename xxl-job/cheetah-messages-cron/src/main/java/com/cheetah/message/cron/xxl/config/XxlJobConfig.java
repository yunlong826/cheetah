package com.cheetah.message.cron.xxl.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/19 21:16
 */
@Configuration
public class XxlJobConfig {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    @NacosValue(value = "${xxl.job.admin.addresses}",autoRefreshed = true)
    private String adminAddresses;

    @NacosValue(value = "${xxl.job.accessToken}",autoRefreshed = true)
    private String accessToken;

    @NacosValue(value = "${xxl.job.executor.appname}",autoRefreshed = true)
    private String appname;

    @NacosValue(value = "${xxl.job.executor.address}",autoRefreshed = true)
    private String address;

    @NacosValue(value = "${xxl.job.executor.ip}",autoRefreshed = true)
    private String ip;

    @NacosValue(value = "${xxl.job.executor.port}",autoRefreshed = true)
    private int port;

    @NacosValue(value = "${xxl.job.executor.logpath}",autoRefreshed = true)
    private String logPath;

    @NacosValue(value = "${xxl.job.executor.logretentiondays}",autoRefreshed = true)
    private int logRetentionDays;


    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setAddress(address);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobSpringExecutor;
    }

    /**
     * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
     *
     *      1、引入依赖：
     *          <dependency>
     *             <groupId>org.springframework.cloud</groupId>
     *             <artifactId>spring-cloud-commons</artifactId>
     *             <version>${version}</version>
     *         </dependency>
     *
     *      2、配置文件，或者容器启动变量
     *          spring.cloud.inetutils.preferred-networks: 'xxx.xxx.xxx.'
     *
     *      3、获取IP
     *          String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
     */
}
