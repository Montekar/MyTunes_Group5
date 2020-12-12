package dal;

import be.Playlist;
import be.Song;
import dal.DAO.PlaylistDAO;
import dal.DAO.PlaylistSongDAO;
import dal.DAO.SongDAO;

import java.io.IOException;
import java.util.List;


public class DALFacade implements IDALFacade{

    private SongDAO songDAO;
    private PlaylistDAO playlistDAO;
    private PlaylistSongDAO playlistSongDAO;

    // This is just a comment for testing purposes

    public DALFacade() throws IOException
    {

        songDAO = new SongDAO();
        playlistDAO = new PlaylistDAO();
        playlistSongDAO = new PlaylistSongDAO();

    }

    @Override
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }

    @Override
    public Song updateSong(Song songToEdit, String name, String text, String selectedItem, int i, String text1) {
        return songDAO.updateSong(songToEdit, name, text, selectedItem, i, text1);
    }

    @Override
    public Song createSong(String name, String text, String selectedItem, int i, String text1) {
        return songDAO.createSong(name, text, selectedItem, i, text1);
    }

    @Override
    public Song deleteSong(Song songToDelete) {

        return songDAO.deleteSong(songToDelete);
    }

    @Override
    public Playlist createPlaylist(String name) {
        return playlistDAO.createPlaylist(name);
    }

    @Override
    public Playlist editPlaylist(Playlist playlistToEdit, String name) {
        return playlistDAO.editPlaylist(playlistToEdit, name);
    }

    @Override
    public List<Playlist> getAllPlaylist() {
        return playlistDAO.getAllPlaylists();
    }

    @Override
    public Playlist deletePlaylist(Playlist playlistToDelete) {
        return playlistDAO.deletePlaylist(playlistToDelete);
    }



    @Override
    public void deleteSongFromPlaylist(Playlist selectedItem, Song selectedItem1) {
        playlistSongDAO.deleteSongFromPlaylist(selectedItem, selectedItem1);

    }

    @Override
    public void addSongToPlaylist(Playlist selectedItem, Song selectedItem1) {
        playlistSongDAO.addSongToPlaylist(selectedItem, selectedItem1);

    }

}
