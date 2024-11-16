package controllers;

import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.dao.UsuarioDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.io.IOException;
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

    public void loginButaoErro(ActionEvent e) throws PersistenciaException, IOException {
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

    public void cadastroButao(ActionEvent e) throws IOException {
        MainFX.changedScreen("Cadastro", null);
    }

    public boolean test(String email, String senha) throws PersistenciaException {
      
        UsuarioDTO nv = new UsuarioDTO();
        nv.setEmail(email);
        nv.setSenha(senha);
        nv.setTelefone("tel");

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.validarlogin(nv)) {
            usuario = usuarioDAO.procurarPorEmail(email);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
        });
    }

}
