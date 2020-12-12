package gui.model;

import be.Song;
import bll.BLLFacade;
import bll.IBLLFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class SongModel {
    private static SongModel SongsSingleteon;
    private IBLLFacade bllFacade;

    private ObservableList<Song> allSongs = FXCollections.observableArrayList();

    public SongModel() throws IOException {
            bllFacade = new BLLFacade();
    }

    public static SongModel getInstance() throws IOException {
        if (SongsSingleteon == null)
        {
            SongsSingleteon = new SongModel();
        }
        return SongsSingleteon;
    }
    public ObservableList<Song> getAllSongs() {
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll( bllFacade.getAllSongs());
        return allSongs;
    }

    public Song createSong(String name, String text, String selectedItem, int i, String text1) {
        return bllFacade.createSong(name, text, selectedItem, i, text1);
    }

    public Song updateSong(Song songToEdit, String name, String text, String selectedItem, int i, String text1) {
        return bllFacade.updateSong(songToEdit, name, text, selectedItem, i, text1);
    }

    public Song deleteSong(Song songToDelete)
    {
        return bllFacade.deleteSong(songToDelete);
    }
}
