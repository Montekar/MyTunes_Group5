package dal;

import be.Song;
import dal.DAO.SongDAO;

import java.util.List;


public class DALFacade implements IDALFacade{

    private SongDAO songDAO;

    public DALFacade() {
        songDAO = new SongDAO();
    }

    @Override
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }
}
