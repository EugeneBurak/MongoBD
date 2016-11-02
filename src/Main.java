import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.*;

public class Main {
    public static void main(String[] args) {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException | MongoException e) {
            e.printStackTrace();
        }

        DB db = mongo.getDB("EugeneBURAK");         //если нет, то создаст поэтому вне блока try/catch
        System.out.println("Имя базы данных: "+db.getName()+"\n");

        DBCollection collClients = db.getCollection("clients");
        DBCollection collCheckpoints = db.getCollection("checkpoints");

        collClients.drop();
        collCheckpoints.drop();

        collClients.insert(makeClient("1","Bob","bea@sgsoft.com.ua"));
        collClients.insert(makeClient("2","Alex","bea@sgsoft.com.ua"));
        collClients.insert(makeClient("3","Tom","bea@sgsoft.com.ua"));
        collClients.insert(makeClient("4","Jon","bea@sgsoft.com.ua"));
        collClients.insert(makeClient("5","Bender","bea@sgsoft.com.ua"));
        collClients.insert(makeClient("6","Fry","bea@sgsoft.com.ua"));
        collClients.insert(makeClient("7","Max","bea@sgsoft.com.ua"));
        collClients.insert(makeClient("8","Dilan","bea@sgsoft.com.ua"));
        collClients.insert(makeClient("9","Sherlock","bea@sgsoft.com.ua"));
        collClients.insert(makeClient("10","Pedro","bea@sgsoft.com.ua"));

        collCheckpoints.insert(makeCheckpoint("1","10","11","12","13","14","15","16","17","18","19"));
        collCheckpoints.insert(makeCheckpoint("2","11","10","11","12","13","14","15","16","17","18"));
        collCheckpoints.insert(makeCheckpoint("3","12","11","10","11","12","13","14","15","16","17"));
        collCheckpoints.insert(makeCheckpoint("4","13","12","11","10","11","12","13","14","15","16"));
        collCheckpoints.insert(makeCheckpoint("5","14","13","12","11","10","11","12","13","14","15"));
        collCheckpoints.insert(makeCheckpoint("6","15","14","13","12","11","10","11","12","13","14"));
        collCheckpoints.insert(makeCheckpoint("7","16","15","14","13","12","11","10","11","12","13"));
        collCheckpoints.insert(makeCheckpoint("8","17","16","15","14","13","12","11","10","11","12"));
        collCheckpoints.insert(makeCheckpoint("9","18","17","16","15","14","13","12","11","10","11"));
        collCheckpoints.insert(makeCheckpoint("10","19","18","17","16","15","14","13","12","11","10"));

        Set<String> collNames = db.getCollectionNames();
        for (String name : collNames) {
            System.out.println(name);
        }

        DBCursor curClients = collClients.find();
        while(curClients.hasNext()) {
            System.out.println(curClients.next());
        }

        System.out.println();

        DBCursor curCheckpoints = collCheckpoints.find();
        while(curCheckpoints.hasNext()) {
            System.out.println(curCheckpoints.next());
        }

        System.out.println("Done");
    }

    private static BasicDBObject makeClient(String clientNumber, String clientName, String clientMail) {
        BasicDBObject client = new BasicDBObject();
        client.put("clientNumber", clientNumber);
        client.put("clientName", clientName);
        client.put("clientMail", clientMail);
        return client;
    }
    private static BasicDBObject makeCheckpoint(String checkIn, String checkOut1, String checkOut2, String checkOut3, String checkOut4, String checkOut5,
                                                String checkOut6, String checkOut7, String checkOut8, String checkOut9, String checkOut10) {
        BasicDBObject checkpoint = new BasicDBObject();
        checkpoint.put("checkIn", checkIn);
        checkpoint.put("checkOut1", checkOut1);
        checkpoint.put("checkOut2", checkOut2);
        checkpoint.put("checkOut3", checkOut3);
        checkpoint.put("checkOut4", checkOut4);
        checkpoint.put("checkOut5", checkOut5);
        checkpoint.put("checkOut6", checkOut6);
        checkpoint.put("checkOut7", checkOut7);
        checkpoint.put("checkOut8", checkOut8);
        checkpoint.put("checkOut9", checkOut9);
        checkpoint.put("checkOut10", checkOut10);
        return checkpoint;
    }
}
