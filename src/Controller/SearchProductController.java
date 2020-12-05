package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Server.Model.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchProductController extends ShowProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> typeTF;

    @FXML
    private TextField OtpriceTF;
    @FXML
    private TextField DopriceTF;

    @FXML
    private Button sendButton;
    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() {
        sendButton.setOnAction(actionEvent -> {
            String type=typeTF.getValue();
            String beginPrice=OtpriceTF.getText().trim();
            String endPrice=DopriceTF.getText().trim();
            if (type==null || beginPrice==null|| beginPrice.matches("\\d+")==false|| endPrice==null|| endPrice.matches("\\d+")==false) MessageLabel.setText("Заполните поля.");
            else {
                String message="Product,findProductByTypeAndPrice,"+type+","+beginPrice+","+endPrice;
                try {
                    Client.os.writeObject(message);
                    sendButton.getScene().getWindow().hide();
                    showSearchProduct();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }

        });
    }

}
