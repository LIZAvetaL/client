package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import Entity.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableColumn<ProductEntity, Double> price;

    @FXML
    private TableColumn<ProductEntity, Integer> amount;

    @FXML
    private TextField TextFieldProductChoice;

    @FXML
    private Button AddToBasket;

    @FXML
    private Label LabelMessage;
    @FXML
    private Button backButton;


    @FXML
    void initialize() {
        showProduct();
        backButton.setOnAction(actionEvent -> {
            openNewScene("/Window/ClientMainWindow.fxml");
        });
        AddToBasket.setOnAction(event -> {
            String IdProductText = TextFieldProductChoice.getText().trim();
            if (IdProductText.matches("\\d+")==false){
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
                if(message.equals("success"))
                LabelMessage.setText("Товар добавлен в корзину.");
                else LabelMessage.setText("Ошибка.");

            }
        });
    }
    public void showProduct(){
        try {
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            ObservableList<ProductEntity> products = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                ProductEntity product=new ProductEntity();
                String[] infoString = list.get(i).split(" ", 5);
                product.setId_product(Integer.parseInt(infoString[0]));
                product.setType(infoString[1]);
                product.setName(infoString[2]);
                product.setAmount(Integer.parseInt(infoString[3]));
                product.setPrice(Double.parseDouble(infoString[4]));
                products.add(product);
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id_product"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            Product.setItems(products);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void openNewScene(String window) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

