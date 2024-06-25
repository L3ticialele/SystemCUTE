/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package br.cefetmg.space.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author letic
 */
public class MainFX extends Application {
    
    private static Stage stage;
    
    private static Scene telaCubesat;
    private static Scene telaExplorar;
    private static Scene telaEquipes;
    private static Scene telaInicial;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            stage = primaryStage;
                    
            primaryStage.setTitle("Tela Inicial");
            
            Parent  loaderTelaCubesat = FXMLLoader.load(getClass().getResource("/fxml/TelaCubesat.fxml"));
            telaCubesat = new Scene(loaderTelaCubesat, 640, 400);
            
            Parent loaderTelaExplorar = FXMLLoader.load(getClass().getResource("/fxml/TelaExplorar.fxml"));
            telaExplorar = new Scene(loaderTelaExplorar, 640, 400);
            
            Parent loaderTelaEquipes = FXMLLoader.load(getClass().getResource("/fxml/TelaEquipes.fxml"));
            telaEquipes = new Scene(loaderTelaEquipes, 640, 400);
             
            Parent loaderTelaInicial = FXMLLoader.load(getClass().getResource("/fxml/TelaInicial.fxml"));
            telaInicial = new Scene(loaderTelaInicial, 640, 400);
            
            primaryStage.setScene(telaInicial);
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void changedScreen(String tela){
        switch(tela){
            case "Cubesat":
                stage.setScene(telaCubesat);
                break;
            case "Explorar":
                stage.setScene(telaExplorar);
                break;
            case "Equipes":
                stage.setScene(telaEquipes);
                break;
            case "Tela Inicial":
                stage.setScene(telaInicial);
                break;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
