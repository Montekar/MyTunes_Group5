package dal.DAO;
import dal.DataBaseConnectionDAO.DbConnectionProvider;

import java.io.IOException;

public class PlaylistSongDAO {
    private DbConnectionProvider connector;

    public PlaylistSongDAO() throws IOException {
        connector = new DbConnectionProvider();
    }
}
