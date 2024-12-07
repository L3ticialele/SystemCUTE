package controllers;

import br.cefetmg.space.dao.DadosDAO;
import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Dados;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class GraficosController implements Initializable {

    @FXML
    private Button botaoCubesat;

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoPerfil;

    @FXML
    private Button botaoSuporte;

    @FXML
    private Button grafico01;

    @FXML
    private Button grafico02;

    @FXML
    private Button grafico03;

    @FXML
    private ImageView iconePerfil;

    @FXML
    private BorderPane painelGraficos;
    @FXML
    private ImageView iconeSuporte;

    private CubeSat cubesat;

    private DadosDAO dadosDao = new DadosDAO();
    private List<Dados> dadosCubeSat;
    private ArrayList<String> hora = new ArrayList<String>();
    private ArrayList<Float> altitude = new ArrayList<Float>();
    private ArrayList<Float> bateria = new ArrayList<Float>();
    //private Dados dadosCubeSat(cubesat);

    @FXML
    void apresentaTelaPerfil(ActionEvent event) {

    }

    @FXML
    void apresentaTelaSuporte(ActionEvent event) {

    }

    @FXML
    void apresentarTelaInicial(ActionEvent event) {

    }

    @FXML
    void voltarData3Dviewer(ActionEvent event) throws IOException {
        MainFX.changedScreen("Gui3d", cubesat);
    }

    @FXML
    void displayGrafico01(ActionEvent event) {
        CategoryAxis xHora = new CategoryAxis();
        xHora.setLabel("hora");

        NumberAxis yAltitude = new NumberAxis();
        yAltitude.setLabel("altitude");

        BarChart graficoHora_Altitude = new BarChart(xHora, yAltitude);
        XYChart.Series dadosHora_Altitude = new XYChart.Series();
        dadosHora_Altitude.setName("hora x altitude");
        //fornecer dados (lembrar de tirar comentário)

        for (int i = 0; i < hora.size(); i++) {
            String h = hora.get(i);
            Float a = altitude.get(i);
            dadosHora_Altitude.getData().add(new XYChart.Data<>(h, a));
        }
        /*for (String h, Float a : hora, altitude){
    
        dadosHora_Altitude.getData().add(new XYChart.Data(h, a));
        }
         */

        graficoHora_Altitude.getData().add(dadosHora_Altitude);
        painelGraficos.setCenter(graficoHora_Altitude);
    }

    @FXML
    void displayGrafico02(ActionEvent event) {

        CategoryAxis xHora = new CategoryAxis();
        xHora.setLabel("hora");

        NumberAxis yBateria = new NumberAxis();
        yBateria.setLabel("% bateria");

        LineChart graficoHora_Bateria = new LineChart(xHora, yBateria);
        XYChart.Series dadosHora_Bateria = new XYChart.Series();
        dadosHora_Bateria.setName("hora x bateria");
        //fornecer dados (lembrar de tirar comentário)
        for (int i = 0; i < hora.size(); i++) {
            String h = hora.get(i);
            Float b = bateria.get(i);
            dadosHora_Bateria.getData().add(new XYChart.Data<>(h, b));
        }

        graficoHora_Bateria.getData().add(dadosHora_Bateria);
        painelGraficos.setCenter(graficoHora_Bateria);

    }

    @FXML
    void displayGrafico03(ActionEvent event) {

        NumberAxis altitude = new NumberAxis();
        altitude.setLabel("altitude");

        NumberAxis pressao = new NumberAxis();
        pressao.setLabel("pressao");

        LineChart graficoAltitudePressao = new LineChart(altitude, pressao);
        XYChart.Series dados = new XYChart.Series();

        XYChart.Series dadosAltitudePressao = new XYChart.Series();

        dados.setName("altitude x pressao");
        //fornecer dados (lembrar de tirar comentário)
        dados.getData().add(new XYChart.Data(0, 100));
        dados.getData().add(new XYChart.Data(300, 70));
        dados.getData().add(new XYChart.Data(900, 40));
        dados.getData().add(new XYChart.Data(200, 20));
        dados.getData().add(new XYChart.Data(0, 10));

        graficoAltitudePressao.getData().add(dados);
        painelGraficos.setCenter(grafico02);

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

    private void carregaDados() throws PersistenciaException {

        dadosCubeSat = dadosDao.procurarPorCubeSat(cubesat);

        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setContentText(dadosCubeSat.toString());

        for (Dados d : dadosCubeSat) {

            //alerta.setContentText(d.getHora());
            //alerta.showAndWait();
            //alerta.setContentText("1:" + d.getHora());
            //alerta.showAndWait();
            hora.add(d.getHora());
            //alerta.setContentText(d.getHora());
            //alerta.showAndWait();
            altitude.add(d.getAltitude());
            bateria.add(d.getBateria());

            //alerta.setContentText(d.getHora());
            //alerta.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {

            if (viewData instanceof CubeSat) {
                cubesat = (CubeSat) viewData;

                try {
                    carregaDados();
                } catch (PersistenciaException ex) {
                    Logger.getLogger(GraficosController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

}
