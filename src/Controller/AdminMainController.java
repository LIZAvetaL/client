package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminMainController implements NewScreen {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button userButton;

    @FXML
    private Button OrderButton;

    @FXML
    private Button statisticsButton;

    @FXML
    private Button ProductButton;

    @FXML
    void initialize() {
        userButton.setOnAction(actionEvent -> {
            closeAndOpenScene("/Window/AdminUserWindow.fxml");
        });
        ProductButton.setOnAction(actionEvent -> {
            closeAndOpenScene("/Window/AdminProductWindow.fxml");
        });
        OrderButton.setOnAction(actionEvent -> {
            closeAndOpenScene("/Window/AdminOrderWindow.fxml");
        });
        statisticsButton.setOnAction(actionEvent -> {
            closeAndOpenScene("/Window/Statistics.fxml");
        });
    }
    public void closeAndOpenScene(String window){
        userButton.getScene().getWindow().hide();
        openNewScene(window);
    }

}

