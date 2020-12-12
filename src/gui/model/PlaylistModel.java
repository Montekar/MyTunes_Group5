package gui.model;

import be.Playlist;
import bll.IBLLFacade;

public class PlaylistModel {
    private IBLLFacade bllFacade;

    public Playlist createPlaylist(String name) {
        return bllFacade.createPlaylist(name);
    }

    public Playlist editPlaylist(Playlist playlistToEdit, String name) {
        return bllFacade.editPlaylist(playlistToEdit, name);
    }
}
