package com.mlmutants.mlmutants.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mlmutants.mlmutants.configuration.AmazonConfig;
import com.mlmutants.mlmutants.enums.DnaTypeEnum;
import com.mlmutants.mlmutants.payloads.ResultRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class KinesisRecordService {

    @Autowired
    AmazonKinesis kinesis;

    @Autowired
    AmazonConfig amazonConfig;

    public Boolean putRecordIntoKinesis(ResultRecord resultRecord){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String data = objectMapper.writeValueAsString(resultRecord);

            PutRecordRequest createRecordRequest = new PutRecordRequest();
            createRecordRequest.setData(ByteBuffer.wrap(data.getBytes()));

            createRecordRequest.setStreamName(amazonConfig.getAwsKinesisStreamName());
            createRecordRequest.setPartitionKey( resultRecord.getKey() );

            kinesis.putRecord(createRecordRequest);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        };
        return false;
    }


}
