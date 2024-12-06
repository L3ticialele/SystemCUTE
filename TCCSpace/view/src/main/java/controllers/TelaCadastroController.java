package controllers;

import br.cefetmg.space.controller.UsuarioController;
import br.cefetmg.space.dao.UsuarioDAO;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.IUsuarioDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TelaCadastroController implements Initializable {

    @FXML
    private Button BotaoCadastrar;

    @FXML
    private TextField CampoEmail;

    @FXML
    private TextField CampoNome;

    @FXML
    private TextField CampoSenha;

    @FXML
    private TextField CampoTelefone;

    @FXML
    private Label msg;
    
    private final UsuarioController usuarioController = new UsuarioController();

    public void voltarPaginaLogin(ActionEvent e) throws IOException {
        MainFX.changedScreen("Login", null);
    }

    public void BotaoCadastrar(ActionEvent e) throws IOException, PersistenciaException {
        if (CampoEmail.getText().isBlank() == true || CampoSenha.getText().isBlank() == true || CampoNome.getText().isBlank() == true || CampoTelefone.getText().isBlank() == true) {
            msg.setText("Preencha os campos vazios");
        } else {
            String email = CampoEmail.getText();
            String nome = CampoNome.getText();
            String senha = CampoSenha.getText();
            String telefone = CampoTelefone.getText();
            MainFX.changedScreen("Tela Inicial", usuarioController.cadastrar(email, nome, senha, telefone));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}