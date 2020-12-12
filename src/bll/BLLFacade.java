package bll;

import be.Playlist;
import be.Song;
import dal.DALFacade;
import dal.IDALFacade;

import java.io.IOException;
import java.util.List;

public class BLLFacade implements IBLLFacade {

    private IDALFacade dalFacade;

    public BLLFacade() throws IOException {
        dalFacade = new DALFacade();
    }

    @Override
    public List<Song> getAllSongs() {
        return dalFacade.getAllSongs();
    }

    @Override
    public Song createSong(String name, String text, String selectedItem, int i, String text1) {
        return dalFacade.createSong(name, text, selectedItem, i, text1);
    }

    @Override
    public Song updateSong(Song songToEdit, String name, String text, String selectedItem, int i, String text1) {
        return dalFacade.updateSong(songToEdit, name, text, selectedItem, i, text1);
    }

    @Override
    public Song deleteSong(Song songToDelete) {

        return dalFacade.deleteSong(songToDelete);
    }

    @Override
    public Playlist createPlaylist(String name) {
        return dalFacade.createPlaylist(name);
    }

    @Override
    public Playlist editPlaylist(Playlist playlistToEdit, String name) {
        return dalFacade.editPlaylist(playlistToEdit, name);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return dalFacade.getAllPlaylist();
    }

    @Override
    public Playlist deletePlaylist(Playlist playlistToDelete) {
        return dalFacade.deletePlaylist(playlistToDelete);
    }



    @Override
    public void deleteSongFromPlaylist(Playlist selectedItem, Song selectedItem1) {
        dalFacade.deleteSongFromPlaylist(selectedItem, selectedItem1);

    }

    @Override
    public void addSongToPlaylist(Playlist selectedItem, Song selectedItem1) {
        dalFacade.addSongToPlaylist(selectedItem, selectedItem1);

    }


}
