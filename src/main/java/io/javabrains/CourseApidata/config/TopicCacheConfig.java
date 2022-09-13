package io.javabrains.CourseApidata.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
public class TopicCacheConfig {
    @Bean
    public Config cacheConfig() {
        return new Config()
                .setInstanceName("hazel-instance")
                .addMapConfig(new MapConfig()
                        .setName("Topic-cache")
                        .setTimeToLiveSeconds(3000));

    }
}
