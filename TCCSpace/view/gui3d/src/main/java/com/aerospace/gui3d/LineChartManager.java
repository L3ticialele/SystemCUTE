package com.aerospace.gui3d;
import javafx.animation.AnimationTimer;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.chart.CategoryAxis;

import java.util.Random;

public class LineChartManager {

    private LineChart<String, Number> lineChart;
    private XYChart.Series<String, Number> series;
    private Random random;

    private long startTimeMs; // Tempo inicial em milissegundos

    public LineChartManager(LineChart<String, Number> lineChart) {
        this.lineChart = lineChart;
        this.random = new Random();
        this.startTimeMs = System.currentTimeMillis();
        initializeChart();
        setupDataUpdate();
        setDefaultColors();
        customizeChart();
    }

    private void initializeChart() {
        // Configurar eixos
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Tempo");
        yAxis.setLabel("Valor");

        // Definir valores mínimos e máximos nos eixos
        yAxis.setAutoRanging(false); // Desativar o ajuste automático do eixo Y
        yAxis.setLowerBound(0); // Valor mínimo do eixo Y
        yAxis.setUpperBound(100); // Valor máximo do eixo Y

        // Criar o gráfico de linha
        lineChart.setTitle("Temperatura");
        lineChart.setCreateSymbols(false); // Não mostrar símbolos nos pontos
        lineChart.setLegendVisible(false); // Ocultar legenda
        lineChart.getData().clear(); // Limpar dados existentes

        series = new XYChart.Series<>();
        lineChart.getData().add(series);
    }

    private void setupDataUpdate() {
        // Usar AnimationTimer para atualizar os dados a cada segundo
        new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                long elapsedMillis = System.currentTimeMillis() - startTimeMs;
                if (elapsedMillis - lastUpdate >= 1000) { // 1 segundo em milissegundos
                    addRandomData(elapsedMillis);
                    lastUpdate = elapsedMillis;
                }
            }
        }.start();
    }

    private void addRandomData(long elapsedMillis) {
        // Gerar dados aleatórios
        double yValue = random.nextDouble() * 100; // Valor aleatório entre 0 e 100
        series.getData().add(new XYChart.Data<>(String.valueOf(elapsedMillis / 1000), yValue));

        // Limitar a quantidade de pontos no gráfico para manter a performance
        if (series.getData().size() > 20) {
            series.getData().remove(0);
        }
    }

    public void setChartBackground(int red, int green, int blue) {
        // Definir cor de fundo do gráfico
        String style = String.format("-fx-background-color: rgb(%d, %d, %d);", red, green, blue);
        lineChart.setStyle(style);
    }

    public void setLineColor(int red, int green, int blue) {
        // Definir cor da linha do gráfico
        String style = String.format("-fx-stroke: rgb(%d, %d, %d);", red, green, blue);
        series.getNode().setStyle(style); // Aplicar a cor à linha
    }

    public void setTextColor(int red, int green, int blue) {
        // Definir cor do texto do gráfico
        String style = String.format("-fx-text-fill: rgb(%d, %d, %d);", red, green, blue);
        for (XYChart.Data<String, Number> data : series.getData()) {
            data.getNode().lookup(".chart-line-symbol").setStyle(style); // Aplicar a cor ao texto
        }
    }

    public void setChartStyleToWhite() {
        // Definir cor branca para o título e os rótulos dos eixos
        setChartTitleStyle("-fx-text-fill: white;");
        setAxisLabelStyle("-fx-tick-label-fill: white;");
    }

    private void setChartTitleStyle(String style) {
        // Definir estilo do título do gráfico
        Label titleLabel = (Label) lineChart.lookup(".chart-title");
        if (titleLabel != null) {
            titleLabel.setStyle(style);
        }
    }

    private void setAxisLabelStyle(String style) {
        // Definir estilo dos rótulos dos eixos X e Y
        CategoryAxis xAxis = (CategoryAxis) lineChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        xAxis.setStyle(style);
        yAxis.setStyle(style);
    }

    private void setDefaultColors() {
        // Cores padrão
        setChartBackground(0, 0, 0); // Branco
        setLineColor(255,255,255); // Red
        setTextColor(255, 255, 255); //
        setChartStyleToWhite();
    }

    private void customizeChart() {
        // Definir borda arredondada
        Region chartRegion = (Region) lineChart.lookup(".chart-plot-background");
        chartRegion.setStyle("-fx-background-radius: 20px;");

        // Modificar o background da área de plotagem (dados do gráfico)
        Region plotBackground = (Region) lineChart.lookup(".chart-plot-background");
        plotBackground.setStyle("-fx-background-color: rgba(0, 0, 0, 1);"); // Cor de fundo com transparência

        // Modificar o background da área de tabulação (eixos, legendas)
    }
}
