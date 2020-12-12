package dal.DAO;

import be.Song;
import dal.DataBaseConnectionDAO.DbConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SongDAO {

    private DbConnectionProvider connector;

    public SongDAO(){
        connector = new DbConnectionProvider();
    }

    public List<Song> getAllSongs()
    {
        List<Song> allSongs = new ArrayList<>();
        try(Connection con = connector.getConnection())
        {
            String sqlStatement = "SELECT * FROM Songs";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) { // Creates and adds song objects into an array list
                int ID = rs.getInt("SongID");
                String Title = rs.getString("Title");
                String Artist = rs.getString("Artist");
                String Category = rs.getString("Category");
                int Time = rs.getInt("Time");
                String Link = rs.getString("Link");
                Song son = new Song(ID, Title, Artist, Category, Time, Link);
                allSongs.add(son);
            }
            return allSongs; //Returns the full list
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public Song updateSong(Song songToEdit, String name, String text, String selectedItem, int i, String text1) {
        try(Connection con = connector.getConnection())
        {
            String sqlStatement = "UPDATE Songs set Title = ?, Artist = ?, Category = ?, Time =?, Link = ? WHERE SongID = ?";
            PreparedStatement ps = con.prepareStatement(sqlStatement);
            ps.setString(1, name);
            ps.setString(2, text);
            ps.setString(3, selectedItem);
            ps.setInt(4, i);
            ps.setString(5, text1);
            ps.setInt(6, songToEdit.getID());
            ps.executeUpdate();
            Song updateSong = new Song(songToEdit.getID(), name, text, selectedItem, i, text1); //String title, String artist, String category, int playtime, String link

            return updateSong;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public Song createSong(String name, String text, String selectedItem, int i, String text1) {
            try(Connection con = connector.getConnection())
            {
                String sqlStatement = "INSERT INTO Songs (Title, Artist, Category, Time, Link) VALUES (?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sqlStatement);
                ps.setString(1, name);
                ps.setString(2, text);
                ps.setString(3, selectedItem);
                ps.setInt(4, i);
                ps.setString(5, text1);
                ps.addBatch();
                ps.executeBatch();
                Song newSong = new Song(-1, name, text, selectedItem, i, text1); //String title, String artist, String category, int playtime, String link

                return newSong;
            } catch (SQLException ex) {
                System.out.println(ex);
                return null;
            }
    }

    public Song deleteSong(Song songToDelete)
    {
        try(Connection con = connector.getConnection())
        {
            String sqlStatement = "DELETE FROM Songs WHERE SongID = ?";
            PreparedStatement ps = con.prepareStatement(sqlStatement);
            ps.setInt(1, songToDelete.getID());
            ps.execute();
            return songToDelete;
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }
}
