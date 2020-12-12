package gui.controller;

import be.Playlist;
import gui.model.PlaylistModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupPlaylistController {

    @FXML
    private TextField playlistName;

    private PlaylistModel playlistModel;
    private boolean isEditing = false;
    private Playlist playlistToEdit;

    public PopupPlaylistController() {
        try {
            playlistModel = PlaylistModel.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInfo(Playlist selectedItem) {
        isEditing = true;
        playlistToEdit = selectedItem;
        playlistName.setText(selectedItem.getName());
    }

    public void setController(MainController mainController) {
    }

    public void closePlaylist(ActionEvent actionEvent) {
        Stage stage = (Stage) playlistName.getScene().getWindow();
        stage.close();
    }

    public void newPlaylist(ActionEvent actionEvent) {
        String name = playlistName.getText().trim(); //Eliminates all white spaces (from and back of the string)
        if (name != null && name.length() > 0 && name.length() < 50) {
            if (!isEditing) {
                playlistModel.createPlaylist(name);
            } else {
                playlistModel.editPlaylist(playlistToEdit, name);
            }
        }
        Stage stage = (Stage) playlistName.getScene().getWindow();
        stage.close();
    }

}
