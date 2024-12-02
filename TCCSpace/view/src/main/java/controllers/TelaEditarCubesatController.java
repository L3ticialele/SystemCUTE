package controllers;

import br.cefetmg.space.dao.CubeSatDAO;
import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.ICubeSatDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TelaEditarCubesatController implements Initializable {

    private BufferedImage imagem;

    private CubeSat cubesat;

    @FXML
    private Button botaoCubesat;

    @FXML
    private TextArea textDescricao;

    @FXML
    private Button botaoSuporte;

    @FXML
    private Button botaoPerfil;

    @FXML
    private Button botaoHome;
    
    private Usuario usuario;
    
    @FXML
    private ChoiceBox<String> choiceBoxAcesso;

    @FXML
    private Label labelDataCadastro;

    @FXML
    private TextField textNomeCubesat;

    private final String[] acesso = {"Público", "Privado"};

    @FXML
    private ImageView iconeSuporte;

    @FXML
    private ImageView iconePerfil;

    @FXML
    private ImageView iconeSair;

    @FXML
    private Button botaoImagemCubesat;

    @FXML
    private Label labelIdCubesat;

    @FXML
    private ImageView perfilCubesat;

    private final FileChooser fileChooser = new FileChooser();

    private final Desktop desktop = Desktop.getDesktop();

    private File arquivo;

    @FXML
    private Button botaoSalvarCubesat;

    @FXML
    private Button botaoExcluirCubesat;

    private Stage dialogStage;
    
    private final boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void textsFields() {
        textNomeCubesat.setText(cubesat.getNome());
        textDescricao.setText(cubesat.getDescricao());
        choiceBoxAcesso.getItems().addAll(acesso);
        choiceBoxAcesso.setValue(cubesat.getAcesso());
        labelDataCadastro.setText(cubesat.getDataCadastro());
        labelIdCubesat.setText("ID: " + cubesat.getId());
    }

    public boolean isOkClicked() {
        return this.okClicked;
    }

    @FXML
    void excluirCubesat(ActionEvent event) throws PersistenciaException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Tem certeza que deseja excluir o cubesat ?"); //+ cubesat.getNome() + "?");
        alert.show();
    }

    @FXML
    void salvarAlteracoesCubesat(ActionEvent event) throws PersistenciaException, IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        Alert erro = new Alert(Alert.AlertType.ERROR);
        CubeSat cube = new CubeSat();
        ICubeSatDAO cubeDAO = new CubeSatDAO();

        if (textNomeCubesat.getText() == null || textNomeCubesat.getText().isEmpty()) {
            alert.setHeaderText("Por favor, informe o nome do CubeSat.");
            alert.show();
        } else {
            LocalDateTime dataCadastro = LocalDateTime.now();
            cube.setDataCadastro(dataCadastro.toString());
            cube.setNome(textNomeCubesat.getText());
            cube.setAcesso(choiceBoxAcesso.getValue());
            cube.setPessoa(cubesat.getUsuario());
            cube.setDescricao(textDescricao.getText());

            if (cubeDAO.atualizar(cube)) {
                confirmacao.setHeaderText("Cubesat atualizado!");
                confirmacao.show();
            } else {
                erro.setHeaderText("Houve um erro ao atualizar o cubesat.");
                erro.show();
            }

            MainFX.changedScreen("Cubesat", cubesat.getUsuario());
        }
    }

    @FXML
    void salvarToPourple(MouseEvent event) {
        botaoSalvarCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color:  #8C52FF;");
    }

    @FXML
    void salvarToWhite(MouseEvent event) {
        botaoSalvarCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color:  #73668B;");
    }

    @FXML
    void excluirToPourple(MouseEvent event) {
        botaoExcluirCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color:  #8C52FF;");
    }

    @FXML
    void excluirToWhite(MouseEvent event) {
        botaoExcluirCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color:  #73668B;");
    }

    @FXML
    void sairToPourple(MouseEvent event) {
        iconeSair.setImage(new Image("file:src/main/resources/images/iconeSairLilas.png"));
    }

    @FXML
    void sairToWhite(MouseEvent event) {
        iconeSair.setImage(new Image("file:src/main/resources/images/iconeSair.png"));
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

    @FXML
    void adicionarImagem(ActionEvent event) throws PersistenciaException {
        try {
            configurarFileChooser(fileChooser);
            arquivo = fileChooser.showOpenDialog(new Stage());
            if (arquivo != null) {
                perfilCubesat.setImage(new Image("file:" + arquivo.getPath()));
                perfilCubesat.setStyle("-fx-border-radius: 50%;");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void configurarFileChooser(final FileChooser fileChooser) {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todas as imagens", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );
    }

    @FXML
    void apresentaTelaSuporte(ActionEvent event) throws IOException {
        MainFX.changedScreen("Suporte", cubesat.getUsuario());
    }

    @FXML
    void apresentaTelaPerfil(ActionEvent event) throws IOException {
        MainFX.changedScreen("Perfil", cubesat.getUsuario());
    }

    @FXML
    void apresentarTelaInicial(ActionEvent event) throws IOException {
        MainFX.changedScreen("Tela Inicial", cubesat.getUsuario());
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
            CubeSat cubeSat = (CubeSat) viewData;
            Usuario user = (Usuario) viewData;
            if(viewData instanceof CubeSat) {
                cubesat = cubeSat;
                textsFields();
                usuario = cubesat.getUsuario();
            }
            else if(viewData instanceof Usuario){
                usuario = user;
            }
        });

    }

}
