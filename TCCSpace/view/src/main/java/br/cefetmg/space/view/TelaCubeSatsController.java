package br.cefetmg.space.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author letic
 */
public class TelaCubeSatsController extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaCubeSats.fxml"));
            BorderPane root = loader.load();
            Scene scene = new Scene(root, 1181.0, 725.0);
            stage.setScene(scene);       
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
