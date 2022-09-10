/**
 * 
 */
package br.com.edu.dynamodb.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.CreateTableResponse;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.model.ExecuteStatementRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;
import software.amazon.awssdk.services.dynamodb.model.TableDescription;

/**
 * @author eduardo
 *
 */
public class DynamoService {

    private String accessKey;

    private String secretKey;

    private String region;

    public DynamoService(String file) {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(Path.of("src/main/resources/", file).toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.accessKey = p.getProperty("AWS_ACCESS_KEY_ID");
        this.secretKey = p.getProperty("AWS_SECRET_ACCESS_KEY");
        this.region = p.getProperty("REGION");

        System.out.println(accessKey);
        System.out.println(secretKey);
        System.out.println(region);
    }

    public DynamoService(String accessKey, String secretKey) {
        super();
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public List<String> listTables() {
        final DynamoDbClient client = getClient();

        final ListTablesResponse listTables = client.listTables();

        if (listTables == null) {
            return Collections.emptyList();
        }
        return listTables.tableNames();
    }

    public TableDescription describeTable(final String tableName) {
        final DynamoDbClient client = getClient();

        final DescribeTableRequest request = DescribeTableRequest.builder().tableName(tableName).build();

        final DescribeTableResponse describeTable = client.describeTable(request);

        if (describeTable == null) {
            return null;
        }

        return describeTable.table();
    }

    public String createTable(String tableName, String key) {

        final DynamoDbClient client = getClient();

        final CreateTableRequest request = CreateTableRequest.builder()
                .attributeDefinitions(
                        AttributeDefinition.builder().attributeName(key).attributeType(ScalarAttributeType.S).build())
                .keySchema(KeySchemaElement.builder().attributeName(key).keyType(KeyType.HASH).build())
                .provisionedThroughput(
                        ProvisionedThroughput.builder().readCapacityUnits(10l).writeCapacityUnits(10l).build())
                .tableName(tableName).build();

        CreateTableResponse response = client.createTable(request);

        return response.tableDescription().tableName();
    }

    public void insert(String tableName, Map<String, AttributeValue> item) {
        final DynamoDbClient client = getClient();

        client.putItem(PutItemRequest.builder().tableName(tableName).item(item).build());
    }

    public List<Map<String, AttributeValue>> query(String tableName) {
        final DynamoDbClient client = getClient();

        var response = client
                .executeStatement(ExecuteStatementRequest.builder().statement("SELECT * FROM " + tableName).build());

        if (response.hasItems()) {
            return response.items();
        }
        return Collections.emptyList();
    }

    public void delete(String tableName) {
        final DynamoDbClient client = getClient();

        client.deleteTable(DeleteTableRequest.builder().tableName(tableName).build());
    }

    private DynamoDbClient getClient() {

        final URI uri = URI.create("http://localhost:8000");

        return DynamoDbClient.builder().credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
                .region(Region.of(region)) // region
                .endpointOverride(uri).build();
    }

}
