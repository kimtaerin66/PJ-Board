package com.project.board.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource") //설정에 관련된것을 spring.datasource에서 찾겠다.
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
