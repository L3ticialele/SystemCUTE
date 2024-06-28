/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package br.cefetmg.space.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private static Scene telaCadastrarCubesat;
    private static Scene telaCadastro;
    private static Scene telaLogin;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            stage = primaryStage;
                    
            primaryStage.setTitle("CUTE");
            
            Parent  loaderTelaCubesat = FXMLLoader.load(getClass().getResource("/fxml/TelaCubesat.fxml"));
            telaCubesat = new Scene(loaderTelaCubesat, 1280, 720);
            
            Parent loaderTelaExplorar = FXMLLoader.load(getClass().getResource("/fxml/TelaExplorar.fxml"));
            telaExplorar = new Scene(loaderTelaExplorar, 1280, 720);
            
            Parent loaderTelaEquipes = FXMLLoader.load(getClass().getResource("/fxml/TelaEquipes.fxml"));
            telaEquipes = new Scene(loaderTelaEquipes, 1280, 720);
             
            Parent loaderTelaInicial = FXMLLoader.load(getClass().getResource("/fxml/TelaInicial.fxml"));
            telaInicial = new Scene(loaderTelaInicial, 1280, 720);
            
            Parent loaderTelaCadastrarCubesat = FXMLLoader.load(getClass().getResource("/fxml/TelaCadastrarCubesat.fxml"));
            telaCadastrarCubesat = new Scene(loaderTelaCadastrarCubesat, 1280, 720);
            
            Parent loaderTelaCadastro = FXMLLoader.load(getClass().getResource("/fxml/TelaCadastro.fxml"));
            telaCadastro = new Scene(loaderTelaCadastro, 1280, 720);
            
            Parent loaderTelaLogin = FXMLLoader.load(getClass().getResource("/fxml/TelaLogin.fxml"));
            telaLogin = new Scene(loaderTelaLogin, 1280, 720);
            
            //primaryStage.initStyle(StageStyle.UNDECORATED);
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
            case "Cadastrar Cubesat":
                stage.setScene(telaCadastrarCubesat);
                break;
            case "Login":
                stage.setScene(telaLogin);
                break;
            case "Cadastro": 
                stage.setScene(telaCadastro);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
