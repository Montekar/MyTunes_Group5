package dal;

import be.Playlist;
import be.Song;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

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

    public void createPlaylist() throws SQLServerException {
        try(Connection con = connector.getConnection())
        {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
