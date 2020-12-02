package gui.controller;

import gui.model.SongModel;

public class MainController {

    private final SongModel songModel;

    public MainController() {
        System.out.println("Aktyvus");
        songModel = SongModel.getInstance(); //singleton
        System.out.println(songModel.getAllSongs());
    }


}
