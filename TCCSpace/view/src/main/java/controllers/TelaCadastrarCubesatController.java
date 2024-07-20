package controllers;
import br.cefetmg.space.model.dao.CubeSatDAO;
import br.cefetmg.space.model.dao.UsuarioDAO;
import br.cefetmg.space.model.dto.CubeSatDTO;
import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.idao.ICubeSatDAO;
import br.cefetmg.space.model.idao.IUsuarioDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class TelaCadastrarCubesatController implements Initializable {
    
   private BufferedImage imagem;
    
   @FXML 
   private Button botaoCadastrarCubesat;
   
   private UsuarioDTO user;
    
   @FXML
   private Button botaoCubesat;
   
   @FXML
   private TextArea textDescricao;

    @FXML
    private Button botaoEquipe;

    @FXML
    private Button botaoExplorar;
    
    @FXML
    private Button botaoHome;
    
    @FXML 
    private ChoiceBox<String> choiceBoxAcesso;
    
    @FXML
    private TextField textNomeCubesat;
    
    @FXML
    private Label labelNomeCubesat;
    
    private String[] acesso = {"Público", "Privado"};
    
    @FXML
    private ImageView iconeEquipes;
    
    @FXML
    private ImageView iconeExplorar;
    
    @FXML
    private ImageView iconeSair;
    
    @FXML
    private Button botaoImagemCubesat;
    
    @FXML
    private ImageView perfilCubesat;
    
    private final FileChooser fileChooser = new FileChooser();
    
    private final Desktop desktop =  Desktop.getDesktop();
    
    private File arquivo;
    
    @FXML
    void cadastrarToPourple(MouseEvent event){
        botaoCadastrarCubesat.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
    }
    
    void setToNull(){
        textNomeCubesat.setText(null);
        textDescricao.setText(null);
        choiceBoxAcesso.setValue("Público");
    }
    
    @FXML
    void cadastrarToWhite(MouseEvent event){
        botaoCadastrarCubesat.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
    }
    
    @FXML
    void sairToPourple(MouseEvent event){
        iconeSair.setImage(new Image("file:src/main/resources/images/iconeSairLilas.png"));
    }
    
    @FXML
    void sairToWhite(MouseEvent event){
        iconeSair.setImage(new Image("file:src/main/resources/images/iconeSair.png"));
    }
    
    @FXML
    void explorarToPourple(MouseEvent event){
        botaoExplorar.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeExplorar.setImage(new Image("file:src/main/resources/images/iconeExplorarLilas.png"));
    }
    
    @FXML
    void explorarToWhite(MouseEvent event){
        botaoExplorar.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeExplorar.setImage(new Image("file:src/main/resources/images/iconeExplorar.png"));
    }
    
    @FXML
    void equipesToPourple(MouseEvent event){
        botaoEquipe.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeEquipes.setImage(new Image("file:src/main/resources/images/iconeEquipesLilas.png"));
    }
    
    @FXML
    void equipesToWhite(MouseEvent event){
        botaoEquipe.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeEquipes.setImage(new Image("file:src/main/resources/images/iconeEquipes.png"));
    }
    
    @FXML 
    void adicionarImagem(ActionEvent event) throws PersistenciaException{
        try{
            configurarFileChooser(fileChooser);
            arquivo = fileChooser.showOpenDialog(new Stage());
            if(arquivo !=  null){
                perfilCubesat.setImage(new Image("file:"+arquivo.getPath()));
                perfilCubesat.setStyle("-fx-border-radius: 50%;");
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void configurarFileChooser(final FileChooser fileChooser){
        FileChooser f = new FileChooser();
        f.getExtensionFilters().addAll(
                     new FileChooser.ExtensionFilter("Todas as imagens", "*.*"),
                     new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                     new FileChooser.ExtensionFilter("PNG", "*.png"),
                     new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
             );
    }
    
    @FXML
    void cadastrarCubesat(ActionEvent event) throws PersistenciaException{
        try{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            Alert erro = new Alert(Alert.AlertType.ERROR);
            CubeSatDTO cube = new CubeSatDTO();
            ICubeSatDAO cubeDAO = new CubeSatDAO();
            
            if(textNomeCubesat.getText() == null || textNomeCubesat.getText().isEmpty()){
                alert.setHeaderText("Por favor, informe o nome do CubeSat.");
                alert.show();
            }
            
            else if(cubeDAO.procurarPorNome(textNomeCubesat.getText()) != null){
                alert.setHeaderText("Já existe um CubeSat com esse nome.");
                alert.show();
            }
            
            else{
                LocalDateTime dataCadastro = LocalDateTime.now(); 
                cube.setDataCadastro(dataCadastro.toString());
                cube.setNome(textNomeCubesat.getText());
                cube.setAcesso(choiceBoxAcesso.getValue());
                cube.setPessoa(user);
                cube.setDescricao(textDescricao.getText());

                if(cubeDAO.inserir(cube)){
                    confirmacao.setHeaderText("Cubesat cadastrado com sucesso!");
                    confirmacao.show();
                }else{
                    erro.setHeaderText("Houve um erro ao inserir o cubesat.");
                    erro.show();
                }
                
                textNomeCubesat.setText(null);
                textDescricao.setText(null);
                choiceBoxAcesso.setValue("Público");
                
                MainFX.changedScreen("Cubesat", user);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    @FXML
    void apresentaTelaCubesat(ActionEvent event) {
        setToNull();
        MainFX.changedScreen("Cubesat", user);
    }

    @FXML
    void apresentaTelaEquipe(ActionEvent event) {
        setToNull();
        MainFX.changedScreen("Equipes", user);
    }

    @FXML
    void apresentaTelaExplorar(ActionEvent event) {
        setToNull();
        MainFX.changedScreen("Explorar", user);
    }
    
    
    @FXML
    void apresentarTelaInicial(ActionEvent event) {
        setToNull();
        MainFX.changedScreen("Tela Inicial", user);
    }
    
    @FXML
    private void nomeDoCubesat(){
        if(textNomeCubesat.getText().isEmpty())
            labelNomeCubesat.setText("<NOME>");
        else
            labelNomeCubesat.setText("<" + textNomeCubesat.getText().toUpperCase() + ">");
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
       
       MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen(){
           @Override
           public void onScreenChanged(String newString, Object viewData){
               user = (UsuarioDTO)viewData;
           }
       });
        
       choiceBoxAcesso.getItems().addAll(acesso);
       choiceBoxAcesso.setValue("Público");
    }
    
}