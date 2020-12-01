package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Server.Model.ProductEntity;
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

public class AdminProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ProductEntity> table;

    @FXML
    private TableColumn<ProductEntity, Integer> id;

    @FXML
    private TableColumn<ProductEntity, String> type;

    @FXML
    private TableColumn<ProductEntity, String> name;

    @FXML
    private TableColumn<ProductEntity, Integer> amount;

    @FXML
    private TableColumn<ProductEntity, Double> price;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField idTF;

    @FXML
    void initialize() {
        showProduct();
        deleteButton.setOnAction(actionEvent -> {
            String deleteId= idTF.getText();
            String message="Product,deleteProduct,"+deleteId;
            try {
                Client.os.writeObject(message);
                message= (String) Client.is.readObject();
                if(message.equals("success")) showProduct();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        backButton.setOnAction(actionEvent ->{
            openNewScene("/Window/AdminMainWindow.fxml");
        } );
        addButton.setOnAction(actionEvent -> {
            openSecondWin("/Window/AddProductWindow.fxml");
        });
        editButton.setOnAction(actionEvent -> {
            String deleteId= idTF.getText();
            String message="Product,editProduct,"+deleteId;
            try {
                Client.os.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            openSecondWin("/Window/EditProductWindow.fxml");
        });
    }

    private void openSecondWin(String win) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(win));
        Scene newScene;
        try {
            newScene = new Scene(loader.load());
        } catch (IOException ex) {
            return;
        }
        Stage inputStage = new Stage();
        inputStage.initOwner(backButton.getScene().getWindow());
        inputStage.setScene(newScene);
        inputStage.show();
    }

    public void showProduct(){
        try {
            String clientMessage = "Product,ShowProduct";
            Client.os.writeObject(clientMessage);
           /* ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
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
            }*/
            List<ProductEntity> list= (List<ProductEntity>) Client.is.readObject();
            ObservableList<ProductEntity> products = FXCollections.observableArrayList();
            for (ProductEntity product:list)
                products.add(product);
            id.setCellValueFactory(new PropertyValueFactory<>("id_product"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            table.setItems(products);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void openNewScene(String window){
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
