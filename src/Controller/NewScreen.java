package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public interface NewScreen {
     default void closeAndOpenScene(Button button,String window) {
         button.getScene().getWindow().hide();
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
        stage.getScene().getStylesheets().add(Client.getTema());
        stage.show();
    }
    default void openSecondWin(Button button, String win) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource(win));
             Scene newScene;
             try {
                 newScene = new Scene(loader.load());
             } catch (IOException ex) {
                 return;
             }
             Stage inputStage = new Stage();
             inputStage.initOwner(button.getScene().getWindow());
             inputStage.setScene(newScene);
             inputStage.getScene().getStylesheets().add(Client.getTema());
             inputStage.show();
         }catch (Exception e){
             e.printStackTrace();
         }
    }
}
