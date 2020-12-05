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

public class ClientMainController implements NewScreen{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Basket;

    @FXML
    private Button Product;

    @FXML
    private Button HistoryOrder;
    @FXML
    private Button reviewButton;
    @FXML
    private Button changePasswordButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        Product.setOnAction(event -> {
            closeAndOpenScene(backButton,"/Window/ShowProductWindow.fxml");
        });
        Basket.setOnAction(actionEvent -> {
            closeAndOpenScene(backButton,"/Window/ShowBasketWindow.fxml");
        });
        HistoryOrder.setOnAction(actionEvent -> {
            closeAndOpenScene(backButton,"/Window/HistoryOfOrderWindow.fxml");
        });
        reviewButton.setOnAction(actionEvent -> {
            closeAndOpenScene(backButton,"/Window/ReviewsWindow.fxml");
        });
        changePasswordButton.setOnAction(actionEvent -> {
            openSecondWin(backButton,"/Window/ChangePasswordWindow.fxml");
        });


        backButton.setOnAction(actionEvent -> {
            closeAndOpenScene(backButton,"/Window/LogInWindow.fxml");
        });

    }
}

