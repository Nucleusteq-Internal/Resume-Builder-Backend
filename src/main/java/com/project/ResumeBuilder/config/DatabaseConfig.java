package com.project.ResumeBuilder.config;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * DatabaseConfig configures the data source for the application.
 */
@Configuration
public class DatabaseConfig {

    /**
     * The environment for accessing application properties.
     */
    @Autowired
    private Environment environment;

    /**
     * The below method is used to create a data source for the application.
     * @return data source object.
     * @throws Exception if cannot create object of data source.
     */
    @Bean
    DataSource getDataSource() throws Exception {
        String userName = environment.getProperty("spring.datasource.username");
        String url = environment.getProperty("spring.datasource.url");
//        String password = environment.getProperty("spring.datasource.password");
        String password = getCredentials();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(userName);
        dataSourceBuilder.password(password);
        dataSourceBuilder.url(url);
        return dataSourceBuilder.build();
    }

    /**
     * This method is used to fetch password from secrets manager.
     * @return password fetched from aws secrets manager
     * @throws Exception if cannot fetch password.
     */
    public String getCredentials() throws Exception {
        String secretName = environment.getProperty("aws.secretsmanager.secretName");
        String region = environment.getProperty("aws.secretsmanager.region");

        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard().withRegion(region).build();
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        return getSecretValueResult.getSecretString();
    }
}

