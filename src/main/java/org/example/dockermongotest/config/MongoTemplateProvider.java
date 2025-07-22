package org.example.dockermongotest.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Utility to create MongoTemplate for SDK consumers.
 */
public class MongoTemplateProvider {


    public static MongoTemplate create(String connectionString, String database) {
        MongoClient client = MongoClients.create(connectionString);
        return new MongoTemplate(client, database);
    }
}