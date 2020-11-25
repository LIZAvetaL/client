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

public class AdminMainController {

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
            openNewScene("/Window/AdminUserWindow.fxml");
        });
        ProductButton.setOnAction(actionEvent -> {
            openNewScene("/Window/AdminProductWindow.fxml");
        });
    }
    public void openNewScene(String window)
    {
        userButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

