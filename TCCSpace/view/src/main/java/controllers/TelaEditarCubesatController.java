package controllers;

import br.cefetmg.space.controller.ManipularImagem;
import br.cefetmg.space.dao.CubeSatDAO;
import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Imagem;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.ICubeSatDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.poi.ss.util.ImageUtils;

public class TelaEditarCubesatController implements Initializable {

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
    private Label labelDataCadastro;

    @FXML
    private TextField textNomeCubesat;

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

    private final ManipularImagem manipuladorImagem = new ManipularImagem();

    private Imagem imagem;

    public void campoFields() throws PersistenciaException {
        if (cubesat != null) {
            textNomeCubesat.setText(cubesat.getNome() != null ? cubesat.getNome() : "");
            labelDataCadastro.setText(cubesat.getDataCadastro() != null ? cubesat.getDataCadastro() : "");
            labelIdCubesat.setText("ID: " + Integer.toString(cubesat.getId()));
            textDescricao.setText(cubesat.getDescricao());
            if (imagem() != null) {
                perfilCubesat.setImage(imagem());
            }
            botaoSalvarCubesat.setVisible(false);
        } else {
            System.err.println("CubeSat atual está nulo!");
        }
    }

    public Image imagem() throws PersistenciaException {
        // Recupera o objeto Imagem do banco pelo ID
        if (cubesat.getImagem() != null) {
            Imagem imagemCube = manipuladorImagem.recuperarImagem(cubesat.getImagem().getId());
            if (imagemCube != null) {
                // Converte o array de bytes para um InputStream
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imagemCube.getDados());

                // Cria o objeto Image (JavaFX)
                return new Image(inputStream);
            } else {
                System.out.println("Imagem não encontrada.");
                return null;
            }
        }
        return null;
    }

    private void inicializarListeners() {
        textNomeCubesat.textProperty().addListener((observable, oldValue, newValue) -> {
            verificarAlteracoes();
        });

        textDescricao.textProperty().addListener((observable, oldValue, newValue) -> {
            verificarAlteracoes();
        });

        labelDataCadastro.textProperty().addListener((observable, oldValue, newValue) -> {
            verificarAlteracoes();
        });

        labelIdCubesat.textProperty().addListener((observable, oldValue, newValue) -> {
            verificarAlteracoes();
        });

        perfilCubesat.accessibleTextProperty().addListener((observable, oldValue, newValue) -> {
            verificarAlteracoes();
        });

    }

    private void verificarAlteracoes() {
        boolean alterado
                = !textNomeCubesat.getText().equals(cubesat.getNome())
                || !textDescricao.getText().equals(cubesat.getDescricao())
                || !verificarAlteracoesImagem();
        botaoSalvarCubesat.setVisible(alterado);
    }

    public boolean isOkClicked() {
        return this.okClicked;
    }

    public boolean verificarAlteracoesImagem() {
        if (cubesat.getImagem() != null) {
            return Arrays.equals(imagem.getDados(), cubesat.getImagem().getDados());
        } else{
            return perfilCubesat.getImage() == null;
        }
    }

    @FXML
    void excluirCubesat(ActionEvent event) throws PersistenciaException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Tem certeza que deseja excluir o CubeSat?");
        alert.setContentText("Essa ação não poderá ser desfeita.");

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                ICubeSatDAO cubesatDAO = new CubeSatDAO();
                try {
                    cubesatDAO.delete(cubesat.getId());
                    Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmacao.setHeaderText("CubeSat excluído com sucesso!");
                    confirmacao.show();
                    cubesat = null;
                    apresentarTelaInicial(event);

                } catch (Exception e) {
                    Alert erro = new Alert(Alert.AlertType.ERROR);
                    erro.setHeaderText("Erro ao excluir o CubeSat: " + e.getMessage());
                    erro.show();
                }
            }
        });
    }

    @FXML
    void apresentarTelaLogin(ActionEvent event) throws IOException {
        MainFX.changedScreen("Login", null);
    }

    public static byte[] imageToBytes(Image image) throws IOException {
        // Converte a Image (JavaFX) para BufferedImage
        BufferedImage bufferedImage = javafxToBufferedImage(image);

        // Usa ImageIO para converter o BufferedImage para array de bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    }

    // Converte Image (JavaFX) para BufferedImage
    private static BufferedImage javafxToBufferedImage(Image image) {
        PixelReader pixelReader = image.getPixelReader();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, pixelReader.getColor(x, y).hashCode());
            }
        }
        return bufferedImage;
    }

    @FXML
    void salvarAlteracoesCubesat(ActionEvent event) throws PersistenciaException, IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        Alert erro = new Alert(Alert.AlertType.ERROR);
        CubeSat cubesatAlterado = new CubeSat();
        ICubeSatDAO cubesatDAO = new CubeSatDAO();

        if (textNomeCubesat.getText() == null || textNomeCubesat.getText().isEmpty()) {
            alert.setHeaderText("Por favor, informe o nome do CubeSat.");
            alert.show();
        } else {
            cubesatAlterado.setId(cubesat.getId());
            cubesatAlterado.setNome(textNomeCubesat.getText());
            cubesatAlterado.setDescricao(textDescricao.getText());
            cubesatAlterado.setDataCadastro(labelDataCadastro.getText());
            cubesatAlterado.setPessoa(cubesat.getUsuario());
            cubesatAlterado.setTodosDados(cubesat.getDados());
            if (perfilCubesat.getImage() != null) {
                if (verificarAlteracoesImagem()) {
                    manipuladorImagem.atualizarImagem(imagem);
                }
                else{
                    cubesatAlterado.setImagem(imagem);
                    System.out.println(cubesatAlterado.getImagem().getNome() + " ok");
                }
            }
            if (cubesatDAO.atualizar(cubesatAlterado)) {
                confirmacao.setHeaderText("CubeSat atualizado com sucesso!");
                confirmacao.show();
                apresentarTelaInicial(event);
            } else {
                erro.setHeaderText("Houve um erro ao atualizar o CubeSat.");
                erro.show();
            }
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

    public static Image transformarImagemParaJavaFX(Imagem imagem) {
        // Converte o array de bytes em um InputStream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imagem.getDados());

        // Cria o objeto Image do JavaFX
        return new Image(inputStream);
    }

    @FXML
    void adicionarImagem(ActionEvent event) throws PersistenciaException {
        imagem = manipuladorImagem.selecionarImagem();
        if (imagem != null) {
            perfilCubesat.setImage(transformarImagemParaJavaFX(imagem));
            verificarAlteracoes();
            if(cubesat.getImagem() != null)
                imagem.setId(cubesat.getImagem().getId());
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

        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
            if (viewData instanceof CubeSat) {
                cubesat = (CubeSat) viewData;
                usuario = cubesat.getUsuario();
                if (cubesat != null) {
                    try {
                        campoFields();
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(TelaEditarCubesatController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    inicializarListeners();
                } else {
                    System.err.println("Erro: CubeSatDTO está nulo.");
                }
            } else {
                System.err.println("Erro: Objeto viewData não é do tipo CubeSatDTO.");
            }
        });

    }

}
