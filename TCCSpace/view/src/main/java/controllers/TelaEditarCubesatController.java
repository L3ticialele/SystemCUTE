/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author letic
 */
public class TelaEditarCubesatController implements Initializable {
    
   private BufferedImage imagem;
 
   private UsuarioDTO usuario;
    
   @FXML
   private Button botaoCubesat;
   
   @FXML
   private TextArea textDescricao;

    @FXML
    private Button botaoEquipe;

    @FXML
    private Button botaoExplorar;
    
    @FXML
    private Button botaoHome;
    
    @FXML 
    private ChoiceBox<String> choiceBoxAcesso;
    
    @FXML
    private TextField textNomeCubesat;
    
    private String[] acesso = {"Público", "Privado"};
    
    @FXML
    private ImageView iconeEquipes;
    
    @FXML
    private ImageView iconeExplorar;
    
    @FXML
    private ImageView iconeSair;
    
    @FXML
    private Button botaoImagemCubesat;
    
    @FXML
    private ImageView perfilCubesat;
    
    private final FileChooser fileChooser = new FileChooser();
    
    private final Desktop desktop =  Desktop.getDesktop();
    
    private File arquivo;
    
    @FXML 
    private Button botaoSalvarCubesat;
    
    @FXML 
    private Button botaoExcluirCubesat;
    
    @FXML
    void salvarToPourple(MouseEvent event){
        botaoExplorar.setStyle("-fx-text-fill: #8C52FF;");
    }
    
    @FXML
    void salvarToWhite(MouseEvent event){
        botaoExplorar.setStyle("-fx-text-fill: white;");
    }
    
    @FXML
    void excluirToPourple(MouseEvent event){
        botaoExcluirCubesat.setStyle("-fx-text-fill: #8C52FF;");
    }
    
    @FXML
    void excluirToWhite(MouseEvent event){
        botaoExcluirCubesat.setStyle("-fx-text-fill: white;");
    }
    
    @FXML
    void sairToPourple(MouseEvent event){
        iconeSair.setImage(new Image("file:src/main/resources/images/iconeSairLilas.png"));
    }
    
    @FXML
    void sairToWhite(MouseEvent event){
        iconeSair.setImage(new Image("file:src/main/resources/images/iconeSair.png"));
    }
    
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
    void adicionarImagem(ActionEvent event) throws PersistenciaException{
        try{
            configurarFileChooser(fileChooser);
            arquivo = fileChooser.showOpenDialog(new Stage());
            if(arquivo !=  null){
                perfilCubesat.setImage(new Image("file:"+arquivo.getPath()));
                perfilCubesat.setStyle("-fx-border-radius: 50%;");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void configurarFileChooser(final FileChooser fileChooser){
        FileChooser f = new FileChooser();
        f.getExtensionFilters().addAll(
                     new FileChooser.ExtensionFilter("Todas as imagens", "*.*"),
                     new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                     new FileChooser.ExtensionFilter("PNG", "*.png"),
                     new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
             );
    }
    
    @FXML
    void apresentaTelaCubesat(ActionEvent event) {
        MainFX.changedScreen("Cubesat", usuario);
    }

    @FXML
    void apresentaTelaEquipe(ActionEvent event) {
        MainFX.changedScreen("Equipes", usuario);
    }

    @FXML
    void apresentaTelaExplorar(ActionEvent event) {
        MainFX.changedScreen("Explorar", usuario);
    }
    
    
    @FXML
    void apresentarTelaInicial(ActionEvent event) {
        MainFX.changedScreen("Tela Inicial", usuario);
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       choiceBoxAcesso.getItems().addAll(acesso);
       
       MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen(){
           @Override
           public void onScreenChanged(String newString, Object viewData){
               usuario = (UsuarioDTO)viewData;
           }
       });
       
    }
    
    
}
