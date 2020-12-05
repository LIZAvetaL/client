package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField price;

    @FXML
    private TextField name;

    @FXML
    private TextField amount;

    @FXML
    private ComboBox<String> type;

    @FXML
    private Button addButton;

    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() {
        addButton.setOnAction(actionEvent -> {
        String Type=type.getValue();
        String Name=name.getText().trim();
        String Amount=amount.getText().trim();
        String Price=price.getText().trim();
        if(Type==null || Name==null ||Amount==null||Price==null || Amount.matches("\\d+")==false|| Price.matches("\\d+")==false) MessageLabel.setText("Ошибка.Повторите ввод");
        else {
            String message ="Product_addProduct_"+ Type + "_" + Name + "_" + Amount + "_" + Price;
            try {
                Client.os.writeObject(message);
                 message= (String) Client.is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (message.equals("success"))MessageLabel.setText("Товар добавлен");
            else MessageLabel.setText("Ошибка добавления");
        }
        });
    }
}

