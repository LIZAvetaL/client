package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Server.Model.ProductEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class AddReviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea reviewArea;

    @FXML
    private Button sendButton;

    @FXML
    private Label model;

    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        MessageLabel.setWrapText(true);
        ProductEntity product= (ProductEntity) Client.is.readObject();
        if (product==null) MessageLabel.setText("Такого товара не существует.");
        else {
            model.setText(product.getName());
            sendButton.setOnAction(actionEvent -> {
                String review= reviewArea.getText();
                if (review.length()>500) MessageLabel.setText("Отзыв первышает 500 символов.");
                else {
                    String message="Review_addReview_"+Client.getId_user()+"_"+product.getId_product()+"_"+review;
                    try {
                        Client.os.writeObject(message);
                        message= (String) Client.is.readObject();
                        if(message.equals("success")) MessageLabel.setText("Отзыв добавлен.");
                        else MessageLabel.setText("Ошибка.");
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
