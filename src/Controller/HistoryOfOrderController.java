package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Server.Model.OrdersEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class HistoryOfOrderController implements NewScreen {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<OrdersEntity> Table;

    @FXML
    private TableColumn<OrdersEntity, Integer> OrderNumber;

    @FXML
    private TableColumn<OrdersEntity, Double> Price;

    @FXML
    private TableColumn<OrdersEntity, String> Status;

    @FXML
    private Button BackButton;

    @FXML
    void initialize() {
       showOrder();
       BackButton.setOnAction(actionEvent ->{
           closeAndOpenScene("/Window/ClientMainWindow.fxml");
       });
    }

    private void showOrder() {
        String message="Order,showOrder,"+Client.getId_user();
        try {
            Client.os.writeObject(message);
            List<OrdersEntity> list = (List<OrdersEntity>) Client.is.readObject();
            ObservableList<OrdersEntity> orders = FXCollections.observableArrayList();
           for (OrdersEntity order: list)
               orders.add(order);
            OrderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
            Price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
            Status.setCellValueFactory(new PropertyValueFactory<>("status"));
            Table.setItems(orders);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void closeAndOpenScene(String window) {
        BackButton.getScene().getWindow().hide();
        openNewScene(window);
    }
}

