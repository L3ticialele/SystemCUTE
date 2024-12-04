package controllers;

import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.view.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaInicialController implements Initializable {

    @FXML
    private Button botaoCubesat;

    @FXML
    private Button botaoSuporte;

    @FXML
    private Button botaoPerfil;

    @FXML
    private Button botaoHome;

    @FXML
    private Label nome;

    @FXML
    private ImageView iconeSuporte;

    @FXML
    private ImageView iconeCubesat;

    @FXML
    private ImageView iconePerfil;

    @FXML
    private HBox visualizarCubes;

    private Usuario usuario;
  
    private List<CubeSat> cubeSat;
    
    private CubeSat cube;

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
    
    @FXML
    void apresentarTelaCadastrarCubesat(ActionEvent event) throws IOException {
        MainFX.changedScreen("Cadastrar Cubesat", usuario);
    }
    
    void apresentarTelaDados(ActionEvent event) throws IOException{
        MainFX.changedScreen("Gui3d", cube);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
            if(viewData instanceof Usuario) {
                System.out.println("a");
                List<CubeSat> cubeSat;
                usuario = (Usuario) viewData;
                nome.setText(usuario.getNome() + "!");
                cubeSat = usuario.getCubeSat();
                visualizarCubes.setSpacing(10);
                visualizarCubes.getChildren().clear();
                for (int x = 0; x < usuario.quantCubeSat(); x++) {
                    Button botaoCube = new Button(cubeSat.get(x).getNome());
                    botaoCube.setId(cubeSat.get(x).getNome());
                    botaoCube.setAlignment(Pos.CENTER);
                    botaoCube.setStyle("-fx-background-color: transparent; -fx-border-color: #8c52ff; -fx-border-radius: 2px;"
                            + "-fx-text-fill: white;" + "-fx-font-size: 20px");
                    /*
                    Image imagem = new Image("teste.jpg");
                    ImageView imageView = new ImageView(imagem);
                    imageView.setFitWidth(30); 
                    imageView.setFitHeight(30);
                    botaoCube.setGraphic(imageView);
                    */
                   botaoCube.setOnAction(event -> {
                        try {
                            apresentarTelaDados(event);
                        } catch (IOException ex) {
                            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            });
                    visualizarCubes.getChildren().add(botaoCube);
                    
                }
            }
        });
    }
}
