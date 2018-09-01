package com.rccl.middleware;

import com.datastax.driver.core.Session;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ConnectorClient {
    
    private static final String HOSTNAME = "192.168.0.6";
    
    private static final int PORT = 9042;
    
    private static final String ENVIRONMENT = "cloud-dev";
    
    private static final String USERNAME = "jenkins";
    
    private static final String PASSWORD = "jenkins";
    
    private static final String KEYSPACE = "port";
    
    
    public static void main(String[] args) {
        
        CassandraConnector cassandraConnector = new CassandraConnector();
        
        String query = null;
        
        try {
            
            FileInputStream fileInputStream = new FileInputStream("db/1.cql");
            
            //        FileInputStream fileInputStream = new FileInputStream(System.getProperty("filename"));
            
            query = IOUtils.toString(fileInputStream,
                    StandardCharsets.UTF_8);
            cassandraConnector.connect(HOSTNAME, PORT);
            Session session = cassandraConnector.getSession();
            session.execute(query);
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            cassandraConnector.close();
        }
    }
}


