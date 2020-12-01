package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button OkButton;
    @FXML
    private Label priceLabel;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        String message= (String) Client.is.readObject();
        priceLabel.setText(message);
        OkButton.setOnAction(actionEvent -> {
            OkButton.getScene().getWindow().hide();
        });
    }
}

