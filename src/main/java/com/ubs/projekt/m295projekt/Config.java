package com.ubs.projekt.m295projekt;

import com.ubs.projekt.m295projekt.dao.TierDao;
import com.ubs.projekt.m295projekt.dao.TierheimDB;
//import com.ubs.projekt.m295projekt.optional.TierOptional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Bean
    public TierDao tierDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new TierDao(namedParameterJdbcTemplate);
    }



    /*@Bean
    public TierOptional userSetExtractor(){
        return new TierOptional();
    }*/

    @Bean
    public TierheimDB tierheimDB(TierDao tierDao){
        return new TierheimDB(tierDao);
    }
}
