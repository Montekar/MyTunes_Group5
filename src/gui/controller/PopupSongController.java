/*
    This is the controller for the PopupSongController fxml window, it holds all the functional code for it's GUI layer.
 */

package gui.controller;

import be.Song;
import gui.model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static java.lang.Math.toIntExact;

public class PopupSongController {

    @FXML
    private TextField chooseTitle;
    @FXML
    private TextField chooseArtist;
    @FXML
    private Label chooseTime;
    @FXML
    private Label chooseFile;
    @FXML
    private ChoiceBox<String> chooseCategory;

    private MediaPlayer mediaPlayer;
    private boolean isEditing = false;
    private Song songToEdit;
    private SongModel songModel;

    public PopupSongController() {
        try {
            songModel = SongModel.getInstance();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void setInfo(Song selectedItem) {
        isEditing = true;
        songToEdit = selectedItem;
        chooseTitle.setText(selectedItem.getTitle());
        if (selectedItem.getArtist() != null) {
            chooseArtist.setText(selectedItem.getArtist());
        }
        if (selectedItem.getCategory() != null) {
            chooseArtist.setText(selectedItem.getCategory());
        }
        chooseFile.setText(selectedItem.getLocation());
        mediaPlayer = new MediaPlayer(new Media(new File(selectedItem.getLocation()).toURI().toString()));
        mediaPlayer.setOnReady(()->{
            String averageSeconds = String.format("%1.0f", mediaPlayer.getMedia().getDuration().toSeconds());
            int minutes = Integer.parseInt(averageSeconds) / 60;
            int seconds = Integer.parseInt(averageSeconds) % 60;
            if (10 > seconds) {
                chooseTime.setText(minutes + ":0" + seconds);
            } else {
                chooseTime.setText(minutes + ":" + seconds);
            }
        });
    }

    public void setController(MainController mainController) {
    }


    public void chooseSong(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select song");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Audio Files", "*.wav*", "*.mp3*", "*.acc*"),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null) {
            chooseFile.setText(selectedFile.getAbsolutePath());
            mediaPlayer = new MediaPlayer(new Media(new File(selectedFile.getAbsolutePath()).toURI().toString()));
            mediaPlayer.setOnReady(()->{
                String averageSeconds = String.format("%1.0f", mediaPlayer.getMedia().getDuration().toSeconds());
                int minutes = Integer.parseInt(averageSeconds) / 60;
                int seconds = Integer.parseInt(averageSeconds) % 60;
                if (10 > seconds) {
                    chooseTime.setText(minutes + ":0" + seconds);
                } else {
                    chooseTime.setText(minutes + ":" + seconds);
                }
            });
        }
    }

    public void saveNew(ActionEvent actionEvent) {
        int i = toIntExact(Math.round(mediaPlayer.getMedia().getDuration().toSeconds()));
        String name = chooseTitle.getText().trim();
        if (name != null && name.length() > 0 && name.length() < 50 && chooseFile.getText() != null && chooseFile.getText().length() != 0 && i > 0) {
            if (!isEditing) {
                songModel.createSong(name, chooseArtist.getText(), chooseCategory.getSelectionModel().getSelectedItem(), i, chooseFile.getText());
            }
            else {
                songModel.updateSong(songToEdit, name, chooseArtist.getText(), chooseCategory.getSelectionModel().getSelectedItem(), i, chooseFile.getText());
            }
        }
        Stage stage = (Stage) chooseTitle.getScene().getWindow();
        stage.close();
    }

    public void closeNew(ActionEvent actionEvent) {
        Stage stage = (Stage) chooseTitle.getScene().getWindow();
        stage.close();
    }


}
