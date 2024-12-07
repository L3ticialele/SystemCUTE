package controllers;

import br.cefetmg.space.controller.UsuarioController;
import br.cefetmg.space.controller.ValidaCamposController;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TelaCadastrarUsuarioController implements Initializable {

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
    
    @FXML
    private ImageView iconeSair;
    
    private final UsuarioController usuarioController = new UsuarioController();
    
    private final ValidaCamposController validador = new ValidaCamposController();

    public void voltarPaginaLogin(ActionEvent e) throws IOException {
        MainFX.changedScreen("Login", null);
    }
    
    @FXML
    void sairToPourple(MouseEvent event) {
        iconeSair.setImage(new Image("file:src/main/resources/images/iconeSairLilas.png"));
    }

    @FXML
    void sairToWhite(MouseEvent event) {
        iconeSair.setImage(new Image("file:src/main/resources/images/iconeSair.png"));
    }
    
    public boolean validaCampos(String email, String senha, String telefone){
        if(!validador.validaEmail(email)){
            msg.setText("Email invalido");
            return false;
        }
        else if(!validador.validarTelefone(telefone)){
            msg.setText("Telefone Inválido");
            return false;
        }
        else if(!validador.senhaForte(senha)){
            msg.setText("Senha fraca");
            return false;
        }
        return true;
    }

    public void BotaoCadastrar(ActionEvent e) throws IOException, PersistenciaException {
        if (CampoEmail.getText().isBlank() == true || CampoSenha.getText().isBlank() == true || CampoNome.getText().isBlank() == true || CampoTelefone.getText().isBlank() == true) {
            msg.setText("Preencha os campos vazios");
        } else {
            String email = CampoEmail.getText();
            String nome = CampoNome.getText();
            String senha = CampoSenha.getText();
            String telefone = CampoTelefone.getText();
            if(validaCampos(email, senha, telefone))
            MainFX.changedScreen("Tela Inicial", usuarioController.cadastrar(email, nome, senha, telefone));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}