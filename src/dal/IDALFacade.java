package dal;

import be.Playlist;
import be.Song;

import java.util.List;

public interface IDALFacade {

    List<Song> getAllSongs();

    Song updateSong(Song songToEdit, String name, String text, String selectedItem, int i, String text1);

    Song createSong(String name, String text, String selectedItem, int i, String text1);

    Song deleteSong(Song songToDelete);

    Playlist createPlaylist(String name);

    Playlist editPlaylist(Playlist playlistToEdit, String name);

    List<Playlist> getAllPlaylist();

    Playlist deletePlaylist(Playlist playlistToDelete);

    void deleteSongFromPlaylist(Playlist selectedItem, Song selectedItem1);

    void addSongToPlaylist(Playlist selectedItem, Song selectedItem1);
}
