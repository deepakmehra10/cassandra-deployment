package com.rccl.middleware;

import com.datastax.driver.core.Session;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ConnectorClient {
    
    private static final Config CONFIG = ConfigFactory.load();
    
    private static final String HOSTNAME = CONFIG.getString("cas_contact_point_one");
    
    private static final int PORT = CONFIG.getInt("port");
    
    private static final String ENVIRONMENT = CONFIG.getString("environment");
    
    private static final String USERNAME = CONFIG.getString("authentication.username");
    
    private static final String PASSWORD = CONFIG.getString("authentication.password");
    
    private static final String KEYSPACE = CONFIG.getString("keyspace");
    
    
    public static void main(String[] args) {
        
        CassandraConnector cassandraConnector = new CassandraConnector();
        
        
        String query = null;
        
        try {
            
            //FileInputStream fileInputStream = new FileInputStream("db/1.cql");
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("filename"));
            
            query = IOUtils.toString(fileInputStream,
                    StandardCharsets.UTF_8);
            cassandraConnector.connect(HOSTNAME, PORT, USERNAME, PASSWORD);
            Session session = cassandraConnector.getSession();
            session.execute(query);
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            cassandraConnector.close();
        }
    }
}


