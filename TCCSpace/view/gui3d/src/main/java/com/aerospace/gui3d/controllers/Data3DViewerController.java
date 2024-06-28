package com.aerospace.gui3d.controllers;

import com.aerospace.gui3d.*;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.MeshView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Data3DViewerController {

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private MenuBar menuBarTrocarGrafico;

    @FXML
    private MenuItem menuItemAltitude;

    @FXML
    private MenuItem menuItemTemperatura;

    @FXML
    private MeshView meshView3D;


    @FXML
    private AnchorPane anchorPane3d;

    private Model3D model3D;

    private LineChartManager lineChartManager;


    public void initialize() {
        lineChartManager = new LineChartManager(lineChart);
        model3D = new Model3D();
        MeshView meshView = model3D.getMeshView();
        anchorPane3d.getChildren().add(meshView);
        meshView.setScaleX(2);
        meshView.setScaleY(2);
        meshView.setScaleZ(2);
        meshView.setTranslateX(210);
        meshView.setTranslateY(150);
        meshView.setTranslateZ(0);
        model3D.rotateModel(120, 0, 35);
        anchorPane3d.requestFocus();
        anchorPane3d.setOnKeyPressed(event -> model3D.handleKeyPressed(event));

    }
}
