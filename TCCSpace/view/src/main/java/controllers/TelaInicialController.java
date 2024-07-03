package controllers;

import br.cefetmg.space.model.dto.CubeSatDTO;
import br.cefetmg.space.model.dto.EquipeDTO;
import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.view.MainFX;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

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
    private Label Nome;

    @FXML
    private ImageView iconeEquipes;

    @FXML
    private ImageView iconeCubesat;

    @FXML
    private ImageView iconeExplorar;

    @FXML
    private VBox visualizarCubes;

    @FXML
    private VBox visualizarEquipe;

    private UsuarioDTO usuario;

    private List<CubeSatDTO> cubeSat;
    
    private List<EquipeDTO> equipe;

    @FXML
    void explorarToPourple(MouseEvent event) {
        botaoExplorar.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeExplorar.setImage(new Image("file:src/main/resources/images/iconeExplorarLilas.png"));
    }

    @FXML
    void explorarToWhite(MouseEvent event) {
        botaoExplorar.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeExplorar.setImage(new Image("file:src/main/resources/images/iconeExplorar.png"));
    }

    @FXML
    void cubesatToPourple(MouseEvent event) {
        botaoCubesat.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeCubesat.setImage(new Image("file:src/main/resources/images/iconeCubesatLilas.png"));
    }

    @FXML
    void cubesatToWhite(MouseEvent event) {
        botaoCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeCubesat.setImage(new Image("file:src/main/resources/images/iconeCubesat.png"));
    }

    @FXML
    void equipesToPourple(MouseEvent event) {
        botaoEquipe.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeEquipes.setImage(new Image("file:src/main/resources/images/iconeEquipesLilas.png"));
    }

    @FXML
    void equipesToWhite(MouseEvent event) {
        botaoEquipe.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeEquipes.setImage(new Image("file:src/main/resources/images/iconeEquipes.png"));
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
    
    @FXML
    void apresentaTelaCubesat(ActionEvent event) {
        MainFX.changedScreen("Cubesat", usuario);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newString, Object viewData) {
                usuario = (UsuarioDTO) viewData;
                Nome.setText(usuario.getNome());

                cubeSat = usuario.getCubeSat();
                visualizarCubes.setSpacing(10);

                for (int x = 0; x < usuario.quantCubeSat(); x++) {
                    Button botaoCube = new Button(cubeSat.get(x).getNome());
                    botaoCube.setId(cubeSat.get(x).getNome());
                    botaoCube.setAlignment(Pos.CENTER);
                    botaoCube.setStyle("-fx-background-color: transparent; -fx-border-color: #8c52ff; -fx-border-radius: 2px;"
                            + "-fx-text-fill: white;" + "-fx-font-size: 20px");
                    //Image imagem = new Image("teste.pjpg");
                    //ImageView imageView = new ImageView(imagem);
                    // imageView.setFitWidth(30); 
                    //imageView.setFitHeight(30);
                    // botaoCube.setGraphic(imageView);
                    visualizarCubes.getChildren().add(botaoCube);
                }
                equipe = usuario.getEquipes();
                for(int y=0; y<usuario.quantEquipes(); y++){
                    Button botaoEquipe = new Button(equipe.get(y).getNome());
                    botaoEquipe.setAlignment(Pos.CENTER);
                    botaoEquipe.setStyle("-fx-background-color: transparent; -fx-border-color: #8c52ff; -fx-border-radius: 2px;"
                            + "-fx-text-fill: white;" + "-fx-font-size: 20px");
                    visualizarEquipe.getChildren().add(botaoEquipe);
                }
            }
        });
    }

}
