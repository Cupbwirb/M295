package com.ubs.projekt.m295projekt.configuration;

import com.ubs.projekt.m295projekt.dao.TierDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class Config {
    @Bean
    public Logger getLogger(){
        return LoggerFactory.getLogger(Config.class);
    }

    @Bean
    public TierDao tierDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new TierDao(namedParameterJdbcTemplate);
    }
}
