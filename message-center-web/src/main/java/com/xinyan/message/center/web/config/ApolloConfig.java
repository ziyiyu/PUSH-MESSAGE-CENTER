package com.xinyan.message.center.web.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author gambo
 * @version 1.0.0
 *          Date  2018/7/23 10:51.
 */
@Configuration
@EnableApolloConfig({"application","XINYAN.EUREKA","OPERATION.redis.zhengxing","XINYAN.OPERATION.DB_PUSH_MESSAGE","XINYAN.PUSH-MESSAGE-kafka"})
//@PropertySource("file:/root/messagecenter/config.properties")
//@PropertySource("file:/git/develop/projects/message-center/configuration/config.properties")
public class ApolloConfig {

}
