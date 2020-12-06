package dal;

import be.Song;
import dal.DAO.SongDAO;

import java.io.IOException;
import java.util.List;


public class DALFacade implements IDALFacade{

    private SongDAO songDAO;

    // This is just a comment for testing purposes

    public DALFacade() throws IOException
    {

        songDAO = new SongDAO();
    }

    @Override
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }
}
