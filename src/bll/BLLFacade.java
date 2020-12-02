package bll;

import be.Song;
import dal.DALFacade;
import dal.IDALFacade;

import java.util.List;

public class BLLFacade implements IBLLFacade {

    private IDALFacade dalFacade;

    public BLLFacade(){
        dalFacade = new DALFacade();
    }

    @Override
    public List<Song> getAllSongs() {
        return dalFacade.getAllSongs();
    }

}
