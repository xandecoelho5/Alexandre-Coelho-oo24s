package br.edu.utfpr.alexandre.coelho.oo24s.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("java.version: " + System.getProperty("java.version"));
        System.out.println("javafx.runtime.version: " + System.getProperty("javafx.runtime.version"));
        
        VBox root = (VBox) FXMLLoader.load(
         this.getClass()
          .getResource("/fxml/FXMLPrincipal.fxml")
        );
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/style.css");
        
        primaryStage.setTitle("Hotelaria Joestar");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
