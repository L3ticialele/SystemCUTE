package controllers;

import br.cefetmg.space.controller.DadosController;
import br.cefetmg.space.controller.Data3DViewer;
import br.cefetmg.space.dao.DadosDAO;
import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Dados;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.exception.PersistenciaException;
import br.cefetmg.space.view.MainFX;
import gui3d.LineChartManager;
import java.io.BufferedReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.MeshView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Data3DViewerController {

    @FXML
    private TableColumn<Dados, String> colunaGravacao;

    @FXML
    private TableColumn<Dados, String> colunaData;

    @FXML
    private TableColumn<Dados, String> colunaHora;

    @FXML
    private TableView<Dados> tabelaGravacoes;

    private int ultimaGravacao;

    private ArrayList<Dados> listaGravacoes;

    private final ArrayList lista = new ArrayList<>();

    @FXML
    private Label labelGravacao;

    @FXML
    private Label labelNomeCubesat;

    @FXML
    private LineChart<String, Number> lineChart;

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

    @FXML
    private Button botaoEditarCubesat;

    private Usuario usuario;

    private CubeSat cubesat;

    private Stage caixaDialogo;

    private static Data3DViewer data3D = new Data3DViewer();

    private final DadosController dadosController = new DadosController();

    @FXML
    private Label labelAltitude;

    @FXML
    private Label labelBateria;

    @FXML
    private Label labelCorrenteBateria;

    @FXML
    private Label labelCorrentePlacaSolar;

    @FXML
    private Label labelEixoX;

    @FXML
    private Label labelEixoY;

    @FXML
    private Label labelEixoZ;

    @FXML
    private Label labelGas1;

    @FXML
    private Label labelGas2;

    @FXML
    private Label labelHorario;

    @FXML
    private Label labelLuz1;

    @FXML
    private Label labelLuz2;

    @FXML
    private Label labelPontoOrvalho;

    @FXML
    private Label labelPotenciaBateria;

    @FXML
    private Label labelPotenciaPlacaSolar;

    @FXML
    private Label labelPressao;

    @FXML
    private Label labelSensorUV;

    @FXML
    private Label labelTemperaturaExterna;

    @FXML
    private Label labelTemperaturaInterna;

    @FXML
    private Label labelTensaoBateria;

    @FXML
    private Label labelTensaoPlacaSolar;

    @FXML
    private Label labelUmidade;

    @FXML
    void gravarDados(ActionEvent event) throws PersistenciaException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(caixaDialogo);
        String data = null, hora = null, tempInterna = null, tempExterna = null;
        String X = null, Y = null, Z = null, anguloX = null, anguloY = null, anguloZ = null;
        String altitude = null, bateria = null, correnteBateria = null, correntePlacaSolar = null;
        String luz1 = null, luz2 = null, pontoOrvalho = null, pressao = null, sensorUV = null;
        String tensaoBateria = null, tensaoPlacaSolar = null, umidade = null;
        int id = 1;
        float floatX = 0, floatY = 0, floatZ = 0, floatAnguloX = 0, floatAnguloY = 0, floatAnguloZ = 0;
        float floatAltide = 0, floatBateria = 0, floatCorrenteBateria = 0, floatCorrentePlacaSolar = 0;
        float floatLuz1 = 0, floatLuz2 = 0, floatPontoOrvalho = 0, floatPressao = 0, floatSensorUV = 0;
        float floatTensaoBateria = 0, floatTensaoPlacaSolar = 0, floatTempExterna = 0, floatTempInterna = 0, floatUmidade = 0;
        DadosDAO dadoDao = new DadosDAO();
        int lineNumber = 1;
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    content.append(line).append("\n");
                    int lineNumer;
                    switch (lineNumber) {
                        case 1:
                            data = line;
                            break;
                        case 2:
                            X = line;
                            floatX = Float.parseFloat(X);
                            break;
                        case 3:
                            Y = line;
                            floatY = Float.parseFloat(Y);
                            break;
                        case 4:
                            Z = line;
                            floatZ = Float.parseFloat(Z);
                            break;
                        case 5:
                            anguloX = line;
                            floatAnguloX = Float.parseFloat(anguloX);
                            break;
                        case 6:
                            anguloY = line;
                            floatAnguloY = Float.parseFloat(anguloY);
                            break;
                        case 7:
                            anguloZ = line;
                            floatAnguloZ = Float.parseFloat(anguloZ);
                            break;
                        case 8:
                            altitude = line;
                            floatAltide = Float.parseFloat(altitude);
                            break;
                        case 9:
                            bateria = line;
                            floatBateria = Float.parseFloat(bateria);
                            break;
                        case 10:
                            correnteBateria = line;
                            floatCorrenteBateria = Float.parseFloat(correnteBateria);
                            break;
                        case 11:
                            correntePlacaSolar = line;
                            floatCorrentePlacaSolar = Float.parseFloat(correntePlacaSolar);
                            break;
                        case 12:
                            luz1 = line;
                            floatLuz1 = Float.parseFloat(luz1);
                            break;
                        case 13:
                            luz2 = line;
                            floatLuz2 = Float.parseFloat(luz2);
                            break;
                        case 14:
                            pontoOrvalho = line;
                            floatPontoOrvalho = Float.parseFloat(pontoOrvalho);
                            break;
                        case 15:
                            pressao = line;
                            floatPressao = Float.parseFloat(pressao);
                            break;
                        case 16:
                            sensorUV = line;
                            floatSensorUV = Float.parseFloat(sensorUV);
                            break;
                        case 17:
                            tensaoBateria = line;
                            floatTensaoBateria = Float.parseFloat(tensaoBateria);
                            break;
                        case 18:
                            tensaoPlacaSolar = line;
                            floatTensaoPlacaSolar = Float.parseFloat(tensaoPlacaSolar);
                            break;
                        case 19:
                            tempExterna = line;
                            floatTempExterna = Float.parseFloat(tempExterna);
                            break;
                        case 20:
                            tempInterna = line;
                            floatTempInterna = Float.parseFloat(tempInterna);
                            break;
                        case 21:
                            umidade = line;
                            floatUmidade = Float.parseFloat(umidade);
                        case 22:
                            hora = line;

                            Dados dado = new Dados(floatX, floatY, floatZ, floatAnguloX, floatAnguloY, floatAnguloZ, floatAltide, floatBateria, floatCorrenteBateria, floatCorrentePlacaSolar, floatLuz1, floatLuz2, floatPontoOrvalho, floatPressao, floatSensorUV, floatTempExterna, floatTempInterna, floatTensaoBateria, floatTensaoPlacaSolar, floatUmidade, cubesat, data, hora);
                            dadoDao.inserir(dado);
                            lineNumber = 1;
                            continue;
                    }
                    lineNumber++;

                }

            } catch (IOException e) {
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
    void apresentarTelaEditarCubesat(ActionEvent event) throws IOException {
        MainFX.changedScreen("Editar Cubesat", cubesat);
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
        Stage stage = (Stage) botaoBaixarPlanilha.getScene().getWindow();
        File file = data3D.abrirExploradorArquivos().showSaveDialog(stage);
        data3D.baixarPlanilha(file);
    }

    @FXML
    void apresentaGraficos(ActionEvent event) throws IOException {

        MainFX.changedScreen("Graficos", cubesat);
    }

    @FXML
    void atualizarDados() throws PersistenciaException {
        listaGravacoes = dadosController.atualizaDados(ultimaGravacao, cubesat);
        tabelaGravacoes.setItems(FXCollections.observableArrayList(listaGravacoes));
    }
    
    void mostrarDados(Dados dados){
        if (dados != null) {
            labelGravacao.setText("GRAVAÇÃO " + dados.getId() + " - " + dados.getDataObtencao());
            labelAltitude.setText(Float.toString(dados.getAltitude()));
            labelBateria.setText(Float.toString(dados.getBateria()));
            labelCorrenteBateria.setText(Float.toString(dados.getBateria()));
            labelCorrentePlacaSolar.setText(Float.toString(dados.getCorrentePlacaSolar()));
            labelEixoX.setText(Float.toString(dados.getAnguloX()));
            labelEixoY.setText(Float.toString(dados.getAnguloY()));
            labelEixoZ.setText(Float.toString(dados.getAnguloZ()));
            //labelGas1.setText(Float.toString(dados.getGas1()));
            //labelGas2.setText(Float.toString(dados.getGas2()));
            labelHorario.setText(dados.getHora());
            labelLuz1.setText(Float.toString(dados.getLuz1()));
            labelLuz2.setText(Float.toString(dados.getLuz2()));
            labelPontoOrvalho.setText(Float.toString(dados.getPontoOrvalho()));
            //labelPotenciaBateria.setText(Float.toString(dados.getPotenciaBateria()));
            //labelPotenciaPlacaSolar.setText(Float.toString(dados.getPotenciaPlacaSolar()));
            labelPressao.setText(Float.toString(dados.getPressao()));
            labelSensorUV.setText(Float.toString(dados.getSensorUV()));
            labelTemperaturaExterna.setText(Float.toString(dados.getTemperaturaExterna()));
            labelTemperaturaInterna.setText(Float.toString(dados.getTemperaturaInterna()));
            labelTensaoBateria.setText(Float.toString(dados.getTensaoBateria()));
            labelTensaoPlacaSolar.setText(Float.toString(dados.getTensaoPlacaSolar()));
            labelUmidade.setText(Float.toString(dados.getUmidade()));
        }
    }

    @FXML
    void selecionarGravacao(MouseEvent event) {
        int i = tabelaGravacoes.getSelectionModel().getSelectedIndex();
        Dados dados = (Dados) tabelaGravacoes.getItems().get(i);
        mostrarDados(dados);
    }

    @FXML
    private void initialize() {
        MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newString, Object viewData) {
                if (viewData instanceof CubeSat) {
                    cubesat = (CubeSat) viewData;
                    usuario = cubesat.getUsuario();
                    System.out.println(cubesat.getNome());
                    labelNomeCubesat.setText(cubesat.getNome());

                    try {
                        List<Dados> dadosCubesat = dadosController.listarDadosPorCubeSat(cubesat);
                        if (dadosCubesat != null) {
                            Dados ultimoDado = dadosCubesat.get(dadosCubesat.size() - 1);
                            mostrarDados(ultimoDado);
                        } else {
                            labelGravacao.setText("Não houve nenhuma gravação.");
                        }
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(Data3DViewerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ultimaGravacao = 0;

                    colunaGravacao.setCellValueFactory(
                            new PropertyValueFactory<>("id"));
                    colunaData.setCellValueFactory(
                            new PropertyValueFactory<>("dataObtencao"));
                    colunaHora.setCellValueFactory(
                            new PropertyValueFactory<>("hora"));

                    try {
                        atualizarDados();
                    } catch (PersistenciaException ex) {
                        Logger.getLogger(Data3DViewerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // if (newString.equals("Gui3d")) {
                    //   parte3d();
                    //}
                }
            }
        });

    }
}
