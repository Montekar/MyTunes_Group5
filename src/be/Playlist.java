package be;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String name; // Title of song
    private final int ID; // Unique song ID in the database
    private List<Song> songsInPlaylist = new ArrayList<Song>();

    public void addToPlaylist(Song newSong) {
        songsInPlaylist.add(newSong);
    }

    public void setList(List<Song> songsInPlaylist) {
        this.songsInPlaylist = songsInPlaylist;
    }

    public List<Song> getSongsInPlaylist() {
        return songsInPlaylist;
    }

    public void removeFromPlaylist(Song newSong) {
        songsInPlaylist.remove(newSong);
    }

    public Playlist(int ID, String name) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }
}
