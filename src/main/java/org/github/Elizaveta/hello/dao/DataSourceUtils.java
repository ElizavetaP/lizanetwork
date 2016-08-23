package org.github.Elizaveta.hello.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceUtils {

    public static DataSource getDataSource(){
        try {
            InitialContext initContext= new InitialContext();
            return  (DataSource) initContext.lookup("java:comp/env/jdbc_empDS");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
