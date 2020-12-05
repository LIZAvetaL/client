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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReviewController implements NewScreen {

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
    private TextField ProductChoiceTF;

    @FXML
    private Button showReview;

    @FXML
    private Label LabelMessage;

    @FXML
    private Button backButton;

    @FXML
    private Button addReview;

    @FXML
    void initialize() {
        showProduct();
        backButton.setOnAction(actionEvent -> {
            closeAndOpenScene(backButton,"/Window/ClientMainWindow.fxml");
        });
        showReview.setOnAction(actionEvent -> {
            String idProduct=ProductChoiceTF.getText().trim();
            if(idProduct!=null){
                String message="Review_showReviews_"+idProduct;
                try {
                    Client.os.writeObject(message);
                    openSecondWin(backButton,"/Window/ShowReviewsWindow.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        addReview.setOnAction(actionEvent -> {
            String idProduct=ProductChoiceTF.getText().trim();
            if(idProduct!=null){
                String message="Product_findProductByID_"+idProduct;
                try {
                    Client.os.writeObject(message);
                    openSecondWin(backButton,"/Window/AddReviewWindow.fxml");
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
            List<ProductEntity> list= (List<ProductEntity>) Client.is.readObject();
            ObservableList<ProductEntity> products = FXCollections.observableArrayList();
            for (ProductEntity product:list)
                products.add(product);
            id.setCellValueFactory(new PropertyValueFactory<>("id_product"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            Product.setItems(products);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
