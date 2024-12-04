package controllers;

import br.cefetmg.space.dao.UsuarioDAO;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.IUsuarioDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import static controllers.TelaEditarPerfilController.configurarFileChooser;
import java.awt.Desktop;
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
import java.util.ResourceBundle;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView imagemPerfil;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelPerfil;

    @FXML
    private Label labelSenha;

    @FXML
    private Label labelTelefone;
    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoNome;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private TextField campoTelefone;

    private Stage dialogStage;

    private final boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private Usuario usuarioAtual;

    @FXML
    private ImageView perfilUsuario;

    private final FileChooser fileChooser = new FileChooser();

    private final Desktop desktop = Desktop.getDesktop();

    private File arquivo;

    public void campoFields() {
        if (usuarioAtual != null) {
            campoNome.setText(usuarioAtual.getNome() != null ? usuarioAtual.getNome() : "");
            campoEmail.setText(usuarioAtual.getEmail() != null ? usuarioAtual.getEmail() : "");
            campoSenha.setText(usuarioAtual.getSenha() != null ? usuarioAtual.getSenha() : "");
            campoTelefone.setText(usuarioAtual.getTelefone() != null ? usuarioAtual.getTelefone() : "");
            botaoSalvarPerfil.setVisible(false);
        } else {
            System.err.println("Usuário atual está nulo!");
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
                || !campoSenha.getText().equals(usuarioAtual.getSenha())
                || !campoTelefone.getText().equals(usuarioAtual.getTelefone());

        botaoSalvarPerfil.setVisible(alterado);
    }

    public boolean isOkClicked() {
        return this.okClicked;
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
                    
                    
                    
                } catch (Exception e) {
                    Alert erro = new Alert(Alert.AlertType.ERROR);
                    erro.setHeaderText("Erro ao excluir o perfil: " + e.getMessage());
                    erro.show();
                }
            }
        });
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
            usuarioAlterado.setSenha(campoSenha.getText());
            usuarioAlterado.setTelefone(campoTelefone.getText());

            int IdUsuario = usuarioAtual.getId();
            if (usuarioDAO.atualizar(IdUsuario, usuarioAlterado)) {
                confirmacao.setHeaderText("Perfil atualizado com sucesso!");
                confirmacao.show();
                MainFX.changedScreen("Perfil", usuarioAlterado);
            } else {
                erro.setHeaderText("Houve um erro ao atualizar o perfil.");
                erro.show();
            }
        }
    }

    @FXML
    void adicionarImagem(ActionEvent event) throws PersistenciaException {
        try {
            configurarFileChooser(fileChooser);
            arquivo = fileChooser.showOpenDialog(new Stage());
            if (arquivo != null) {
                perfilUsuario.setImage(new Image("file:" + arquivo.getPath()));
                perfilUsuario.setStyle("-fx-border-radius: 50%;");
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
    void apresentaTelaHome(ActionEvent event) throws IOException {
        MainFX.changedScreen("Tela Inicial", usuarioAtual);
    }
   

   

@Override
public void initialize(URL url, ResourceBundle rb) {
    MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
        if (viewData instanceof Usuario) {
            usuarioAtual = (Usuario) viewData;
            System.out.println("Dados recebidos: " + usuarioAtual.getNome());
            if (usuarioAtual != null) {
                campoFields();
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
