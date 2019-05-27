/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 1:37 AM.
 * Last modified 5/27/19 1:36 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.dataextraction.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class HazelcastConfiguration {
    private static boolean cacheInitialized = false;
    private static int cacheTtl = (int) TimeUnit.MINUTES.toSeconds(5); // default 5 min. cache TTL
    private static int cacheSize = 50; // 50 MB

    private static void throwException() {
        throw new UnsupportedOperationException("You may call setCacheTtl/setCacheSize only prior to initializing Spring!");
    }

    @Bean
    public Config hazelcastConfig() {
        cacheInitialized = true;
        System.setProperty("hazelcast.phone.home.enabled", "false");
        Config config = new Config();
        config.setInstanceName("hazelcast-instance")
                .addMapConfig(new MapConfig()
                        .setName("DataExtractionResult")
                        .setMaxSizeConfig(new MaxSizeConfig(cacheSize, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setTimeToLiveSeconds(cacheTtl)
                );
        return config;
    }

    public static int getCacheTtl() {
        return cacheTtl;
    }

    public static void setCacheTtl(int cacheTtl) {
        if (cacheInitialized) {
            throwException();
        }

        HazelcastConfiguration.cacheTtl = cacheTtl;
    }

    public static int getCacheSize() {
        return cacheSize;
    }

    public static void setCacheSize(int cacheSize) {
        if (cacheInitialized) {
            throwException();
        }

        HazelcastConfiguration.cacheSize = cacheSize;
    }
}