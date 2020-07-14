package com.mlmutants.mlmutants.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ConditionalOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.mlmutants.mlmutants.model.DnaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DnaResultRepository {

    @Autowired
    private DynamoDBMapper mapper;

    /**
     * Inserta un resultado del analisis de un adn en la tabla DnaResult.
     * @param dnaResult el objeto DnaResult con la informacion a insertar en la base
     */
    public Boolean insertDnaResult(DnaResult dnaResult) {
        Map expected = new HashMap<>();
        expected.put("status", new ExpectedAttributeValue().withExists(false));
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        saveExpression.setExpected(expected);
        Boolean saveNotExist = true;

        try {
            mapper.save(dnaResult, saveExpression);
        }catch (ConditionalCheckFailedException e){
            saveNotExist=false;
        }
        return saveNotExist;
    }
    /**
     * Se obtiene, si existe, el resultado de un analisis de adn almacenado en la tabla DnaResult
     * @param dnaHashCode el codigo hash buscar el adn segun su clave primaria
     * @return
     */
    public DnaResult getDnaResult(int dnaHashCode) {
        return mapper.load(DnaResult.class, dnaHashCode);
    }
}

