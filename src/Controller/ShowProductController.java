package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import Server.Model.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowProductController implements NewScreen{

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
    private TextField AmountTF;


    @FXML
    void initialize()  {
        showProduct();
        backButton.setOnAction(actionEvent -> {
            closeAndOpenScene("/Window/ClientMainWindow.fxml");
        });
        AddToBasket.setOnAction(event -> {
            String IdProductText = TextFieldProductChoice.getText().trim();
            String amountProductText=AmountTF.getText().trim();
            if (IdProductText.matches("\\d+")==false || amountProductText.matches("\\d+")==false || Integer.parseInt(amountProductText)<0 || Integer.parseInt(IdProductText)<0)
               LabelMessage.setText("Ошибка, повторите ввод.");
            else{
                String message="Basket,addToBasket,"+IdProductText+","+amountProductText+","+Client.getId_user();
                try {
                    Client.os.writeObject(message);
                    message= (String) Client.is.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(message.equals("success")){
                LabelMessage.setText("Товар добавлен в корзину.");
                showProduct();}
                else LabelMessage.setText("Ошибка.");

            }
        });
    }

    private void closeAndOpenScene(String window) {
        backButton.getScene().getWindow().hide();
        openNewScene(window);
    }

    public void showProduct(){
        try {
            String clientMessage = "Product,ShowProduct";
            Client.os.writeObject(clientMessage);
            List<ProductEntity>list= (List<ProductEntity>) Client.is.readObject();
            ObservableList<ProductEntity> products = FXCollections.observableArrayList();
            for (ProductEntity product:list)
                products.add(product);
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

}

