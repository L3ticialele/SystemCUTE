package controllers;

import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.view.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.MeshView;

public class BaseController implements Initializable{

    @FXML
    private AnchorPane anchorPane3d;

    @FXML
    private Button botaoCubesat;

    @FXML
    private Button botaoSuporte;

    @FXML
    private Button botaoPerfil;

    @FXML
    private Button botaoHome;

    @FXML
    private ImageView iconeSuporte;

    @FXML
    private ImageView iconePerfil;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private MenuBar menuBarTrocarGrafico;

    @FXML
    private MenuItem menuItemAltitude;

    @FXML
    private MenuItem menuItemTemperatura;

    @FXML
    private MeshView meshVIew3D;
    
    private Usuario usuario;
    
    @FXML
    void perfilToPourple(MouseEvent event){
        botaoPerfil.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconePerfil.setImage(new Image("file:src/main/resources/images/userLilas.png"));
    }
    
    
    @FXML
    void perfilToWhite(MouseEvent event){
        botaoPerfil.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconePerfil.setImage(new Image("file:src/main/resources/images/user.png"));
    }
    
    @FXML
    void suporteToPourple(MouseEvent event){
        botaoSuporte.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeSuporte.setImage(new Image("file:src/main/resources/images/suporteLilas.png"));
    }
    
    @FXML
    void suporteToWhite(MouseEvent event){
        botaoSuporte.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeSuporte.setImage(new Image("file:src/main/resources/images/suport.png"));
    }
    
    @FXML
    void telaCadastrarCubesat(ActionEvent event) throws IOException{
        MainFX.changedScreen("Cadastrar Cubesat", usuario);
    }
    

    @FXML
    void apresentaTelaCubesat(ActionEvent event) throws IOException {
        MainFX.changedScreen("Cubesat", usuario);
    }

    @FXML
    void apresentaTelaSuporte(ActionEvent event) throws IOException {
        MainFX.changedScreen("Suporte", usuario);
    }

    @FXML
    void apresentaTelaPerfil(ActionEvent event) throws IOException {
        MainFX.changedScreen("Perfil", usuario);
    }
    
    
    @FXML
    void apresentarTelaInicial(ActionEvent event) throws IOException {
        MainFX.changedScreen("Tela Inicial", usuario);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

}
