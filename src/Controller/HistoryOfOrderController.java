package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Server.Model.OrderEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistoryOfOrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<OrderEntity> Table;

    @FXML
    private TableColumn<OrderEntity, Integer> OrderNumber;

    @FXML
    private TableColumn<OrderEntity, Double> Price;

    @FXML
    private TableColumn<OrderEntity, String> Status;

    @FXML
    private Button BackButton;

    @FXML
    void initialize() {
       showOrder();
    }

    private void showOrder() {
        String message="Order,showOrder,"+Client.getId_user();
        try {
            Client.os.writeObject(message);
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            ObservableList<OrderEntity> orders = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                OrderEntity order = new OrderEntity();
                String[] infoString = list.get(i).split(",", 3);
                order.setOrderNumber(Integer.parseInt(infoString[0]));
                order.setTotalPrice(Double.parseDouble(infoString[1]));
                order.setStatus(infoString[2]);
                orders.add(order);
            }
            OrderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
            Price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
            Status.setCellValueFactory(new PropertyValueFactory<>("status"));
            Table.setItems(orders);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

