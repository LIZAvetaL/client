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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminProductController implements NewScreen{

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
            String message="Product_deleteProduct_"+deleteId;
            try {
                Client.os.writeObject(message);
                message= (String) Client.is.readObject();
                if(message.equals("success")) showProduct();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        backButton.setOnAction(actionEvent ->{
            closeAndOpenScene(backButton,"/Window/AdminMainWindow.fxml");
        } );
        addButton.setOnAction(actionEvent -> {
            openSecondWin(backButton,"/Window/AddProductWindow.fxml");
        });
        editButton.setOnAction(actionEvent -> {
            String deleteId= idTF.getText().trim();
            if (deleteId!=null || Integer.parseInt(deleteId)>0){
            String message="Product_editProduct_"+deleteId;
            try {
                Client.os.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            openSecondWin(backButton,"/Window/EditProductWindow.fxml");
            }
        });
    }

    public void showProduct(){
        try {
            String clientMessage = "Product_ShowProduct";
            Client.os.writeObject(clientMessage);
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
        } catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
