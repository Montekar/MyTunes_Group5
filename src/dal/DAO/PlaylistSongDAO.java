/*
    This is the Data access object that's being used to manage the data of the songs on the playlist.
    it's use is to process the core functions of the data that used to:
        store, edit and add new songs to the playlist as an entity
 */

package dal.DAO;

import be.Playlist;
import be.Song;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.DataBaseConnectionDAO.DbConnectionProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistSongDAO {
    private DbConnectionProvider connector;

    public PlaylistSongDAO() throws IOException {
        connector = new DbConnectionProvider();
    }

    public List<Song> getPlaylistSongs(int id) {
        List<Song> newSongList = new ArrayList();
        try (Connection con = connector.getConnection())
        {
            String query = "SELECT * FROM SongsOnPlaylist" +
                    " INNER JOIN Songs ON SongsOnPlaylist.SongID = Songs.SongID" +
                    " WHERE SongsOnPlaylist.PlaylistID = ? "; // Gets all songs from a coresponding playlist.
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("SongID");
                String Title = rs.getString("Title");
                String Artist = rs.getString("Artist");
                String Category = rs.getString("Category");
                int Time = rs.getInt("Time");
                String Link = rs.getString("Link");
                Song son = new Song(ID, Title, Artist, Category, Time, Link);

                newSongList.add(son); //adds song to a song array
            }
            return newSongList;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            return null;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    private int getNewestSongInPlaylist(int id) {
        int newestID = -1;
        try (Connection con = connector.getConnection())
        {
            String query = "SELECT TOP(1) * FROM PlaylistSong WHERE PlaylistID = ? ORDER by locationInListID desc";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                newestID = rs.getInt("locationInListID");
            }
            System.out.println(newestID);
            return newestID;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            return newestID;
        } catch (SQLException ex) {
            System.out.println(ex);
            return newestID;
        }
    }

    public void deleteSongFromPlaylist(Playlist selectedItem, Song selectedItem1) {
        try (Connection con = connector.getConnection())
        {
            String query = "DELETE from SongsOnPlaylist WHERE PlaylistID = ? AND SongID = ? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, selectedItem.getID());
            preparedStmt.setInt(2, selectedItem1.getID());
            preparedStmt.execute();
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void addSongToPlaylist(Playlist selectedItem, Song selectedItem1) {
        String sql = "INSERT INTO SongsOnPlaylist(PlaylistID,SongID) VALUES (?,?)";
        try (Connection con = connector.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, selectedItem.getID());
            ps.setInt(2, selectedItem1.getID());
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLServerException ex) {
            System.out.println(ex);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
