package allforkids;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

<<<<<<< HEAD

=======
>>>>>>> ce7a6cc1741dc247942d6c4c5c3d3d7fc4a2930d
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Wassim
 */
public class AllForKids extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
<<<<<<< HEAD
            root = FXMLLoader.load(getClass().getResource("/allforkids/orderManagement/views/orderTable.fxml"));
=======
            primaryStage.setResizable(false);
            root = FXMLLoader.load(getClass().getResource("/allforkids/blog/BlogMain.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("All For Kids");
            primaryStage.setScene(scene);
            primaryStage.show();
>>>>>>> ce7a6cc1741dc247942d6c4c5c3d3d7fc4a2930d
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
        System.setProperty("database", "from_scratch");
        System.setProperty("user", "root");
<<<<<<< HEAD
        System.setProperty("password", "");
       
        
=======
        System.setProperty("password", "root");

>>>>>>> ce7a6cc1741dc247942d6c4c5c3d3d7fc4a2930d
        launch(args);
    }

}
