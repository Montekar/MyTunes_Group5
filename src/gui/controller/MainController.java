/*
    This is the main controller for the main fxml window, it holds all the functional code for it's GUI layer.
 */

package gui.controller;

import be.Playlist;
import be.Song;
import dal.DAO.SongDAO;
import gui.model.PlaylistModel;
import gui.model.SongModel;
import javafx.collections.FXCollections;
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
    private final PlaylistModel playlisModel;
    @FXML
    public TableView<Song> songsInPlaylist;
    @FXML
    public TableColumn<Song, String> songName;

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
    private TableView<Playlist> playlistView;
    @FXML
    private TableColumn<Playlist, String> playlistName;
    @FXML
    private TableColumn<Playlist, String>  playlistNumber;
    @FXML
    private TableColumn<Playlist, Integer>  playlistTime;


    private MediaPlayer mediaPlayer;



    public MainController() throws IOException {
        System.out.println("Active");
        songModel = SongModel.getInstance(); //singleton
        System.out.println(songModel.getAllSongs());

        playlisModel = PlaylistModel.getInstance();
    }

    public void clickLoad(ActionEvent actionEvent)
    {
        //List<Song> songs = songDAO.getAllSongs();
        //lstSongs.getItems().clear();
        //lstSongs.getItems().addAll(songs);
    }

    public void refreshPlaylist(javafx.event.ActionEvent actionEvent) throws IOException {
        UpdataPlaylist();
    }

    public void refreshSong(javafx.event.ActionEvent actionEvent) throws IOException {
        UpdataSong();
    }

    public void UpdataPlaylist(){
        playlistView.setItems(playlisModel.getAllPlaylists());
    }

    public void UpdataSong(){
        lstSongs.setItems(songModel.getAllSongs());
    }

    public void UpdataTable(){
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Artist.setCellValueFactory(new PropertyValueFactory<>("Artist"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Time.setCellValueFactory(new PropertyValueFactory<>("playtimeString"));
        lstSongs.setItems(songModel.getAllSongs());

        playlistName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        playlistView.setItems(playlisModel.getAllPlaylists());

        songName.setCellValueFactory(new PropertyValueFactory<>("Title"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //observableListPlay = playlistModel.getPlaylists();
        UpdataTable();
    }



    private void play()
    {
        mediaPlayer = new MediaPlayer(
                new Media(
                        new File(
                                songsInPlaylist.getItems().get(currentSong).getLocation()).toURI().toString()
                )
        );
        mediaPlayer.play();
        paylingSongLabel.setText(songsInPlaylist.getItems().get(currentSong).getTitle() + " is Playing");
        mediaPlayer.setOnEndOfMedia(() -> {
            if (songsInPlaylist.getSelectionModel().getSelectedIndex() != -1) {
                if (songsInPlaylist.getItems().size() == currentSong + 1) {
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
            currentSong = songsInPlaylist.getSelectionModel().getSelectedIndex();
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
        setUpScenes(false);
    }

    public void newPlaylist(javafx.event.ActionEvent actionEvent) throws IOException {
        setUpPlaylists(false);
    }

    public void setExit(javafx.event.ActionEvent actionEvent) throws IOException{
        System.exit(0);
    }

    public void deleteSong(javafx.event.ActionEvent actionEvent) {
        if (lstSongs.getSelectionModel().getSelectedIndex() != -1) {
            songModel.deleteSong(lstSongs.getSelectionModel().getSelectedItem());
        }
        UpdataSong();
    }


    public void updateSong(javafx.event.ActionEvent actionEvent) {
        try {
            setUpScenes(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpScenes(boolean isEditing) throws IOException { // New/Edit song scene
        Parent root2;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/view/popupSong.fxml"));
        root2 = (Parent) fxmlLoader.load();
        if (isEditing) {
            fxmlLoader.<PopupSongController>getController().setInfo(lstSongs.getSelectionModel().getSelectedItem());
        }
        fxmlLoader.<PopupSongController>getController().setController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.centerOnScreen();
        stage.show();
    }
    private void setUpPlaylists(boolean isEditing) throws IOException { // New/Edit playlist scene
        Parent root2;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/view/popupPlaylist.fxml"));
        root2 = (Parent) fxmlLoader.load();
        if (isEditing) {
            fxmlLoader.<PopupPlaylistController>getController().setInfo(playlistView.getSelectionModel().getSelectedItem());
        }
        fxmlLoader.<PopupPlaylistController>getController().setController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.centerOnScreen();
        stage.show();
    }

    public void deletePlaylist(javafx.event.ActionEvent actionEvent) {
        if (playlistView.getSelectionModel().getSelectedIndex() != -1) {
            playlisModel.deletePlaylist(playlistView.getSelectionModel().getSelectedItem());
        }
        UpdataPlaylist();
    }

    public void editPlaylist(javafx.event.ActionEvent actionEvent) {
        try {
            setUpPlaylists(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displaySongs(MouseEvent mouseEvent) {
        if (playlistView.getSelectionModel().getSelectedIndex() != -1) {
            ObservableList<Song> allSongs = FXCollections.observableArrayList();
            allSongs.addAll( playlistView.getSelectionModel().getSelectedItem().getSongsInPlaylist());
            songsInPlaylist.setItems(allSongs);
        }
    }

    public void deleteSongFromPlaylist(javafx.event.ActionEvent actionEvent) {
        if (songsInPlaylist.getSelectionModel().getSelectedIndex() != -1) {
            playlisModel.deleteSongFromPlaylist(playlistView.getSelectionModel().getSelectedItem(), songsInPlaylist.getSelectionModel().getSelectedItem());
        }
        UpdataPlaylist();
    }

    public void addSongOnPlaylist(javafx.event.ActionEvent actionEvent) {
        if (lstSongs.getSelectionModel().getSelectedIndex() != -1) {
            playlisModel.addSongToPlaylist(playlistView.getSelectionModel().getSelectedItem(), lstSongs.getSelectionModel().getSelectedItem());
        }
        UpdataTable();
    }
}
