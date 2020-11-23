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

public class ClientMainController {

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
    private Button backButton;

    @FXML
    void initialize() {
        Product.setOnAction(event -> {
            String clientMessage = "Product,ShowProduct";
            try {
                Client.os.writeObject(clientMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            openNewScene("/Window/ShowProductWindow.fxml");
        });
        Basket.setOnAction(actionEvent -> {
            String clientMessage = "Basket,ShowBasket";
            try {
                Client.os.writeObject(clientMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            openNewScene("/Window/ShowBasketWindow.fxml");
        });

    }
    public void openNewScene(String window)
    {
        Product.getScene().getWindow().hide();

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

