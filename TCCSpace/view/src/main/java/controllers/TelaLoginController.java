package controllers;

import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.idao.IUsuarioDAO;
import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.dao.UsuarioDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLoginController implements Initializable {

    @FXML
    private Button BotaoCadastro;

    @FXML
    private Button BotaoLogin;

    @FXML
    private Button BotaoSenha;

    @FXML
    private TextField CampoEmail;

    @FXML
    private PasswordField CampoSenha;
    
    private UsuarioDTO usuario;

    @FXML
    private Label msgErro;

    public void loginButaoErro(ActionEvent e) throws PersistenciaException {
        String email = CampoEmail.getText();
        String senha = CampoSenha.getText();
        if (CampoEmail.getText().isBlank() == true || CampoSenha.getText().isBlank() == true) {
            msgErro.setText("Preencha os campos vazios");
        } else if (test(email, senha)) {
            MainFX.changedScreen("Tela Inicial", usuario);
        } else {
            msgErro.setText("E-mail ou senha incorreto!");
        }
    }

    public void cadastroButao(ActionEvent e) {
        MainFX.changedScreen("Cadastro", null);
    }

    public boolean test(String email, String senha) throws PersistenciaException {
        usuario = new UsuarioDTO();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(senha);
        usuario.setTelefone(senha);

        UsuarioDAO user = new UsuarioDAO();

        return user.validarlogin(usuario);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}