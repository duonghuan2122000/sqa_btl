package com.sora.n4bank.di;

import com.sora.n4bank.configuration.DatabaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DIConfiguration {

    @Autowired
    private DatabaseProperties databaseProperties;

    @Bean
    public DataSource getDataSource(){
        String urlConnectionString = "jdbc:mysql://"+ databaseProperties.getHost() + ":3306/" + databaseProperties.getName();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(urlConnectionString);
        dataSource.setUsername(databaseProperties.getUser());
        dataSource.setPassword(databaseProperties.getPass());

        return dataSource;
    }
}
