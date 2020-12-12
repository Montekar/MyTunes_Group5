package dal.DAO;

import be.Playlist;
import dal.DataBaseConnectionDAO.DbConnectionProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PlaylistDAO {

    private DbConnectionProvider connector;


    public PlaylistDAO() throws IOException {
        connector = new DbConnectionProvider();
    }

    public Playlist createPlaylist(String name) {
        try(Connection con = connector.getConnection())
        {
            String sqlStatement = "INSERT INTO Playlist (Name) VALUES (?)";
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
        return null;
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
