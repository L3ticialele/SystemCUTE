package controllers;

import br.cefetmg.space.controller.ManipularImagem;
import br.cefetmg.space.controller.UsuarioController;
import br.cefetmg.space.controller.ValidaCamposController;
import br.cefetmg.space.dao.UsuarioDAO;
import br.cefetmg.space.entidades.Imagem;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.IUsuarioDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class TelaEditarPerfilController implements Initializable {

    @FXML
    private Button botaoCubesat;

    @FXML
    private Button botaoEscolherFoto;

    @FXML
    private Button botaoExcluirPerfil;

    @FXML
    private Button botaoImagemPerfil;

    @FXML
    private Button botaoPerfil;

    @FXML
    private Label labelPerfil;

    @FXML
    private Button botaoSair;

    @FXML
    private Button botaoSalvarPerfil;

    @FXML
    private Button botaoSuporte;

    @FXML
    private ImageView iconePerfil;

    @FXML
    private ImageView iconeSair;

    @FXML
    private ImageView iconeSuporte;

    @FXML
    private ImageView iconeCubesat;
    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoNome;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private PasswordField campoSenha1;

    @FXML
    private TextField campoTelefone;

    private Stage dialogStage;

    private ValidaCamposController validador = new ValidaCamposController();

    private final boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private Usuario usuarioAtual;

    private final ManipularImagem manipuladorImagem = new ManipularImagem();

    @FXML
    private ImageView perfilUsuario;

    private final FileChooser fileChooser = new FileChooser();

    private final Desktop desktop = Desktop.getDesktop();

    private File arquivo;

    private Imagem imagem;

    private final UsuarioController usuarioController = new UsuarioController();

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

    public void campoFields() throws PersistenciaException {
        if (usuarioAtual != null) {
            campoNome.setText(usuarioAtual.getNome() != null ? usuarioAtual.getNome() : "");
            campoEmail.setText(usuarioAtual.getEmail() != null ? usuarioAtual.getEmail() : "");
            campoTelefone.setText(usuarioAtual.getTelefone() != null ? usuarioAtual.getTelefone() : "");
            labelPerfil.setText("ID: " + usuarioAtual.getId());
            botaoSalvarPerfil.setVisible(false);
        } else {
            System.err.println("Usuário atual está nulo!");
        }
    }

    public Image imagem() throws PersistenciaException {
        // Recupera o objeto Imagem do banco pelo ID
        Imagem imagemUsuario = manipuladorImagem.recuperarImagem(usuarioAtual.getImagem().getId());

        if (imagemUsuario != null) {
            // Converte o array de bytes para um InputStream
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imagemUsuario.getDados());

            // Cria o objeto Image (JavaFX)
            return new Image(inputStream);
        } else {
            System.out.println("Imagem não encontrada.");
            return null;
        }
    }

    private void inicializarListeners() {
        campoNome.textProperty().addListener((observable, oldValue, newValue) -> {
            verificarAlteracoes();
        });

        campoEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            verificarAlteracoes();
        });

        campoSenha.textProperty().addListener((observable, oldValue, newValue) -> {
            verificarAlteracoes();
        });

        campoTelefone.textProperty().addListener((observable, oldValue, newValue) -> {
            verificarAlteracoes();
        });
    }

    private void verificarAlteracoes() {
        boolean alterado
                = !campoNome.getText().equals(usuarioAtual.getNome())
                || !campoEmail.getText().equals(usuarioAtual.getEmail())
                || !campoSenha1.getText().equals(usuarioAtual.getSenha())
                || !campoTelefone.getText().equals(usuarioAtual.getTelefone())
                || !verificarAlteracoesImagem();

        botaoSalvarPerfil.setVisible(alterado);
    }

    public boolean verificarAlteracoesImagem() {
        if (usuarioAtual.getImagem() != null) {
            return Arrays.equals(imagem.getDados(), usuarioAtual.getImagem().getDados());
        } else {
            return perfilUsuario.getImage() != null;
        }
    }

    public boolean isOkClicked() {
        return this.okClicked;
    }

    @FXML
    void adicionarImagem(ActionEvent event) throws PersistenciaException {
        imagem = manipuladorImagem.selecionarImagem();
        if (imagem != null) {
            perfilUsuario.setImage(transformarImagemParaJavaFX(imagem));
            if (usuarioAtual.getImagem() != null) {
                imagem.setId(usuarioAtual.getImagem().getId());
                verificarAlteracoes();
            } else {
                botaoSalvarPerfil.setVisible(true);
            }
        }
    }

    public static Image transformarImagemParaJavaFX(Imagem imagem) {
        // Converte o array de bytes em um InputStream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imagem.getDados());

        // Cria o objeto Image do JavaFX
        return new Image(inputStream);
    }

    @FXML
    void excluirPerfil(ActionEvent event) throws PersistenciaException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Tem certeza que deseja excluir o perfil?");
        alert.setContentText("Essa ação não poderá ser desfeita.");

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                IUsuarioDAO usuarioDAO = new UsuarioDAO();
                try {

                    usuarioDAO.delete(usuarioAtual.getId());
                    Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmacao.setHeaderText("Perfil excluído com sucesso!");
                    confirmacao.show();
                    usuarioAtual = null;
                    apresentarTelaLogin(event);
                } catch (Exception e) {
                    Alert erro = new Alert(Alert.AlertType.ERROR);
                    erro.setHeaderText("Erro ao excluir o perfil: " + e.getMessage());
                    erro.show();
                }
            }
        });
    }

    void salvar(Usuario usuarioAlterado) throws PersistenciaException, IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        Alert erro = new Alert(Alert.AlertType.ERROR);
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioAlterado.setTelefone(campoTelefone.getText());
        int IdUsuario = usuarioAtual.getId();
        if (imagem != null && verificarAlteracoesImagem()) {
            usuarioAlterado.setImagem(imagem);
        }
        if (usuarioDAO.atualizar(IdUsuario, usuarioAlterado)) {
            if (usuarioAtual.getImagem() != null && imagem != null) {
                manipuladorImagem.atualizarImagem(imagem);
            } else {
                usuarioAlterado.setImagem(usuarioAtual.getImagem());
            }
            confirmacao.setHeaderText("Perfil atualizado com sucesso!");
            confirmacao.show();
            MainFX.changedScreen("Perfil", usuarioAlterado);
        } else {
            erro.setHeaderText("Houve um erro ao atualizar o perfil.");
            erro.show();
        }
    }

    @FXML
    void salvarAlteracoesPerfil(ActionEvent event) throws PersistenciaException, IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        Alert erro = new Alert(Alert.AlertType.ERROR);
        Usuario usuarioAlterado = new Usuario();
        IUsuarioDAO usuarioDAO = new UsuarioDAO();

        if (campoNome.getText() == null || campoNome.getText().isEmpty()) {
            alert.setHeaderText("Por favor, informe o nome.");
            alert.show();
        } else if (campoEmail.getText() == null || campoEmail.getText().isEmpty()) {
            alert.setHeaderText("Por favor, informe o email.");
            alert.show();
        } else if (campoSenha.getText() == null || campoSenha.getText().isEmpty()) {
            alert.setHeaderText("Por favor, informe a senha.");
            alert.show();
        } else if (campoTelefone.getText() == null || campoTelefone.getText().isEmpty()) {
            alert.setHeaderText("Por favor, informe o telefone.");
            alert.show();
        } else {
            usuarioAlterado.setId(usuarioAtual.getId());
            usuarioAlterado.setNome(campoNome.getText());
            usuarioAlterado.setEmail(campoEmail.getText());
            if (usuarioController.login(usuarioAtual.getEmail(), campoSenha.getText()) != null) {
                if (campoTelefone.getText() == null || campoTelefone.getText().isEmpty()) {
                    usuarioAlterado.setSenha(usuarioController.senhaHash(campoSenha.getText()));
                    salvar(usuarioAlterado);
                } else {
                    if (validador.senhaForte(campoSenha1.getText())) {
                        usuarioAlterado.setSenha(usuarioController.senhaHash(campoSenha1.getText()));
                        salvar(usuarioAlterado);
                    } else {
                        alert.setHeaderText("Senha fraca!");
                        alert.show();
                    }
                }
            } else {
                alert.setHeaderText("Senha atual incorreta!");
                alert.show();
            }
        }
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
    void apresentaTelaSuporte(ActionEvent event) throws IOException {
        MainFX.changedScreen("Suporte", usuarioAtual);
    }

    @FXML
    void apresentarTelaLogin(ActionEvent event) throws IOException {
        MainFX.changedScreen("Login", usuarioAtual);
    }

    @FXML
    void apresentaTelaHome(ActionEvent event) throws IOException {
        MainFX.changedScreen("Tela Inicial", usuarioAtual);
    }

    @FXML
    void apresentaTelaPerfil(ActionEvent event) throws IOException {
        MainFX.changedScreen("Perfil", usuarioAtual);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
            if (viewData instanceof Usuario) {
                usuarioAtual = (Usuario) viewData;
                System.out.println("Dados recebidos: " + usuarioAtual.getNome());
                try {
                    if (usuarioAtual.getImagem() != null) {
                        perfilUsuario.setImage(imagem());
                    }
                } catch (PersistenciaException ex) {
                    Logger.getLogger(TelaEditarPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (usuarioAtual != null) {
                    try {
                        campoFields();
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(TelaEditarPerfilController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    inicializarListeners();
                } else {
                    System.err.println("Erro: UsuarioDTO está nulo.");
                }
            } else {
                System.err.println("Erro: Objeto viewData não é do tipo UsuarioDTO.");
            }
        });

        botaoSalvarPerfil.setVisible(false);
    }

}
