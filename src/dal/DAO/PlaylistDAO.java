package dal.DAO;

import be.Playlist;
import dal.DataBaseConnectionDAO.DbConnectionProvider;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlaylistDAO {

    private DbConnectionProvider connector;

    public PlaylistDAO() throws IOException { connector = new DbConnectionProvider(); }

    public List<Playlist> getAllPlaylists()
    {
        List<Playlist> allPlaylists = new ArrayList<>();
        try (Connection con = connector.getConnection())
        {
            String sqlStatement = "SELECT * FROM Playlists";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) {
                int ID = rs.getInt("PlaylistID");
                String Name = rs.getString("Name");
                Playlist playlist = new Playlist(ID, Name);
                allPlaylists.add(playlist);
            }
            //System.out.println(allPlaylists);
            return allPlaylists; //Returns the full list
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public Playlist createPlaylist(String name) {
        try(Connection con = connector.getConnection())
        {
            String sqlStatement = "INSERT INTO Playlists (Name) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sqlStatement);
            ps.setString(1, name);
            ps.addBatch();
            ps.executeBatch();
            Playlist newPlaylist = new Playlist(-1, name); //String title, String artist, String category, int playtime, String link

            return newPlaylist;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public Playlist editPlaylist(Playlist playlistToEdit, String name) {
        try(Connection con = connector.getConnection())
        {
            String sqlStatement = "UPDATE Playlists set Name = ? WHERE PlaylistID = ?";
            PreparedStatement ps = con.prepareStatement(sqlStatement);
            ps.setString(1, name);
            ps.setInt(2, playlistToEdit.getID());
            ps.executeUpdate();
            Playlist editPlaylist = new Playlist(playlistToEdit.getID(), name);

            return editPlaylist;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public Playlist deletePlaylist(Playlist playlistToDelete)     {
        try(Connection con = connector.getConnection())
        {
            String sqlStatement = "DELETE FROM Playlists WHERE PlaylistID = ?";
            PreparedStatement ps = con.prepareStatement(sqlStatement);
            ps.setInt(1, playlistToDelete.getID());
            ps.execute();
            return playlistToDelete;
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
    }


/*
    public void createPlaylist() throws SQLServerException {
        try(Connection con = connector.getConnection())
        {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
*/


}
