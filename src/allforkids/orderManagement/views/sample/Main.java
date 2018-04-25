package allforkids.orderManagement.views.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage)  {
//        Parent root = FXMLLoader.load(getClass().getResource("now.fxml"));
//
//        Scene scene = new Scene(root);
//
//        scene.setFill(Color.TRANSPARENT);
//
//        primaryStage.setScene(scene);
//
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//
//        primaryStage.show();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("now.fxml"));
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println(ex.getMessage());

        }
        Scene scene = new Scene(root);
        primaryStage.setTitle("All For Kids");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        System.setProperty("host", "127.0.0.1");
        System.setProperty("port", "3306");
        System.setProperty("database", "from_scratch");
        System.setProperty("user", "root");
        System.setProperty("password", "");

        launch(args);
    }
}
