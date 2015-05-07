import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * Created by christine on 07.05.2015.
 */
public class DataBaseProductVersion {

    public static void main(String[] args) {

        DataBaseProductVersion dataBaseProductVersion = new DataBaseProductVersion();
        Connection connection = null;
        connection = openConnection(dataBaseProductVersion.setupBasicDataSource());

        DatabaseMetaData dbMetaData = null;
        try {
            dbMetaData = connection.getMetaData();
            System.out.println("database URL: " + dbMetaData.getURL());
            System.out.println("database username: " + dbMetaData.getUserName());
            System.out.println("database product name: " + dbMetaData.getDatabaseProductName());
            System.out.println("database product version: " + dbMetaData.getDatabaseProductVersion());
            System.out.println("database major version: " + dbMetaData.getDatabaseMajorVersion());
            System.out.println("database minor version: " + dbMetaData.getDatabaseMinorVersion());
            System.out.println("JDBC driver name: " + dbMetaData.getDriverName());
            System.out.println("JDBC driver version: " + dbMetaData.getDriverVersion());
            System.out.println("JDBC driver major version: " + new Integer(dbMetaData.getDriverMajorVersion()));
            System.out.println("JDBC driver minor version: " + new Integer(dbMetaData.getDriverMinorVersion()));

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public BasicDataSource setupBasicDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:db2://xxxxx");
        ds.setUsername("USERNAME");
        ds.setPassword("PASSWORD");
        ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        return ds;
    }

    public static Connection openConnection(DataSource dataSource) throws RuntimeException {
        try {
            Connection connection = dataSource.getConnection();
            if (connection == null) {
                throw new RuntimeException("Unable to obtain Jdbc connection from DataSource");
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to obtain Jdbc connection from DataSource", e);
        }
    }

}
