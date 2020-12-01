package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Server.Model.ProductEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private TextField amount;

    @FXML
    private Button editButton;

    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() {
        try {
            ProductEntity product = (ProductEntity) Client.is.readObject();
            type.setValue(product.getType());
            name.setText(product.getName());
            amount.setText(String.valueOf(product.getAmount()));
            price.setText(String.valueOf(product.getPrice()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

