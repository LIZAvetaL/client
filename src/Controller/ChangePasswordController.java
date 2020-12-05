package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Server.Model.UsersEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChangePasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private Label MessageLabel;

    @FXML
    private Button changeButton;

    @FXML
    void initialize() {
        changeButton.setOnAction(actionEvent -> {
            String login=loginTF.getText().trim();
            String password=passwordTF.getText().trim();
            if (login==null || password==null) MessageLabel.setText("Заполните поля.");
            else {
                UsersEntity user=new UsersEntity();
                String message="User_findByID_"+Client.getId_user();
                try {
                    Client.os.writeObject(message);
                    user= (UsersEntity) Client.is.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(!user.getLogin().equals(login)) MessageLabel.setText("Проверьте свой логин.");
                else {
                    message="User_changePassword_"+Client.getId_user()+"_"+password;
                    try {
                        Client.os.writeObject(message);
                        message= (String) Client.is.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(message.equals("success")) MessageLabel.setText("Пароль изменен.");

                }
            }
        });
    }
}
