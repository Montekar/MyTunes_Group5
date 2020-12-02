package dal.DAO;

import be.Song;
import dal.DataBaseConnectionDAO.DbConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class SongDAO {

    private DbConnectionProvider connector;

    public SongDAO(){
        connector = new DbConnectionProvider();
    }

    public List<Song> getAllSongs() {
        List<Song> allSongs = new ArrayList<>();
        try(Connection con = connector.getConnection())
        {
            String sqlStatement = "SELECT * FROM Songs";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) { // Creates and adds song objects into an array list
                Song son = new Song(rs.getString("Title"),
                        rs.getString("Artist"),
                        rs.getString("Category"),
                        rs.getInt("Time"),
                        rs.getString("Link"));
                allSongs.add(son);
            }
            return allSongs; //Returns the full list
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
