/*
    This is the Data access object that's being used to manage the data of the database connection.
    it's use is to process the core functions of connections that's needed for the program.
 */

package dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DataBaseConnectionDAO {

    /*
    Gets database information from file. Adds it to an array to be sent to all DAO classes
    so database connection could be easily possible
    */

    public static class DbConnectionProvider {

        private static final String PROP_FILE = "data/connectionInfo.settings";
        private SQLServerDataSource ds;

        public DbConnectionProvider()
        {
            try
            {
                Properties databaseProperties = new Properties();
                databaseProperties.load(new FileInputStream(PROP_FILE));
                ds = new SQLServerDataSource();
                ds.setServerName(databaseProperties.getProperty("Server"));
                ds.setDatabaseName(databaseProperties.getProperty("Database"));
                ds.setUser(databaseProperties.getProperty("User"));
                ds.setPassword(databaseProperties.getProperty("Password"));
            }
            catch(IOException e)
            {
                //To DO
            }
        }

        /**
         * Returns Connection object which is able to
         * provide informations about database.
         *
         * @return The connection with database.
         * @throws SQLServerException if connection with database cannot be established.
         */
        public Connection getConnection() throws SQLServerException
        {
            return ds.getConnection();
        }
    }
}
