package projectEsprit;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Wassim
 */
public class projectEsprit extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Parent root = null;
        try { 
            primaryStage.setResizable(false);
            root = FXMLLoader.load(getClass().getResource("/projectEsprit/login/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("projectEsprit");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println(ex.getMessage());
            
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("host", "localhost");
        System.setProperty("port", "3306");
        System.setProperty("database", "yosri");
        System.setProperty("user", "root");
        System.setProperty("password", "root");
        System.setProperty("uploads_folder", "/home/kbach/myDemo/yosri/");
        launch(args);
    }
    
}
