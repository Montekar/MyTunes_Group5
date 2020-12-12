package be;

public class Playlist {

    private String name; // Title of song
    private final int ID; // Unique song ID in the database

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
