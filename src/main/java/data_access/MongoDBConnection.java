package data_access;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {

    private static final String CONNECTION_STRING = "mongodb+srv://jason:krKUTXSJXxOkkoUZ@cluster0.u46mh.mongodb.net/myDatabase?retryWrites=true&w=majority";
  
    private static MongoClient mongoClient;

    // Static block to initialize mongoClient once when the class is loaded
    static {
        try {
            ServerApi serverApi = ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build();

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                    .serverApi(serverApi)
                    .build();

            mongoClient = MongoClients.create(settings);

            // Ping the database to confirm the connection
            MongoDatabase database = mongoClient.getDatabase("admin");
            database.runCommand(new Document("ping", 1));
            System.out.println("Successfully connected to MongoDB Atlas!");

        } catch (MongoException e) {
            e.printStackTrace();
            System.err.println("Could not connect to MongoDB Atlas.");
        }
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static MongoDatabase getDatabase(String dbName) {
        if (mongoClient == null) {
            throw new IllegalStateException("MongoClient has not been initialized.");
        }
        return mongoClient.getDatabase(dbName);
    }
}


//public class MongoDBConnection {
//
//    private static final String CONNECTION_STRING = "mongodb+srv://jason:krKUTXSJXxOkkoUZ@cluster0.u46mh.mongodb.net/myDatabase?retryWrites=true&w=majority";
//    private static MongoClient mongoClient;
//
//    public static void main(String[] args) {
//        try {
//            ServerApi serverApi = ServerApi.builder()
//                    .version(ServerApiVersion.V1)
//                    .build();
//
//            MongoClientSettings settings = MongoClientSettings.builder()
//                    .applyConnectionString(new ConnectionString(CONNECTION_STRING))
//                    .serverApi(serverApi)
//                    .build();
//
//            mongoClient = MongoClients.create(settings);
//
//            // Ping the database to confirm the connection
//            MongoDatabase database = mongoClient.getDatabase("admin");
//            database.runCommand(new Document("ping", 1));
//            System.out.println("Successfully connected to MongoDB Atlas!");
//
//        } catch (MongoException e) {
//            e.printStackTrace();
//            System.err.println("Could not connect to MongoDB Atlas.");
//        }
//    }
//
//    public static MongoClient getMongoClient() {
//        return mongoClient;
//    }
//
//    public static MongoDatabase getDatabase(String dbName) {
//        return mongoClient.getDatabase(dbName);
//    }
//}

