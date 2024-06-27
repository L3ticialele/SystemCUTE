/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import br.cefetmg.space.view.MainFX;
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

/**
 * FXML Controller class
 *
 * @author letic
 */
public class TelaInicialController implements Initializable {
    
    @FXML
    private Button botaoCubesat;

    @FXML
    private Button botaoEquipe;

    @FXML
    private Button botaoExplorar;
    
    @FXML
    private Button botaoHome;

    @FXML
    private Label label;
    
    @FXML
    private ImageView iconeEquipes;
    
    @FXML
    private ImageView iconeCubesat;
    
    @FXML
    private ImageView iconeExplorar;
    
    @FXML
    void explorarToPourple(MouseEvent event){
        botaoExplorar.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeExplorar.setImage(new Image("file:src/main/resources/images/iconeExplorarLilas.png"));
    }
    
    @FXML
    void explorarToWhite(MouseEvent event){
        botaoExplorar.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeExplorar.setImage(new Image("file:src/main/resources/images/iconeExplorar.png"));
    }
    
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
    void equipesToPourple(MouseEvent event){
        botaoEquipe.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeEquipes.setImage(new Image("file:src/main/resources/images/iconeEquipesLilas.png"));
    }
    
    @FXML
    void equipesToWhite(MouseEvent event){
        botaoEquipe.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeEquipes.setImage(new Image("file:src/main/resources/images/iconeEquipes.png"));
    }

    @FXML
    void apresentaTelaCubesat(ActionEvent event) {
        MainFX.changedScreen("Cubesat");
    }

    @FXML
    void apresentaTelaEquipe(ActionEvent event) {
        MainFX.changedScreen("Equipes");
    }

    @FXML
    void apresentaTelaExplorar(ActionEvent event) {
        MainFX.changedScreen("Explorar");
    }
    
    
    @FXML
    void apresentarTelaInicial(ActionEvent event) {
        MainFX.changedScreen("Tela Inicial");
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
    
}
