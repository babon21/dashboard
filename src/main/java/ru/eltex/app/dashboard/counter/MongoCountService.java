package ru.eltex.app.dashboard.counter;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;
import org.bson.Document;
import ru.eltex.app.dashboard.exception.UserException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

public class CountService {

    private static final Logger logger = Logger.getLogger(CountService.class);


    public static int getCount() throws UserException {
        logger.info("Получение счетчика посещений");
        try {
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
        } catch (Exception e) {
            logger.info("Ошибка получения счетчика посещений");
            throw new UserException("Ошибка получения счетчика посещений");
        }
    }
}
