package controllers;

import gui3d.LineChartManager;
import gui3d.Model3D;
import br.cefetmg.space.model.dto.DadosDTO;
import br.cefetmg.space.model.dao.DadosDAO;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class UpdaterController {

    private final DadosDAO dadosDAO;
    private final Label labelAccelerationX;
    private final Label labelAccelerationY;
    private final Label labelAccelerationZ;
    private final Label labelRotationX;
    private final Label labelRotationY;
    private final Label labelRotationZ;
    private final Label labelSpeedX;
    private final Label labelSpeedY;
    private final Label labelSpeedZ;
    private final Label labelAltitude;
    private final Label labelBateria;
    private final Label labelCorrenteBateria;
    private final Label labelTensaoBateria;
    private final Label labelPotenciaBateria;
    private final Label labelCorrentePlacaSolar;
    private final Label labelTensaoPlacaSolar;
    private final Label labelPotenciaPlacaSolar;
    private final Label labelGas1;
    private final Label labelGas2;
    private final Label labelLuz1;
    private final Label labelLuz2;
    private final Label labelPontoOrvalho;
    private final Label labelPressao;
    private final Label labelSensorUV;
    private final Label labelTemperaturaExterna;
    private final Label labelTemperaturaInterna;
    private final Label labelUmidade;
    private final Model3D model3d;
    private final LineChartManager lineChartManager;
    private double prevX;
    private double prevY;
    private double prevZ;

    public UpdaterController(Label labelAccelerationX, Label labelAccelerationY, Label labelAccelerationZ,
            Label labelRotationX, Label labelRotationY, Label labelRotationZ,
            Label labelSpeedX, Label labelSpeedY, Label labelSpeedZ,
            Label labelAltitude, Label labelBateria, Label labelCorrenteBateria,
            Label labelTensaoBateria, Label labelPotenciaBateria, Label labelCorrentePlacaSolar,
            Label labelTensaoPlacaSolar, Label labelPotenciaPlacaSolar, Label labelGas1,
            Label labelGas2, Label labelLuz1, Label labelLuz2, Label labelPontoOrvalho,
            Label labelPressao, Label labelSensorUV, Label labelTemperaturaExterna,
            Label labelTemperaturaInterna, Label labelUmidade, Model3D model3d, LineChartManager lineChartManager) {
        this.labelAccelerationX = labelAccelerationX;
        this.labelAccelerationY = labelAccelerationY;
        this.labelAccelerationZ = labelAccelerationZ;
        this.labelRotationX = labelRotationX;
        this.labelRotationY = labelRotationY;
        this.labelRotationZ = labelRotationZ;
        this.labelSpeedX = labelSpeedX;
        this.labelSpeedY = labelSpeedY;
        this.labelSpeedZ = labelSpeedZ;
        this.labelAltitude = labelAltitude;
        this.labelBateria = labelBateria;
        this.labelCorrenteBateria = labelCorrenteBateria;
        this.labelTensaoBateria = labelTensaoBateria;
        this.labelPotenciaBateria = labelPotenciaBateria;
        this.labelCorrentePlacaSolar = labelCorrentePlacaSolar;
        this.labelTensaoPlacaSolar = labelTensaoPlacaSolar;
        this.labelPotenciaPlacaSolar = labelPotenciaPlacaSolar;
        this.labelGas1 = labelGas1;
        this.labelGas2 = labelGas2;
        this.labelLuz1 = labelLuz1;
        this.labelLuz2 = labelLuz2;
        this.labelPontoOrvalho = labelPontoOrvalho;
        this.labelPressao = labelPressao;
        this.labelSensorUV = labelSensorUV;
        this.labelTemperaturaExterna = labelTemperaturaExterna;
        this.labelTemperaturaInterna = labelTemperaturaInterna;
        this.labelUmidade = labelUmidade;
        this.model3d = model3d;
        this.dadosDAO = new DadosDAO(); // Instanciação do DAO para acessar os dados do banco
        this.lineChartManager = lineChartManager;
    }

    public void startUpdating() {
        Thread updaterThread = new Thread(() -> {
            try {
                while (true) {
                    DadosDTO dadoMaisRecente = dadosDAO.buscarDadoMaisRecente();
                    if (dadoMaisRecente != null) {
                        Platform.runLater(() -> {
                            // Atualiza as labels com os novos dados
                            labelAccelerationX.setText(String.format("%.2f", dadoMaisRecente.getAcelerometroX()));
                            labelAccelerationY.setText(String.format("%.2f", dadoMaisRecente.getAcelerometroY()));
                            labelAccelerationZ.setText(String.format("%.2f", dadoMaisRecente.getAcelerometroZ()));
                            labelRotationX.setText(String.format("%.2f", dadoMaisRecente.getAnguloX()));
                            labelRotationY.setText(String.format("%.2f", dadoMaisRecente.getAnguloY()));
                            labelRotationZ.setText(String.format("%.2f", dadoMaisRecente.getAnguloZ()));
                            /*
                            labelSpeedX.setText(String.format("%.2f", dadoMaisRecente.getVelocidadeAngularX()));
                            labelSpeedY.setText(String.format("%.2f", dadoMaisRecente.getVelocidadeAngularY()));
                            labelSpeedZ.setText(String.format("%.2f", dadoMaisRecente.getVelocidadeAngularZ()));
                            */
                            
                            labelAltitude.setText(String.format("%.2f", dadoMaisRecente.getAltitude()));
                            labelBateria.setText(String.format("%.2f", dadoMaisRecente.getBateria()));
                            labelCorrenteBateria.setText(String.format("%.2f", dadoMaisRecente.getCorrenteBateria()));
                            labelTensaoBateria.setText(String.format("%.2f", dadoMaisRecente.getTensaoBateria()));
                            labelPotenciaBateria.setText(String.format("%.2f", dadoMaisRecente.getCorrenteBateria() * dadoMaisRecente.getTensaoBateria()));

                            labelCorrentePlacaSolar.setText(String.format("%.2f", dadoMaisRecente.getCorrentePlacaSolar()));
                            labelTensaoPlacaSolar.setText(String.format("%.2f", dadoMaisRecente.getTensaoPlacaSolar()));
                            labelPotenciaPlacaSolar.setText(String.format("%.2f", dadoMaisRecente.getCorrentePlacaSolar() * dadoMaisRecente.getTensaoPlacaSolar()));

                            /*
                            labelGas1.setText(String.format("%.2f", dadoMaisRecente.getGas1()));
                            labelGas2.setText(String.format("%.2f", dadoMaisRecente.getGas2()));
                            */
                            
                            labelLuz1.setText(String.format("%.2f", dadoMaisRecente.getLuz1()));
                            labelLuz2.setText(String.format("%.2f", dadoMaisRecente.getLuz2()));
                            labelPontoOrvalho.setText(String.format("%.2f", dadoMaisRecente.getPontoOrvalho()));
                            labelPressao.setText(String.format("%.2f", dadoMaisRecente.getPressao()));
                            labelSensorUV.setText(String.format("%.2f", dadoMaisRecente.getSensorUV()));
                            labelTemperaturaExterna.setText(String.format("%.2f", dadoMaisRecente.getTemperaturaExterna()));
                            labelTemperaturaInterna.setText(String.format("%.2f", dadoMaisRecente.getTemperaturaInterna()));
                            labelUmidade.setText(String.format("%.2f", dadoMaisRecente.getUmidade()));
                            if (prevX != dadoMaisRecente.getAnguloX() && prevY != dadoMaisRecente.getAnguloY() && prevZ != dadoMaisRecente.getAnguloZ()) {
                                model3d.rotateModel(dadoMaisRecente.getAnguloX(), dadoMaisRecente.getAnguloY(), dadoMaisRecente.getAnguloZ());
                                prevX = dadoMaisRecente.getAnguloX();
                                prevY = dadoMaisRecente.getAnguloY();
                                prevZ = dadoMaisRecente.getAnguloZ();
                            }

                            lineChartManager.setyValueTemperaturaInterna(dadoMaisRecente.getTemperaturaInterna());
                             lineChartManager.setyValueTemperaturaExterna(dadoMaisRecente.getTemperaturaExterna());
                            lineChartManager.setyValueAltitude(dadoMaisRecente.getAltitude());
                            lineChartManager.setyValueUmidade(dadoMaisRecente.getUmidade());
                            lineChartManager.setyValuePotenciaBateria(dadoMaisRecente.getCorrenteBateria() * dadoMaisRecente.getTensaoBateria());
                            lineChartManager.setyValuePotenciaPainelSolar(dadoMaisRecente.getCorrentePlacaSolar() * dadoMaisRecente.getTensaoPlacaSolar());
                            lineChartManager.setyValuePressao(dadoMaisRecente.getPressao());
                            lineChartManager.setyValueSensorUV(dadoMaisRecente.getSensorUV());

                        });
                    }
                    Thread.sleep(1000); // Espera 1 segundo antes de buscar novamente
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        });
        updaterThread.setDaemon(true);
        updaterThread.start();
    }
}
