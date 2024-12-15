package controllers;

import br.cefetmg.space.controller.UsuarioController;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VerificarSenhaController implements Initializable {

    @FXML
    private Button BotaoVerificar;

    @FXML
    private Button BotaoSair;

    @FXML
    private PasswordField CampoSenha;

    @FXML
    private ImageView iconeSair;

    @FXML
    private Label labelInsira;

    private Usuario usuario;

    private UsuarioController usuarioController = new UsuarioController();

    @FXML
    public void verificarSenha(ActionEvent e) throws PersistenciaException, IOException {
        try {
            String senha = CampoSenha.getText().trim();

            if (senha.isEmpty()) {
                labelInsira.setText("Por favor, insira uma senha.");
                return;
            }

            if (usuario != null && BCrypt.checkpw(senha, usuario.getSenha())) {
                MainFX.changedScreen("Perfil", usuario);
                labelInsira.setText("Acesso concedido.");
            } else {
                throw new Exception("Senha incorreta ou usuário inválido.");
            }
        } catch (Exception ex) {
            // Exibe um alerta de erro para o usuário
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setHeaderText("Erro ao verificar a senha.");
            erro.setContentText(ex.getMessage());
            erro.show();

            ex.printStackTrace();
        }
    }

    @FXML
    public void apresentarTelaInicial(ActionEvent event) throws IOException {
        MainFX.changedScreen("Tela Inicial", usuario);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
