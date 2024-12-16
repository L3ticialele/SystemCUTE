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
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private Button montarGrafico;

    @FXML
    private ComboBox<String> opcao1;

    @FXML
    private ComboBox<String> opcao2;

    @FXML
    private ImageView iconePerfil;

    @FXML
    private BorderPane painelGraficos;
    @FXML
    private ImageView iconeSuporte;
    
    private Usuario usuario;


    private CubeSat cubesat;

    private DadosDAO dadosDao = new DadosDAO();
    private List<Dados> dadosCubeSat;
    private ArrayList<Object> hora = new ArrayList<Object>();
    private ArrayList<Object> altitude = new ArrayList<Object>();
    private ArrayList<Object> bateria = new ArrayList<Object>();
    private ArrayList<Object> tensaoBateria = new ArrayList<Object>();
    private ArrayList<Object> pressao = new ArrayList<Object>();
    private ArrayList<Object> pontoOrvalho = new ArrayList<Object>();
    private ArrayList<Object> correnteBateria = new ArrayList<Object>();
    private ArrayList<Object> sensorUV = new ArrayList<Object>();
    //private Dados dadosCubeSat(cubesat);

     @FXML
    void apresentaTelaSuporte(ActionEvent event) throws IOException {
        MainFX.changedScreen("Suporte", usuario);
    }

    @FXML
    void apresentaTelaInicial(ActionEvent event) throws IOException {
        MainFX.changedScreen("Tela Inicial", usuario);
    }

    @FXML
    void apresentaTelaPerfil(ActionEvent event) throws IOException {
        MainFX.changedScreen("VerificarSenha", usuario);
    }

    @FXML
    void voltarData3Dviewer(ActionEvent event) throws IOException {
        MainFX.changedScreen("Gui3d", cubesat);
    }

    ArrayList<ArrayList<Object>> selectListas() {
        ArrayList<ArrayList<Object>> listas = new ArrayList<ArrayList<Object>>();
        switch (opcao1.getValue()) {

            case "hora":
                listas.add(hora);
                break;
            case "altitude":
                listas.add(altitude);
                break;
            case "pressão":
                listas.add(pressao);
                break;
            case "bateria":
                listas.add(bateria);
                break;
            case "tensão da bateria":
                listas.add(tensaoBateria);
                break;
            case "ponto de orvalho":
                listas.add(pontoOrvalho);
                break;
            case "corrente da bateria":
                listas.add(correnteBateria);
                break;
            case "sensor UV":
                listas.add(sensorUV);
                break;
        }
        switch (opcao2.getValue()) {

            case "hora":
                listas.add(hora);
                break;
            case "altitude":
                listas.add(altitude);
                break;
            case "pressão":
                listas.add(pressao);
                break;            
            case "bateria":
                listas.add(bateria);
                break;
            case "tensão da bateria":
                listas.add(tensaoBateria);
                break;
            case "ponto de orvalho":
                listas.add(pontoOrvalho);
                break;
            case "corrente da bateria":
                listas.add(correnteBateria);
                break;
            case "sensor UV":
                listas.add(sensorUV);
                break;
        }
        return listas;
    }

    @FXML
    void montarGrafico(ActionEvent event) {

        Axis x;
        Axis y;
        Chart grafico;
        ArrayList<ArrayList<Object>> listasSelec = selectListas();
        ArrayList<Object> listaOpcao1 = listasSelec.get(0);
        ArrayList<Object> listaOpcao2 = listasSelec.get(1);
        XYChart.Series<Object, Object> dados = new XYChart.Series<>();

        switch (opcao1.getValue()) {

            case "hora":
                x = new CategoryAxis();
                y = new NumberAxis();
                grafico = new BarChart(x, y);

                for (int i = 0; i < listaOpcao1.size(); i++) {
                    String h = listaOpcao1.get(i).toString();
                    Float a = Float.parseFloat(listaOpcao2.get(i).toString());
                    dados.getData().add(new XYChart.Data<>(h, a));
                }

                break;
            default:
                x = new NumberAxis();
                if (opcao2.getValue() != "hora") {
                    y = new NumberAxis();
                    for (int i = 0; i < listaOpcao1.size(); i++) {
                        Float b = Float.parseFloat(listaOpcao1.get(i).toString());
                        Float a = Float.parseFloat(listaOpcao2.get(i).toString());
                        dados.getData().add(new XYChart.Data<>(b, a));
                    }
                    grafico = new LineChart(x, y);
                } else {

                    y = new CategoryAxis();
                    for (int i = 0; i < listaOpcao1.size(); i++) {
                        Float h = Float.parseFloat(listaOpcao1.get(i).toString());
                        String a = listaOpcao2.get(i).toString();
                        dados.getData().add(new XYChart.Data<>(h, a));
                    }
                    grafico = new BarChart(x, y);
                }

                break;
        }
        //opcao2.getItems().remove(opcao1.getValue());

        dados.setName(opcao1.getValue() + " x " + opcao2.getValue());
        x.setLabel(opcao1.getAccessibleText());
        y.setLabel(opcao2.getAccessibleText());
        if (grafico instanceof BarChart) {
            ((BarChart) grafico).getData().add(dados);
        } else if (grafico instanceof LineChart) {
            ((LineChart) grafico).getData().add(dados);
        }
        painelGraficos.setCenter(grafico);

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

            hora.add(d.getHora());
            altitude.add(d.getAltitude());
            bateria.add(d.getBateria());
            pressao.add(d.getPressao());
            tensaoBateria.add(d.getTensaoBateria());
            pontoOrvalho.add(d.getPontoOrvalho());
            correnteBateria.add(d.getCorrenteBateria());
            sensorUV.add(d.getSensorUV());

        }

    }

    private void carregaOpcoes() {

        opcao1.getItems().addAll("hora", "altitude", "bateria", "pressão", "tensão da bateria", "ponto de orvalho", "corrente da bateria", "sensor UV");
        opcao2.getItems().addAll("hora", "altitude", "bateria", "pressão", "tensão da bateria", "ponto de orvalho", "corrente da bateria", "sensor UV");
        opcao1.setPromptText("atributo 1");
        opcao2.setPromptText("atributo 2");
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

                carregaOpcoes();

            }
        });
    }

}
