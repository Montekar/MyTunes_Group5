package bll;

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

}
