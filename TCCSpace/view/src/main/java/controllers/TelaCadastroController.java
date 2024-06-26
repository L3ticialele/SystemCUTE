package controllers;
import br.cefetmg.space.model.idao.IUsuarioDAO;
import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.dao.UsuarioDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
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
    private Button BotaoVoltar;

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

    public void voltar(ActionEvent e){
        MainFX.changedScreen("Login");
    }
    
    public void cadastrarBotao(ActionEvent e){
        if(CampoEmail.getText().isBlank() == true || CampoSenha.getText().isBlank() == true || CampoNome.getText().isBlank() == true || CampoTelefone.getText().isBlank() == true)
            msg.setText("Preencha os campos vazios");
        
        else
        {
            String email = CampoEmail.getText();
            String nome = CampoNome.getText();
            String senha = CampoSenha.getText();
            String telefone = CampoTelefone.getText();
            inserir(email, nome, senha, telefone);
            MainFX.changedScreen("Login");
        }
    }
    
        public void inserir(String email, String nome,String senha, String telefone){
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setEmail(email);
            usuario.setNome(nome);
            usuario.setSenha(senha);
            usuario.setTelefone(telefone);
            
            UsuarioDAO user = new UsuarioDAO();
        try {
            user.inserir(usuario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
