package gui.model;

import be.Playlist;
import be.Song;
import bll.BLLFacade;
import bll.IBLLFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class PlaylistModel {
    private static PlaylistModel PlaylistSingleteon;

    private ObservableList<Playlist> allPlaylists = FXCollections.observableArrayList();

    private IBLLFacade bllFacade;

    public PlaylistModel() throws IOException {
        bllFacade = new BLLFacade();
    }

    public static PlaylistModel getInstance() throws IOException {
            if (PlaylistSingleteon == null)
            {
                PlaylistSingleteon = new PlaylistModel();
            }
            return PlaylistSingleteon;
    }

    public Playlist createPlaylist(String name) {
        return bllFacade.createPlaylist(name);
    }

    public Playlist editPlaylist(Playlist playlistToEdit, String name) {
        return bllFacade.editPlaylist(playlistToEdit, name);
    }

    public ObservableList<Playlist> getAllPlaylists() {
        allPlaylists = FXCollections.observableArrayList();
        allPlaylists.addAll( bllFacade.getAllPlaylists());
        return allPlaylists;
    }

    public Playlist deletePlaylist(Playlist playlistToDelete)
    {
        return bllFacade.deletePlaylist(playlistToDelete);
    }



    public void deleteSongFromPlaylist(Playlist selectedItem, Song selectedItem1) {
        bllFacade.deleteSongFromPlaylist(selectedItem, selectedItem1);
    }

    public void addSongToPlaylist(Playlist selectedItem, Song selectedItem1) {
        bllFacade.addSongToPlaylist(selectedItem, selectedItem1);
    }
}
