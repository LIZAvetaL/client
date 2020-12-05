package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;

public class StatisticsController implements NewScreen {
    @FXML
    private Button backButton;

    @FXML
    private PieChart pieChart;

    @FXML
    void initialize() {
        showStatistics();
        backButton.setOnAction(actionEvent -> {
            closeAndOpenScene(backButton,"/Window/AdminMainWindow.fxml");
        });
    }
    public void showStatistics() {
        try {
            String clientMessage = "Product_showTypes";
            Client.os.writeObject(clientMessage);
            ArrayList<String> list = (ArrayList<String>) Client.is.readObject();
            ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList(
                    new PieChart.Data("Телефон", Double.valueOf(list.get(0))),
                    new PieChart.Data("Ноутбук", Double.valueOf(list.get(1))),
                    new PieChart.Data("Принтер", Double.valueOf(list.get(2))),
                    new PieChart.Data("Планшет", Double.valueOf(list.get(3)))
            );
            pieChart.setData(pieChartList);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
