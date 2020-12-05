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
    protected TableView<ProductEntity> Product;

    @FXML
    protected TableColumn<ProductEntity, Integer> id;

    @FXML
    protected TableColumn<ProductEntity, String> type;

    @FXML
    protected TableColumn<ProductEntity, String> name;

    @FXML
    protected TableColumn<ProductEntity, Double> price;

    @FXML
    protected TableColumn<ProductEntity, Integer> amount;

    @FXML
    private TextField TextFieldProductChoice;

    @FXML
    private Button AddToBasket;

    @FXML
    private Label LabelMessage;
    @FXML
    private Button backButton;
    @FXML
    private Button searchProductButton;
    @FXML
    private TextField AmountTF;
    @FXML
    private ComboBox<String> typeTF;

    @FXML
    private TextField OtpriceTF;
    @FXML
    private TextField DopriceTF;


    @FXML
    void initialize()  {
        showProduct();
        backButton.setOnAction(actionEvent -> {
            closeAndOpenScene(backButton,"/Window/ClientMainWindow.fxml");
        });
        AddToBasket.setOnAction(event -> {
            String IdProductText = TextFieldProductChoice.getText().trim();
            String amountProductText=AmountTF.getText().trim();
            if (IdProductText.matches("\\d+")==false || amountProductText.matches("\\d+")==false || Integer.parseInt(amountProductText)<0 || Integer.parseInt(IdProductText)<0)
               LabelMessage.setText("Ошибка, повторите ввод.");
            else{
                String message="Basket_addToBasket_"+IdProductText+"_"+amountProductText+"_"+Client.getId_user();
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
        searchProductButton.setOnAction(actionEvent -> {
            //openSecondWin(backButton_"/Window/SearchProductWindow.fxml");
            String type=typeTF.getValue();
            String beginPrice=OtpriceTF.getText().trim();
            String endPrice=DopriceTF.getText().trim();
            if (type==null || beginPrice==null|| beginPrice.matches("\\d+")==false|| endPrice==null|| endPrice.matches("\\d+")==false) LabelMessage.setText("Заполните поля.");
            else {
                String message="Product_findProductByTypeAndPrice_"+type+"_"+beginPrice+"_"+endPrice;
                try {
                    Client.os.writeObject(message);
                    showSearchProduct();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public void showProduct(){
        try {
            String clientMessage = "Product_ShowProduct";
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
    public void showSearchProduct() {
        List<ProductEntity> list=null;
        try {
            list= (List<ProductEntity>) Client.is.readObject();
        } catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();}
        ObservableList<ProductEntity> products = FXCollections.observableArrayList();
        for (ProductEntity product:list)
            products.add(product);
        try {
            id.setCellValueFactory(new PropertyValueFactory<>("id_product"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            Product.setItems(products);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

