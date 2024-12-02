package controllers;

import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.view.MainFX;
import gui3d.LineChartManager;
import gui3d.Model3D;
import gui3d.Updater;
import java.io.File;
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
    private Button botaoGravarDados;

    @FXML
    private ImageView iconeSuporte;

    @FXML
    private ImageView iconePerfil;

    @FXML
    private MeshView meshVIew3D;

    private Usuario usuario;

    private CubeSat cubesat;

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
    void apresentaGraficos (ActionEvent event) throws IOException {
    
        MainFX.changedScreen("Graficos", cubesat);
    }
    
    @FXML
    void gravarDados(ActionEvent event){
        System.out.println("oi");
        FileChooser fileChooser = new FileChooser();
        Stage secondaryStage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(secondaryStage); 
        if (selectedFile != null) {
            try { Path path = Paths.get(selectedFile.getAbsolutePath()); 
            List<String> fileContent = Files.readAllLines(path); 
            fileContent.forEach(System.out::println); 
            } catch (IOException ex) { ex.printStackTrace(); } }
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
                    if(newString.equals("Gui3d"))
                        parte3d();
                } else if (viewData.getClass().equals(Usuario.class)) {
                    usuario = (Usuario) viewData;
                }
            }
        });

    }
}
