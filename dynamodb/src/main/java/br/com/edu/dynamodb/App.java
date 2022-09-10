package br.com.edu.dynamodb;

import java.util.HashMap;

import br.com.edu.dynamodb.service.DynamoService;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

public class App {

    public static void main(String[] args) {
        DynamoService service = new DynamoService("credentials.properties");

        // delete all tables
        service.listTables().forEach(service::delete);

        final String tableName = "users";

        createTableAndInsertItems(service, tableName);

        service.query(tableName).forEach(r -> {
            r.forEach((k, v) -> {
                System.out.println(k + ": " + v.s());
            });
            System.out.println("****************");
        });

        service.listTables().forEach(t -> System.out.println(t));

        System.out.println(service.describeTable(tableName));
    }

    private static void createTableAndInsertItems(DynamoService service, final String tableName) {
        service.createTable(tableName, "id");

        for (int i = 0; i < 20; i++) {
            var item = new HashMap<String, AttributeValue>();
            item.put("user_name", AttributeValue.fromS("User" + i));
            item.put("id", AttributeValue.fromS(String.valueOf(i)));
            service.insert(tableName, item);
        }
    }
}
