from __future__ import print_function
import boto3
import base64
from json import loads
from decimal import Decimal

dynamodb_client = boto3.client('dynamodb')

def update_item(counterType,countResult):
    response = dynamodb_client.update_item(
        TableName='StatusDetection',
        Key={ 'tipoContador':  {'S': counterType }},
        UpdateExpression="set #ts = #ts + :val",
        ExpressionAttributeValues={
            ':val': {'N':str(countResult)}
            
        },
        ExpressionAttributeNames={
        '#ts': 'count'
            
        },
    
        ReturnValues="UPDATED_NEW"
    )
    
    return response

def lambda_handler(event, context):
    payload = event['Records']
    output = []
    success = 0
    failure = 0
    countMutant = 0
    countHuman = 0
    for record in payload:
        item = base64.b64decode(record['kinesis']['data'])
        data_item = loads(item)
        
        if data_item['recordType'] =='MUTANT':
            countMutant+=1
        else:
            countHuman+=1
   
    if (countHuman > 0):
        print(update_item('Humano',countHuman))
    
    if (countMutant > 0):
        print(update_item('Mutante',countMutant))
        
    print('Proceso finalizado con countMutantes:' + str(countMutant) + ' countHumanos:' + str(countHuman))