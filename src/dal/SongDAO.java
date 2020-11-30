package dal;

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

public class SongDAO {

    private SQLServerDataSource ds;

    public SongDAO() throws IOException {
        this.ds = new SQLServerDataSource();
        DataBaseConnectionDAO connectionInfo = new DataBaseConnectionDAO();
        List<String> infoList = connectionInfo.getDatabaseInfo();
        ds.setDatabaseName(infoList.get(0));
        ds.setUser(infoList.get(1));
        ds.setPassword(infoList.get(2));
        ds.setServerName(infoList.get(3));
        ds.setPortNumber(1433);
    }

    public List<Song> getAllSongs() {
        List<Song> allSongs = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM Song";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) { // Creates and adds song objects into an array list
                Song son = new Song(rs.getString("title"), rs.getString("artist"), rs.getString("category"), rs.getInt("time"));
                allSongs.add(son);
            }
            return allSongs; //Returns the full list
        } catch (SQLServerException ex) {
            System.out.println(ex);
            return null;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
