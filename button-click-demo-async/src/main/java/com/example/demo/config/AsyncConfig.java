package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Value("${app.async.core-pool-size:2}")
    private int corePoolSize;

    @Value("${app.async.max-pool-size:8}")
    private int maxPoolSize;

    @Value("${app.async.queue-capacity:1000}")
    private int queueCapacity;

    @Value("${app.async.await-termination-seconds:10}")
    private int awaitTerminationSeconds;

    @Bean(name = "clickExecutor")
    public Executor clickExecutor() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setThreadNamePrefix("click-");
        exec.setCorePoolSize(corePoolSize);
        exec.setMaxPoolSize(maxPoolSize);
        exec.setQueueCapacity(queueCapacity);
        exec.setAwaitTerminationSeconds(awaitTerminationSeconds);
        exec.setWaitForTasksToCompleteOnShutdown(true);
        exec.initialize();
        return exec;
    }
}
