package gui.controller;

import be.Song;
import dal.DAO.SongDAO;
import gui.model.SongModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final SongModel songModel;

    private SongDAO songDAO = new SongDAO();

    private int currentSong = 0;

    private ObservableList<Song> observableListSong;
    //@FXML
    //private Label songLabel;
    @FXML
    private TableView<Song> lstSongs;
    @FXML
    private TableColumn<Song, String> Title;
    @FXML
    private TableColumn<Song, String> Artist;
    @FXML
    private TableColumn<Song, String> Category;
    @FXML
    private TableColumn<Song, Integer> Time;
    @FXML
    private Button playButton;
    @FXML
    private Label paylingSongLabel;
    @FXML
    private Slider volume;
    @FXML
    private Button exit;

    private MediaPlayer mediaPlayer;



    public MainController() throws IOException {
        System.out.println("Active");
        songModel = SongModel.getInstance(); //singleton
        System.out.println(songModel.getAllSongs());


    }

    public void clickLoad(ActionEvent actionEvent)
    {
        //List<Song> songs = songDAO.getAllSongs();
        //lstSongs.getItems().clear();
        //lstSongs.getItems().addAll(songs);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //observableListPlay = playlistModel.getPlaylists();
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Artist.setCellValueFactory(new PropertyValueFactory<>("Artist"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Time.setCellValueFactory(new PropertyValueFactory<>("playtimeString"));
        lstSongs.setItems(songModel.getAllSongs());
    }



    private void play()
    {
        mediaPlayer = new MediaPlayer(
                new Media(
                        new File(
                                lstSongs.getItems().get(currentSong).getLocation()).toURI().toString()
                )
        );
        mediaPlayer.play();
        paylingSongLabel.setText(lstSongs.getItems().get(currentSong).getTitle() + " is Playing");
        mediaPlayer.setOnEndOfMedia(() -> {
            if (lstSongs.getSelectionModel().getSelectedIndex() != -1) {
                if (lstSongs.getItems().size() == currentSong + 1) {
                    currentSong = 0;
                }
                else {
                    currentSong++;
                }
                play();
            }

        }) ;
    }


    public void playSong(javafx.event.ActionEvent actionEvent) {
        if (mediaPlayer == null) {
            currentSong = lstSongs.getSelectionModel().getSelectedIndex();
            playButton.setText("Stop");
            play();

        }
        else {
            paylingSongLabel.setText("None is Playing");
            playButton.setText("Play");
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }

    public void backButton(javafx.event.ActionEvent actionEvent) {
        if (lstSongs.getSelectionModel().getSelectedIndex() != -1) {
            mediaPlayer.stop();
            if (currentSong - 1 <= - 1) {
                currentSong = 0;
            }
            else {
                currentSong --;
            }
            play();
        }

    }

    public void forwardButton(javafx.event.ActionEvent actionEvent) {
        if (lstSongs.getSelectionModel().getSelectedIndex() != -1) {
            mediaPlayer.stop();
            if (lstSongs.getItems().size() == currentSong + 1) {
                currentSong = 0;
            }
            else {
                currentSong++;
            }
            play();
        }
    }

    public void setSound(MouseEvent mouseEvent) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume.getValue());
        }
    }

    public void newSong(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/view/popupSong.fxml"));
        root1 = (Parent) fxmlLoader.load();
        fxmlLoader.<PopupSongController>getController().setInfo();
        fxmlLoader.<PopupSongController>getController().setController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 600, 400));
        stage.centerOnScreen();
        stage.show();
    }

    public void newPlaylist(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root2;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/view/popupPlaylist.fxml"));
        root2 = (Parent) fxmlLoader.load();
        fxmlLoader.<PopupPlaylistController>getController().setInfo();
        fxmlLoader.<PopupPlaylistController>getController().setController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.centerOnScreen();
        stage.show();
    }

    public void setExit(javafx.event.ActionEvent actionEvent) throws IOException{
        System.exit(0);
    }
}
