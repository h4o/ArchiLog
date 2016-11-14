package com.polytech.al.synchro.util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.LazyLoadingProxy;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class LazyLoadingSerializationConfig {

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper om = new ObjectMapper();
        final SimpleModule module = new SimpleModule("Zone", new Version(1, 0, 0,null));

        module.addSerializer(LazyLoadingProxy.class, new LazyLoadingSerializer());
        om.registerModule(module);

        return om;
    }

}