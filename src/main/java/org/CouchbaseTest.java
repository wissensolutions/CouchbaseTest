package org;

import com.couchbase.client.java.*;
import com.couchbase.client.java.document.*;
import com.couchbase.client.java.document.json.*;
import com.couchbase.client.java.transcoder.*;

public class CouchbaseTest {

    public static  void  main(String[] args) throws Exception{
        String docID = args[0];
        Cluster cluster = CouchbaseCluster.create(args[1]);
        Bucket bucket = cluster.openBucket(args[2],args[3]);
        JsonTranscoder trans = new JsonTranscoder();
        JsonObject jsonObj = trans.stringToJsonObject(args[4]);

        if(args.length >= 6 && args[5].equals("CREATE")) {
            JsonDocument doc = JsonDocument.create(docID, jsonObj);
            JsonDocument response = bucket.upsert(doc);
            System.out.println("JSON doc is created. Response = "+response);
        }

        System.out.println("==================================================");
        if(args.length >= 6 && args[5].equals("DELETE")) {
            JsonDocument response = bucket.remove(docID);
            System.out.println("Deleted Response = " + response);
        }

        if(args.length >= 6 && args[5].equals("PRINT")) {
            System.out.println("==================================================");
            JsonDocument resultDoc = bucket.get(docID);
            System.out.println("DOC FOUND: " + resultDoc);
        }





    }


}
