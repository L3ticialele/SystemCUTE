package controllers;

import br.cefetmg.space.dao.DadosDAO;
import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Dados;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import gui3d.LineChartManager;
import gui3d.Model3D;
import gui3d.Updater;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.MeshView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class Data3DViewerController {

    @FXML
    private Label labelAccelerationX;

    @FXML
    private Label labelAccelerationY;

    @FXML
    private Label labelAccelerationZ;

    @FXML
    private Label labelRotationX;

    @FXML
    private Label labelRotationY;

    @FXML
    private Label labelRotationZ;

    @FXML
    private Label labelSpeedX;

    @FXML
    private Label labelSpeedY;

    @FXML
    private Label labelSpeedZ;

    @FXML
    private Label labelAltitude;

    @FXML
    private Label labelBateria;

    @FXML
    private Label labelCorrenteBateria;

    @FXML
    private Label labelTensaoBateria;

    @FXML
    private Label labelPotenciaBateria;

    @FXML
    private Label labelTensaoPlacaSolar;

    @FXML
    private Label labelCorrentePlacaSolar;

    @FXML
    private Label labelPotenciaPlacaSolar;

    @FXML
    private Label labelGas1;

    @FXML
    private Label labelGas2;

    @FXML
    private Label labelLuz1;

    @FXML
    private Label labelLuz2;

    @FXML
    private Label labelPontoOrvalho;

    @FXML
    private Label labelPressao;

    @FXML
    private Label labelSensorUV;

    @FXML
    private Label labelTemperaturaExterna;

    @FXML
    private Label labelTemperaturaInterna;

    @FXML
    private Label labelUmidade;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private MenuBar menuBarTrocarGrafico;

    @FXML
    private MenuItem menuItemAltitude;

    @FXML
    private MenuItem menuItemPotenciaBateria;

    @FXML
    private MenuItem menuItemPotenciaPainelSolar;

    @FXML
    private MenuItem menuItemPressao;

    @FXML
    private MenuItem menuItemSensorUV;

    @FXML
    private MenuItem menuItemUmidade;

    @FXML
    private MenuItem menuItemTemperatura;

    @FXML
    private AnchorPane anchorPane3d;

    private LineChartManager lineChartManager;
    private LineChartManager chartManager;

    @FXML
    private Button botaoCubesat;

    @FXML
    private Button botaoSuporte;

    @FXML
    private Button botaoPerfil;

    @FXML
    private Button botaoHome;
    
    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoBaixarPlanilha;

    private Button botaoGravarDados;


    @FXML
    private ImageView iconeSuporte;

    @FXML
    private ImageView iconePerfil;

    @FXML
    private MeshView meshVIew3D;

    private Usuario usuario;

    private CubeSat cubesat;
    

    
    
    private Stage caixaDialogo;
    
     @FXML
    void gravarDados(ActionEvent event) throws PersistenciaException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(caixaDialogo);
        String data = null, tempInterna = null, tempExterna = null;
        String X = null, Y = null, Z=null, anguloX=null, anguloY=null, anguloZ=null;
        String altitude=null, bateria=null, correnteBateria=null, correntePlacaSolar=null;
        String luz1=null,  luz2=null,pontoOrvalho=null, pressao=null,  sensorUV=null;
        String tensaoBateria=null, tensaoPlacaSolar=null, umidade=null;
        int id=1;
        float floatX=0, floatY=0,floatZ=0,floatAnguloX=0, floatAnguloY=0, floatAnguloZ=0;
        float floatAltide=0, floatBateria=0, floatCorrenteBateria=0, floatCorrentePlacaSolar=0;
        float floatLuz1=0, floatLuz2=0, floatPontoOrvalho=0,floatPressao=0, floatSensorUV=0;
        float floatTensaoBateria=0, floatTensaoPlacaSolar=0, floatTempExterna=0, floatTempInterna=0,floatUmidade=0;
        DadosDAO dadoDao = new DadosDAO();
        int lineNumber = 1;
        if(file != null){
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null){
                    System.out.println(line);
                    content.append(line).append("\n");
                    int lineNumer;
                    switch(lineNumber){
                        case 1: data = line;
                                break;
                        case 2: X = line;
                                floatX = Float.parseFloat(X);
                                break;
                        case 3: Y = line;
                                floatY = Float.parseFloat(Y);
                                break;
                        case 4: Z = line;
                                floatZ = Float.parseFloat(Z);
                                break;
                        case 5: anguloX = line;
                                floatAnguloX = Float.parseFloat(anguloX);
                                break;
                        case 6: anguloY = line;
                                floatAnguloY = Float.parseFloat(anguloY);
                                break;
                        case 7: anguloZ = line;
                                floatAnguloZ = Float.parseFloat(anguloZ);
                                break;   
                        case 8: altitude = line;
                                floatAltide = Float.parseFloat(altitude);
                                break;
                        case 9: bateria = line;
                                floatBateria = Float.parseFloat(bateria);
                                break; 
                        case 10: correnteBateria = line;
                                floatCorrenteBateria = Float.parseFloat(correnteBateria);
                                break; 
                        case 11: correntePlacaSolar = line;
                                floatCorrentePlacaSolar = Float.parseFloat(correntePlacaSolar);
                                break; 
                        case 12: luz1 = line;
                                floatLuz1 = Float.parseFloat(luz1);
                                break;
                        case 13: luz2 = line;
                               floatLuz2 = Float.parseFloat(luz2);
                                break;
                        case 14: pontoOrvalho = line;
                                floatPontoOrvalho = Float.parseFloat(pontoOrvalho);
                                break;
                        case 15: pressao = line;
                                floatPressao = Float.parseFloat(pressao);
                                break;
                        case 16: sensorUV = line;
                                floatSensorUV = Float.parseFloat(sensorUV);
                                break;
                        case 17: tensaoBateria = line;
                                floatTensaoBateria = Float.parseFloat(tensaoBateria);
                                break;
                        case 18: tensaoPlacaSolar = line;
                                floatTensaoPlacaSolar = Float.parseFloat(tensaoPlacaSolar);
                                break;
                        case 19: tempExterna = line;
                                floatTempExterna = Float.parseFloat(tempExterna);
                                break;
                        case 20: tempInterna = line;
                                floatTempInterna = Float.parseFloat(tempInterna);
                                break;
                        case 21: umidade = line;
                                floatUmidade = Float.parseFloat(umidade);
                              
                                
                                Dados dado = new Dados(id,floatX, floatY,floatZ, floatAnguloX,floatAnguloY,floatAnguloZ, floatAltide, floatBateria,floatCorrenteBateria, floatCorrentePlacaSolar,floatLuz1,  floatLuz2,floatPontoOrvalho, floatPressao,  floatSensorUV, floatTempExterna,floatTempInterna,  floatTensaoBateria, floatTensaoPlacaSolar, floatUmidade,  cubesat, data);   
                                dadoDao.inserir(dado);
                                lineNumber = 1;
                                continue;
                    }
                    lineNumber++;
                    
                }
                
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void perfilToPourple(MouseEvent event) {
        botaoPerfil.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconePerfil.setImage(new Image("file:src/main/resources/images/userLilas.png"));
    }

    @FXML
    void perfilToWhite(MouseEvent event) {
        botaoPerfil.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconePerfil.setImage(new Image("file:src/main/resources/images/user.png"));
    }

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
    void telaCadastrarCubesat(ActionEvent event) throws IOException {
        MainFX.changedScreen("Cadastrar Cubesat", usuario);
    }

    @FXML
    void apresentaTelaSuporte(ActionEvent event) throws IOException {
        MainFX.changedScreen("Suporte", usuario);
    }

    @FXML
    void apresentaTelaPerfil(ActionEvent event) throws IOException {
        MainFX.changedScreen("Perfil", usuario);
    }

    @FXML
    void apresentarTelaInicial(ActionEvent event) throws IOException {
        MainFX.changedScreen("Tela Inicial", usuario);
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        MainFX.changedScreen("Tela Inicial", usuario);
    }
    
    @FXML
    void baixarPlanilha(ActionEvent event) throws IOException {
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("PlanilhaVazia");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Planilha");
        fileChooser.setInitialFileName("PlanilhaCubesat.xlsx");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos Excel", "*.xlsx"));

        Stage stage = (Stage) botaoBaixarPlanilha.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                System.out.println("Planilha vazia salva em: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao salvar a planilha.");
            } finally {
                workbook.close();
            }
        } else {
            System.out.println("Operação de salvamento cancelada.");
        }
    }
    
    @FXML
    void apresentaGraficos (ActionEvent event) throws IOException {
    
        MainFX.changedScreen("Graficos", cubesat);
    }
    

    private void configureMenuActions() {
        menuItemTemperatura.setOnAction(event -> chartManager.setActiveSeries("Temperatura Interna"));
        menuItemAltitude.setOnAction(event -> chartManager.setActiveSeries("Altitude"));
        menuItemPotenciaBateria.setOnAction(event -> chartManager.setActiveSeries("Potência da Bateria"));
        menuItemPotenciaPainelSolar.setOnAction(event -> chartManager.setActiveSeries("Potência do Painel Solar"));
        menuItemPressao.setOnAction(event -> chartManager.setActiveSeries("Pressão"));
        menuItemSensorUV.setOnAction(event -> chartManager.setActiveSeries("Sensor UV"));
        menuItemUmidade.setOnAction(event -> chartManager.setActiveSeries("Umidade"));
    }

    private void parte3d() {
        int defaultRotateX = 120;
        int defaultRotateY = 0;
        int defaultRotateZ = 35;
        chartManager = new LineChartManager(lineChart);
        configureMenuActions();
        menuItemAltitude.fire();
        Model3D model3D = new Model3D();
        MeshView meshView = model3D.getMeshView();
        anchorPane3d.getChildren().add(meshView);
        meshView.setScaleX(2);
        meshView.setScaleY(2);
        meshView.setScaleZ(2);
        meshView.setTranslateX(210);
        meshView.setTranslateY(150);
        meshView.setTranslateZ(0);
        model3D.rotateModel(defaultRotateX, defaultRotateY, defaultRotateZ);

        // Inicializando o Updater com as labels correspondentes
        Updater updater = new Updater(labelAccelerationX, labelAccelerationY, labelAccelerationZ,
                labelRotationX, labelRotationY, labelRotationZ,
                labelSpeedX, labelSpeedY, labelSpeedZ,
                labelAltitude, labelBateria, labelCorrenteBateria,
                labelTensaoBateria, labelPotenciaBateria, labelCorrentePlacaSolar,
                labelTensaoPlacaSolar, labelPotenciaPlacaSolar, labelGas1,
                labelGas2, labelLuz1, labelLuz2, labelPontoOrvalho,
                labelPressao, labelSensorUV, labelTemperaturaExterna,
                labelTemperaturaInterna, labelUmidade, model3D, chartManager);
        updater.startUpdating();
    }

    @FXML
    private void initialize() {
        MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newString, Object viewData) {
                if (viewData.getClass().equals(CubeSat.class)) {
                    cubesat = (CubeSat) viewData;
                    usuario = cubesat.getUsuario();

                    if (newString.equals("Gui3d")) {
                        parte3d();
                    }

                } else if (viewData.getClass().equals(Usuario.class)) {
                    usuario = (Usuario) viewData;
                }
            }
        });

    }
}
