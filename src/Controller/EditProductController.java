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
    private ComboBox<String> editChoice;

    @FXML
    private TextField editTF;

    @FXML
    private Button editButton;

    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        ProductEntity product = (ProductEntity) Client.is.readObject();
        ProductEntity finalProduct = product;
        editButton.setOnAction(actionEvent -> {
            String edit=editTF.getText().trim();
            if(edit!=null){
                try {
                 switch (editChoice.getValue()){
                    case "Модель": finalProduct.setName(edit);
                        Client.os.writeObject(finalProduct);
                        break;
                    case "Цена":finalProduct.setPrice(Double.parseDouble(edit));
                        Client.os.writeObject(finalProduct);
                        break;
                    case "Количество":finalProduct.setAmount(Integer.parseInt(edit));
                        Client.os.writeObject(finalProduct);
                    }
                    String message= (String) Client.is.readObject();
                    if (message.equals("success")) MessageLabel.setText("Товар отредактирован");
                    else MessageLabel.setText("Ошибка.");
                 } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}

