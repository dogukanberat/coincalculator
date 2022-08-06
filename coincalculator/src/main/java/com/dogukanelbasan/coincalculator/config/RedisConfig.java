package com.dogukanelbasan.coincalculator.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RedisConfig {

	@Value("${redis.url}")
	private String REDIS_URL;
	
	@Value("${redis.password}")
	private String REDIS_PASS;
    
    @Bean(destroyMethod="shutdown")
    RedissonClient redisson() {
        Config config = new Config();
        if(REDIS_PASS.isEmpty()){
            config.useSingleServer().setConnectionMinimumIdleSize(10).setConnectTimeout(50000).setAddress(REDIS_URL);
        }else{
            config.useSingleServer().setConnectionMinimumIdleSize(10).setConnectTimeout(50000).setAddress(REDIS_URL).setPassword(REDIS_PASS);
        }
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
    
}