package bll;

import be.Playlist;
import be.Song;

import java.util.List;

public interface IBLLFacade {

    List<Song> getAllSongs();

    Song createSong(String name, String text, String selectedItem, int i, String text1);

    Song updateSong(Song songToEdit, String name, String text, String selectedItem, int i, String text1);

    Song deleteSong(Song songToDelete);

    Playlist createPlaylist(String name);

    Playlist editPlaylist(Playlist playlistToEdit, String name);

    List<Playlist> getAllPlaylists();

    Playlist deletePlaylist(Playlist playlistToDelete);
}
