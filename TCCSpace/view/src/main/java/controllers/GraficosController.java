package controllers;

import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.view.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GraficosController implements Initializable {

    @FXML
    private Button botaoCubesat;

    @FXML
    private Button botaoHome;
    
    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoPerfil;

    @FXML
    private Button botaoSuporte;

    @FXML
    private Button grafico01;

    @FXML
    private Button grafico02;

    @FXML
    private Button grafico03;

    @FXML
    private ImageView iconePerfil;

    @FXML
    private ImageView iconeSuporte;
    
    private CubeSat cubesat;

    @FXML
    void apresentaTelaPerfil(ActionEvent event) {

    }

    @FXML
    void apresentaTelaSuporte(ActionEvent event) {

    }

    @FXML
    void apresentarTelaInicial(ActionEvent event) {

    }

    @FXML
    void voltarData3Dviewer(ActionEvent event) throws IOException{
        MainFX.changedScreen("Gui3d", cubesat);
    }
    
    @FXML
    void displayGrafico01(ActionEvent event) {

    }

    @FXML
    void displayGrafico02(ActionEvent event) {

    }

    @FXML
    void displayGrafico03(ActionEvent event) {

    }
    
    @FXML
    void perfilToPourple(MouseEvent event) {
        botaoPerfil.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconePerfil.setImage(new Image("file:src/main/resources/images/userLilas.png"));
    }

    @FXML
    void perfilToWhite(MouseEvent event) {
        botaoPerfil.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconePerfil.setImage(new Image("file:src/main/resources/images/user.png"));
    }

    @FXML
    void suporteToPourple(MouseEvent event) {
        botaoSuporte.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeSuporte.setImage(new Image("file:src/main/resources/images/suporteLilas.png"));
    }

    @FXML
    void suporteToWhite(MouseEvent event) {
        botaoSuporte.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeSuporte.setImage(new Image("file:src/main/resources/images/suport.png"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
            if (viewData instanceof CubeSat) {
                cubesat = (CubeSat) viewData;
            }
        });
    }

}
