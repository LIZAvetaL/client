package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Application {
    public static Socket socket;
    public static ObjectOutputStream os;
    public static ObjectInputStream is;
    public static boolean connected = false;
    private static int id_user=0;
    private static String tema="/Window/light.css";


    public static void Connect()
    {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 8070);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
            connected = true;
        } catch (UnknownHostException var1) {
            connected = false;
            System.err.println("Address not available" + var1);
        } catch (IOException var2) {
            connected = false;
            System.err.println("I/О thread error" + var2);
        }
    }
    public static void setId_user(int id){
        id_user=id;}
    public static String getId_user(){
        return Integer.toString(id_user);}

    public static String getTema() {
        return tema;
    }

    public static void setTema(String tema) {
        Client.tema = tema;
    }

    public static void Disconnect() {
        try {
            if (is != null) {
                is.close();
            }

            if (os != null) {
                os.close();
            }

            if (socket != null) {
                socket.close();
            }

            connected = false;
        } catch (IOException var1) {
            var1.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Client.class.getResource("/Window/LogInWindow.fxml"));
        Parent root =  loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
