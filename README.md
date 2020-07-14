# ML-Mutants

Resolución del examen de Mercado Libre.

## Arquitectura

La aplicación esta compuesta por dos microservicios y una función de python que correra en AWS Lambda.

#### Microservicios

1. detector-dna: Se dedica a detectar los adn enviados por el usuario. Luego de la detección, guarda el adn en una tabla DynamoDB y envía un registro de evento a un stream de Amazon Kinesis indicando si la detección correspondió a un HUMANO o un MUTANTE.
2. status: Se dedica a entregar al usuario el status de la detección.

#### Funcion Lambda

El codigo "contador-streams" es una función python que consume los eventos registrados en el stream de Amazon Kinesis, realiza un conteo de MUTANTES y HUMANOS y, por último, actualiza dichos resultados en una tabla de DynamoDB.7.

## Instrucciones

#### Requerimientos

1 - Maven 3.6.0
2 - Java 8
3 - AWS DynamoDB*
4 - Kinesis

---

*Si la base de datos es local seguir las siguientes instrucciones de instalacion, configuracion y puesta en marcha:
[DynamoLocal download and running](https://docs.aws.amazon.com/es_es/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)

#### Instalacion

- Clonar el repositorio

```bash
git clone https://github.com/PabloBruno/ml-mutants.git
```

- Instalar dependencias y creacion del jar:

Ejecutar para ambos microservicios.

```bash
mvn clean package

```

- Configurar puertos de los microservicios

  ````bash
  server.port=portnumber
  ````
- Configurar credenciales en el archivo aplication.properties a una instancia de DynamoDb en ambos microservicios

```bash
amazon.access.key= yourKey
amazon.access.secret-key=yourSecretKey
amazon.region=yourRegion
amazon.end-point.url=yourEndPointUrl
```

- Crear dos tablas:

```bash
Tabla: DnaResult
Primary key (HASH Numérico): dnaId
Attributes:  dnaArray, Type: SS
             isMutant, Type: BOOL
```

```bash
Tabla: StatusDetection
Primary key (HASH Cadena): tipoContador
Attributes:  count, Type: N
   
```

- Crear un Data Stream de Amazon Kinesis Data Stream

  Seguir instrucciones[ https://docs.aws.amazon.com/streams/latest/dev/tutorial-stock-data-kplkcl-create-stream.htmlhttps://docs.aws.amazon.com/streams/latest/dev/tutorial-stock-data-kplkcl-create-stream.html](https://)
- Crear una nueva función lamda para el codigo "contador-streams.py". Configurar el Source el datastream de Amazon Kinesis.

  Seguir instrucciones: [https://docs.aws.amazon.com/lambda/latest/dg/with-kinesis.htmlhttps://docs.aws.amazon.com/lambda/latest/dg/with-kinesis.html](https://)
- Configurar el nombre del data stream en aplication.properties del microservicio detector-dna.

  ````bash
  amazon.kinesis.stream-name= yourKinesisDataStreamName
  ````
- Ejecutar ambas la api

```bash
 mvn spring-boot:run
```

- Realizar peticiones a

```bash
 localhost:portnumber
```


#### Algunas consideraciones e ideas.

- A nivel arquitectónico, ambos microservicios fueron implementados usando AWS Elastic Beanstalk. Se debe activar la escalabilidad automática en los dos. Se configuró una ApiGateway para hacer peticiones a un unico endpoint. Esta, luego, reenvía las peticiones al microservicio correspondiente.
- Se utilizó DynamoDB para almacenar los adn y el resultado de los análisis y un contador de resultados para humanos y mutantes. En este caso la escalabilidad automática tambien se debe activar.
  Para el adn se hizo una implementación propia de un hash para la clave primaria. Se entiende que el hash usado puede crear colisiones a medida que crece la base de datos. Quedó pendiente en la investigación utilizar un hash SHA1 para reducir ampliamente la posibilidad de colisiones.
  Para el contador de MUTANTES y HUMANOS se cró una tabla a la cual se le hacen updates atómicos. Se entiende que esto puede traer problemas de escalabilidad a la hora de manejar muchisimas escrituras por segundo ya que se concentra todo en una tabla. Para evitar esto, se envían eventos de creación de nuevos MUTANTES o HUMANOS a un stream de Amazon Kinesis. Finalmente estos eventos son consumidos por una función de AwsLambda que cuenta los eventos y hace el update a la tabla con los contadores. Esto ayuda a reducir el impacto a la BD y contar eventos de a grupos en vez de hacer escrituras individuales (configurando la ejecución de la función lambda en sí). Ante cualquier cosa, Amazon Kinesis mantiene los eventos almacenados según se lo configure.
