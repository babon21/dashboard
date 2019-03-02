package ru.mail.dmitrii.service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

public class CountService {


    public static int getCount() {
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("dash-count");
        MongoCollection<Document> collection = database.getCollection("doc");

        int cnt = 0;

        Document document = collection.find(eq("id", 0)).first();
        if (document != null) {
            cnt = document.getInteger("count");
            collection.updateOne(eq("id", 0), inc("count", 1));
        } else {
            document = new Document();
            document.put("id", 0);
            document.put("count", 1);
            collection.insertOne(document);
        }

        return ++cnt;
    }
}
