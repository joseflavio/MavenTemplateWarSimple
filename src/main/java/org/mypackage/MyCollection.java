package org.mypackage;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.IndexOptions;

public class MyCollection {

    private static final String DATABASE_NAME = "testdb";

    private static final String COLLECTION_NAME = "mycollection";

    private final MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");

    public void insert(final Document d) {
        try (final MongoClient mongoClient = new MongoClient(connectionString)) {
            MongoDatabase db = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> myCollection = db.getCollection(COLLECTION_NAME);
            myCollection.insertOne(d);
        }
    }

    public void dropBeCareful() {
        try (final MongoClient mongoClient = new MongoClient(connectionString)) {
            MongoDatabase db = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> myCollection = db.getCollection(COLLECTION_NAME);
            myCollection.drop();
        }
    }

    public void createCollection() {
        try (final MongoClient mongoClient = new MongoClient(connectionString)) {
            MongoDatabase db = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> myCollection = db.getCollection(COLLECTION_NAME);
            IndexOptions indexOptions = new IndexOptions();
            indexOptions.sparse(true);
            indexOptions.unique(true);
            myCollection.createIndex(new Document("hostname", 1), indexOptions);
        }
    }

    public void applyAll(final SimpleFunction<Document> f) {
        try (final MongoClient mongoClient = new MongoClient(connectionString)) {
            MongoCollection<Document> myCollection = getCollectionIfExists(mongoClient, DATABASE_NAME, COLLECTION_NAME);
            try (final MongoCursor<Document> cursor = myCollection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    f.apply(doc);
                }
            }
        }

    }

    private static MongoCollection<Document> getCollectionIfExists(final MongoClient client, final String dbName,
        final String collectionName) {
        MongoDatabase db = client.getDatabase(dbName);
        MongoIterable<String> collectionNames = db.listCollectionNames();
        for (final String name : collectionNames) {
            System.out.println(name);
            if (name.equals(collectionName)) {
                return db.getCollection(collectionName);
            }
        }
        throw new RuntimeException("Collection '" + collectionName + "' not found in '" + dbName + "'.");
    }

    public interface SimpleFunction<T> {
        void apply(T t);
    }

}
