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

    private DataBaseConnectionDAO.DbConnectionProvider connector;

    public SongDAO() throws IOException {
        connector = new DataBaseConnectionDAO.DbConnectionProvider();
    }

    public void createPlaylist() throws SQLServerException {
        try(Connection con = connector.getConnection())
        {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Song> getAllSongs() {
        List<Song> allSongs = new ArrayList<>();
        try (Connection con = connector.getConnection()) {
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
