package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Entity.ProductEntity;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ProductEntity> Product;

    @FXML
    private TableColumn<ProductEntity, Integer> id;

    @FXML
    private TableColumn<ProductEntity, String> type;

    @FXML
    private TableColumn<ProductEntity, String> name;

    @FXML
    private TableColumn<ProductEntity, Integer> price;

    @FXML
    private TableColumn<ProductEntity, Integer> amount;

    @FXML
    private TextField TextFieldProductChoice;

    @FXML
    private Button AddToBasket;

    @FXML
    private Label LabelMessage;


    @FXML
    void initialize() {
        try {
            List<ProductEntity> list = (List<ProductEntity>) Client.is.readObject();
        ObservableList<ProductEntity> products = FXCollections.observableArrayList();
        for (ProductEntity product:list){
            products.add(product);
        }
        id.setCellValueFactory(new PropertyValueFactory<ProductEntity, Integer>("id_product"));
        type.setCellValueFactory(new PropertyValueFactory<ProductEntity, String>("type"));
        name.setCellValueFactory(new PropertyValueFactory<ProductEntity, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<ProductEntity, Integer>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<ProductEntity, Integer>("amount"));
        Product.setItems(products);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        AddToBasket.setOnAction(event -> {
            String IdProductText = TextFieldProductChoice.getText().trim();
            if (IdProductText.matches("\\d+")==false || id.equals(IdProductText)==false){
               LabelMessage.setText("Ошибка, повторите ввод.");
            }
            else{
                String message="Basket,addToBasket,"+IdProductText+","+Client.getId_user();
                try {
                    Client.os.writeObject(message);
                    message= (String) Client.is.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(message=="success")
                LabelMessage.setText("Товар добавлен в корзину.");
            }
        });
    }
}

