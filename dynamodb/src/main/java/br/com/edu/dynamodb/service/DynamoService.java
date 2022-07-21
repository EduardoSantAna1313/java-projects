/**
 * 
 */
package br.com.edu.dynamodb.service;

import java.util.Collections;
import java.util.List;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;
import software.amazon.awssdk.services.dynamodb.model.TableDescription;

/**
 * @author eduardo
 *
 */
public class DynamoService {

    private String accessKey;

    private String secretKey;

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

    private DynamoDbClient getClient() {
        return DynamoDbClient.builder().credentialsProvider(() -> new AwsCredentials() {

            @Override
            public String secretAccessKey() {
                return secretKey;
            }

            @Override
            public String accessKeyId() {
                return accessKey;
            }
        })
//				.httpClient(new SdkHttpClient.Builder<SdkHttpClient>.Builder().build())

                .build();
    }

}
