package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Server.Model.UsersEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminUserController implements NewScreen{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<UsersEntity> table;

    @FXML
    private TableColumn<UsersEntity, Integer> id;

    @FXML
    private TableColumn<UsersEntity, String> login;

    @FXML
    private TableColumn<UsersEntity, String> password;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button addAdminButton;

    @FXML
    private Button backButton;
    @FXML
    private TextField idTF;

    @FXML
    void initialize() {
        showUser();
        deleteUserButton.setOnAction(actionEvent -> {
            String deleteId= idTF.getText();
            String message="User_deleteUser_"+deleteId;
            try {
                Client.os.writeObject(message);
                message= (String) Client.is.readObject();
                if(message.equals("success")) showUser();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        addAdminButton.setOnAction(actionEvent -> {
            openSecondWin(backButton,"/Window/AddAdminWindow.fxml");
        });
        backButton.setOnAction(actionEvent ->{
            closeAndOpenScene(backButton,"/Window/AdminMainWindow.fxml");
        } );
    }

    private void showUser() {
        try {
        String clientMessage = "User_showUser";
        Client.os.writeObject(clientMessage);
        List<UsersEntity> list = (List<UsersEntity>) Client.is.readObject();
        ObservableList<UsersEntity> users = FXCollections.observableArrayList();
        for (UsersEntity user:list)
            users.add(user);
        id.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        table.setItems(users);
    } catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

