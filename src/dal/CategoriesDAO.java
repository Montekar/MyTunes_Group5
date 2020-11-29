package dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.io.IOException;
import java.util.List;

public class CategoriesDAO {

    SQLServerDataSource ds;

    public CategoriesDAO() throws IOException {
        this.ds = new SQLServerDataSource();
        DataBaseConnectionDAO connectionInfo = new DataBaseConnectionDAO();
        List<String> infoList = connectionInfo.getDatabaseInfo();
        ds.setDatabaseName(infoList.get(0));
        ds.setUser(infoList.get(1));
        ds.setPassword(infoList.get(2));
        ds.setServerName(infoList.get(3));
        //ds.setPortNumber(Integer.parseInt(infoList.get(4)));
    }
}
