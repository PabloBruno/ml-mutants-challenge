package com.mlmutants.mlmutants.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName = "DnaResult")
public class DnaResult {
    private int dnaId;
   private String dnaArray;
   private Boolean isMutant;

    @DynamoDBHashKey
    public int getDnaId() {
        return dnaId;
    }

    public void setDnaId(int dnaId) {
        this.dnaId = dnaId;
    }

    @DynamoDBAttribute
    public Boolean getMutant() {
        return isMutant;
    }

    public void setMutant(Boolean mutant) {
        isMutant = mutant;
    }

    @DynamoDBAttribute
    public String  getDnaArray() {
        return dnaArray;
    }

    public void setDnaArray(String  dnaArray) {
        this.dnaArray = dnaArray;
    }
}
