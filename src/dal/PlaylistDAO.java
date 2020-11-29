package dal;

import be.Playlist;
import be.Song;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    SQLServerDataSource ds;

    public PlaylistDAO() throws IOException {
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
