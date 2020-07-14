package com.mlmutants.status.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "StatusDetection")
public class StatusDetection {

    private String tipoContador;
    private long count;

    @DynamoDBHashKey
    public String getTipoContador() {
        return tipoContador;
    }

    public void setTipoContador(String tipoContador) {
        this.tipoContador = tipoContador;
    }
    @DynamoDBAttribute
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
