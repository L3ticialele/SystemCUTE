package controllers;
import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.view.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TelaPerfilController implements Initializable {

   @FXML
    private Button botaoCubesat;

    @FXML
    private Button botaoSuporte;
    
    private UsuarioDTO usuario;

    @FXML
    private Button botaoPerfil;
    
    @FXML
    private Button botaoHome;

    @FXML
    private Label label;
    
    @FXML
    private ImageView iconeSuporte;
    
    @FXML
    private ImageView iconeCubesat;
    
    @FXML
    void cubesatToPourple(MouseEvent event){
        botaoCubesat.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeCubesat.setImage(new Image("file:src/main/resources/images/iconeCubesatLilas.png"));
    }
    
    @FXML
    void cubesatToWhite(MouseEvent event){
        botaoCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeCubesat.setImage(new Image("file:src/main/resources/images/iconeCubesat.png"));
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
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen(){
           @Override
           public void onScreenChanged(String newString, Object viewData){
               usuario = (UsuarioDTO)viewData;
           }
       });
    }
    
}
