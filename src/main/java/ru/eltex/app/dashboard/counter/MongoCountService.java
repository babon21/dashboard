package ru.eltex.app.dashboard.counter;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;
import org.bson.Document;


import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

/**
 * Учет числа посещений с помощью MongoDB <br>
 * Порт, адрес, и БД сервера стандартные <br>
 *
 * <p>
 * Формат документа<br>
 * <code>{ id : integer, count : integer}</code>
 * </p>
 *
 * @author darhzain
 */
public class MongoCountService implements CountService {

    private static final Logger logger = Logger.getLogger(MongoCountService.class);

    /**
     * Получение значение счетчика посещений
     * @return значение счетчика
     */
    public int getCount() {
        logger.info("Получение счетчика посещений");
        int cnt = 0;

        try {
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase database = mongoClient.getDatabase("dash-count");
            MongoCollection<Document> collection = database.getCollection("doc");

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
        } catch (MongoException | IllegalArgumentException e) {
            logger.error(e.getMessage());

            return -1;
        }

        return ++cnt;
    }
}
