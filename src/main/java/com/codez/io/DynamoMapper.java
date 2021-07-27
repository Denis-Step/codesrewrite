package com.codez.io;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoMapper {

    private AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    public DynamoMapper () {
        this.client = setUpClient();
        this.mapper = new DynamoDBMapper(client);
    }

    public DynamoDBMapper getMapper() {
        return this.mapper;
    }

    // Pull default from environment.
    private static AmazonDynamoDB setUpClient() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion("us-east-2").withCredentials(new ProfileCredentialsProvider("default"))
                .build();

       return client;
    }

}