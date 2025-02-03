package controllers;

import br.cefetmg.space.dao.UsuarioDAO;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.view.EmailSender;
import br.cefetmg.space.view.GeradorSenha;
import br.cefetmg.space.view.MainFX;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

public class EsqueceuSenhaController implements Initializable {

    @FXML
    private Button BotaoEnviar;

    @FXML
    private TextField CampoRecuperarEmail;

    @FXML
    private HBox fundo;

    @FXML
    private ImageView iconeSair;

    @FXML
    private Label labelInsira;
    
     private Usuario usuario;


    @FXML
    public void mandarEmail(ActionEvent event) {
        String email = CampoRecuperarEmail.getText().trim();

        if (email.isEmpty()) {
            labelInsira.setText("Por favor, insira um e-mail.");
            return;
        }

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();

             usuario = usuarioDAO.procurarPorEmail(email);
            if (usuario == null) {
                labelInsira.setText("E-mail não encontrado.");
                return;
            }

            String novaSenha = GeradorSenha.gerarSenha(8);

            usuario.setSenha(BCrypt.hashpw(novaSenha, BCrypt.gensalt()));
            if (usuarioDAO.atualizar(usuario.getId(), usuario)) {
                EmailSender.enviarEmail(email, "Recuperação de Senha", "Sua nova senha é: " + novaSenha);

                labelInsira.setText("Nova senha enviada para o e-mail.");
            } else {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setHeaderText("Houve um erro ao atualizar a senha.");
                erro.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            labelInsira.setText("Ocorreu um erro.");
        }
        
       
    }
    
     @FXML
   public  void apresentarTelaInicial(ActionEvent event) throws IOException {
        MainFX.changedScreen("Login", usuario);
    }
    
     


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
