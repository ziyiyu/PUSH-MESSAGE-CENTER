package com.xinyan.message.center.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 应用启动入口
 *
 * @author gambo
 * @version 1.0.0
 */
@EnableEurekaClient
@EnableAsync
@Configuration
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xinyan.message")
@SpringBootApplication(scanBasePackages = {"com.xinyan.message.center"},exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ImportResource(value = "classpath:spring-redis.xml")
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        //加载spring上下文
        log.info("push-message-center started!!! swagger url: 21102/push-message-center/swagger-ui.html");
    }

    /**
     * 自定义异步线程池
     * @return
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Anno-Executor");
        //定义线程池最大线程数
        executor.setMaxPoolSize(16);
        //定义核心线程数，并行线程数
        executor.setCorePoolSize(8);
        //额外线程空闲状态生存时间
        executor.setKeepAliveSeconds(5000);
        // 使用预定义的异常处理类
         executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

}
