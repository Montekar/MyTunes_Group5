package gui.model;

import be.Song;
import bll.BLLFacade;
import bll.IBLLFacade;

import java.util.List;

public class SongModel {
    private static SongModel SongsSingleteon;
    private IBLLFacade bllFacade;

    public SongModel() {
            bllFacade = new BLLFacade();
    }

    public static SongModel getInstance(){
        if (SongsSingleteon == null)
        {
            SongsSingleteon = new SongModel();
        }
        return SongsSingleteon;
    }

    public List<Song> getAllSongs() {
        return bllFacade.getAllSongs();
    }
}
