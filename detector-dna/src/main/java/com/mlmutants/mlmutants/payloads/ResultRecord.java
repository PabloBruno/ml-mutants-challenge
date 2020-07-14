package com.mlmutants.mlmutants.payloads;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mlmutants.mlmutants.enums.DnaTypeEnum;
import sun.java2d.pipe.AAShapePipe;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultRecord {


    private String key;
    private DnaTypeEnum recordType;

    public ResultRecord(String key,DnaTypeEnum recordType) {
        this.recordType = recordType;
        this.key=key;
    }

    public DnaTypeEnum getRecordType() {
        return recordType;
    }

    public void setRecordType(DnaTypeEnum recordType) {
        this.recordType = recordType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
