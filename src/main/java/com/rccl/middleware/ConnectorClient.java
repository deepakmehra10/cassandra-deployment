package com.rccl.middleware;

import com.datastax.driver.core.Session;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ConnectorClient {
    
    private static final String HOSTNAME = "127.0.0.1";
    
    private static final int PORT = 9042;
    
    private static final String ENVIRONMENT = "cloud-dev";
    
    private static final String USERNAME = "jenkins";
    
    private static final String PASSWORD = "jenkins";
    
    private static final String KEYSPACE = "port";
 
    
    public static void main(String[] args) {
        
       // String query = null;
        try {
           // System.out.println("query" + query);
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("filename"));
            
            String s = IOUtils.toString(fileInputStream,
                    StandardCharsets.UTF_8);
            System.out.println(s);
        } catch (IOException ex) {
            System.out.println(ex.getCause() + "dil");
            System.out.println("Inside catch method" + "\n\n\n\n\n");
            System.out.println(ex.getMessage());
        }
        CassandraConnector cassandraConnector = new CassandraConnector();
        cassandraConnector.connect(HOSTNAME, PORT);
        Session session = cassandraConnector.getSession();
        
        //session.execute(query);
        session.close();
    }
}


