package com.mlmutants.status.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;

import com.mlmutants.status.model.StatusDetection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class StatusDetectorRepository {
    @Autowired
    private DynamoDBMapper mapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    /**
     * Obtiene el contador del tipoContador requerido
     * @param tipoContador indica el tipo de contador requerido: "Mutante" o "Humano"
     * @return un objeto StatusDetection con el tipoContador y el valor de count.
     */
    public StatusDetection getCounter(String tipoContador) {
        return mapper.load(StatusDetection.class, tipoContador);
    }


}
