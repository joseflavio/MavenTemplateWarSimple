package org.mypackage;

import static java.util.Arrays.asList;

import java.text.ParseException;
import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class RegisterHostUsecase {

    public static void main(String[] args) throws ParseException {

        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        try (final MongoClient mongoClient = new MongoClient(connectionString)) {

            MongoDatabase database = mongoClient.getDatabase("testdb");

            Document d = new Document();
            d.append("username", "joseflavio");
            d.append("insertTime", new Date());
            d.append("hostname", "mypc001");
            d.append("hostcode", "ABCDEFABCDEF");
            d.append("properties", asList(new Document("model", "XYZ"), new Document("serial", "123456")));

            MongoCollection<Document> myCollection = database.getCollection("mycollection");
            myCollection.insertOne(d);

            try (final MongoCursor<Document> cursor = myCollection.find().iterator()) {
                while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
                }
            }
        }

    }

}
