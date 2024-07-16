import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
        primaryStage.setTitle("Recepcao USB");//Titulo da janel
        Scene tela = new Scene(root);   // Cria a tela baseado no design Scene Builder
        primaryStage.setScene(tela);    //Define qual cena a apresentar
        primaryStage.show();            //Exibe Tela
    }

    @Override
    public void stop() throws Exception {
        // Interromper threads que estão em execução
        ThreadUartAerospace.setEncerrar(true);//Fala para ThreadUart encerrar
        ReceptorUart.notificarTodasThreads();   //Notifica Todas as Threads para evitar bloqueios do tipo wait
        super.stop();//Finaliza tudo
    }

    public static void main(String[] args) {
        launch(args);
    }
}