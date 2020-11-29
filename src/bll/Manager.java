package bll;

import be.Song;
import bll.util.SongFilter;
import dal.CategoriesDAO;
import dal.PlaylistDAO;
import dal.PlaylistSongDAO;
import dal.SongDAO;

import java.io.IOException;
import java.util.List;

public class Manager {
    private final SongDAO songDAO;
    private final SongFilter songSearcher;
    private final PlaylistSongDAO PlaylistSongInfo;
    private final CategoriesDAO categoriesDAO;
    private final PlaylistDAO playListDAO;

    public Manager() throws IOException {
        playListDAO = new PlaylistDAO();
        songDAO = new SongDAO();
        songSearcher = new SongFilter();
        PlaylistSongInfo = new PlaylistSongDAO();
        categoriesDAO = new CategoriesDAO();

    }

    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }
}
