package Controller;


import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Server.Model.BasketEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ShowBasketController implements NewScreen{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BasketEntity> table;

    @FXML
    private TableColumn<BasketEntity, String> name;

    @FXML
    private TableColumn<BasketEntity, Integer> amount;

    @FXML
    private TableColumn<BasketEntity, Double> price;
    @FXML
    private TableColumn<BasketEntity, Integer> id;
    @FXML
    private TextField deleteIdTF;

    @FXML
    private Button deleteBasketButton;
    @FXML
    private Button backButton;
    @FXML
    private Button OrderButton;
    @FXML
    private Label MessageLabel;

    @FXML
    void initialize() {
        showBasket();
        backButton.setOnAction(actionEvent -> {
            closeAndOpenScene(backButton,"/Window/ClientMainWindow.fxml");
        });
        deleteBasketButton.setOnAction(actionEvent -> {
            String idBasket=deleteIdTF.getText().trim();
            if (idBasket.matches("\\d+")==false || Integer.parseInt(idBasket)<0) {
                String message = "Basket_deleteBasket_" + idBasket;
                try {
                    Client.os.writeObject(message);
                    message = (String) Client.is.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (message.equals("success"))
                    showBasket();
                else MessageLabel.setText("Ошибка.");
            }
        });
        OrderButton.setOnAction(actionEvent -> {
            String  message="Order_addToOrder_"+Client.getId_user();
            try {
                Client.os.writeObject(message);
                message= (String) Client.is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (message.equals("success")) {
                openSecondWin(backButton,"/Window/OrderWindow.fxml");
                saveFile();
                message="Basket_deleteAll_"+Client.getId_user();
                try {
                    Client.os.writeObject(message);
                    message= (String) Client.is.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                showBasket();
            }
        });

    }

    private void saveFile() {
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(new File("myOrder.txt"));
        for (BasketEntity basket: table.getItems()) {
            String text="Модель: " + basket.getName() + "Цена: " + basket.getPrice() + "Количество: " + basket.getAmount()+" \r\n";
            byte[] buffer = text.getBytes();
            f.write(buffer, 0, buffer.length);
        }
        f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBasket() {
        try {
            String clientMessage = "Basket_ShowBasket_"+Client.getId_user();
            Client.os.writeObject(clientMessage);
            List<BasketEntity> list=(List<BasketEntity>)Client.is.readObject();
            ObservableList<BasketEntity> baskets = FXCollections.observableArrayList();
            for (BasketEntity basket :list)
                baskets.add(basket);
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            table.setItems(baskets);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

