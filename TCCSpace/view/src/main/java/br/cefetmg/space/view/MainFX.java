package br.cefetmg.space.view;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainFX extends Application {
    
    private static Stage stage;
    
    private static Scene telaCubesat;
    private static Scene telaExplorar;
    private static Scene telaEquipes;
    private static Scene telaInicial;
    private static Scene telaCadastrarCubesat;
    private static Scene telaCadastro;
    private static Scene telaLogin;
    private static Scene telaEditarCubesat;
    
    private static Scene telaGui3d;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            stage = primaryStage;
                    
            primaryStage.setTitle("CUTE");
            
            Parent loaderTelaEditarCubesat = FXMLLoader.load(getClass().getResource("/fxml/TelaEditarCubesat.fxml"));
            telaEditarCubesat = new Scene(loaderTelaEditarCubesat, 1280, 720);
            
            Parent loaderTelaGui3d = FXMLLoader.load(getClass().getResource("/fxml/Data3DViewer.fxml"));
            telaGui3d = new Scene(loaderTelaGui3d, 1280, 720);
            
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
            
            primaryStage.setScene(telaLogin);
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void changedScreen(String tela, Object userData){
        switch(tela){
            case "Gui3d":
                stage.setScene(telaGui3d);
                notifyAllListeners("Gui3d", userData);
            case "Cubesat":
                stage.setScene(telaCubesat);
                notifyAllListeners("Cubesat", userData);
                break;
            case "Explorar":
                stage.setScene(telaExplorar);
                notifyAllListeners("Explorar", userData);
                break;
            case "Equipes":
                stage.setScene(telaEquipes);
                notifyAllListeners("Equipes", userData);
                break;
            case "Tela Inicial":
                stage.setScene(telaInicial);
                notifyAllListeners("Tela Inicial", userData);
                break;
            case "Cadastrar Cubesat":
                stage.setScene(telaCadastrarCubesat);
                notifyAllListeners("Cadastrar Cubesat", userData);
                break;
            case "Cadastro": 
                stage.setScene(telaCadastro);
                notifyAllListeners("Cadastro", userData);
                break;
            case "Login":
                stage.setScene(telaLogin);
                notifyAllListeners("Login", userData);
                break;
            case "Editar Cubesat":
                stage.setScene(telaEditarCubesat);
                notifyAllListeners("Editar Cubesat", userData);
        }
        
    }
    public static void changedScreen(String tela){
        notifyAllListeners(tela, null);
    }
    
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();
    
    public static interface OnChangeScreen{
        void onScreenChanged(String newScreen, Object userData);
    }
    
    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }
    
    private static void notifyAllListeners(String newScreen, Object userData){
        for(OnChangeScreen l : listeners){
            l.onScreenChanged(newScreen, userData);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
