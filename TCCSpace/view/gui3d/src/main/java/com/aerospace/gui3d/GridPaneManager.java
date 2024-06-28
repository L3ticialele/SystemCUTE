/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aerospace.gui3d;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GridPaneManager {

    private GridPane gridPane;

    public GridPaneManager(GridPane gridPane) {
        this.gridPane = gridPane;
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
    }

    public void addElement(String labelText, String initialValue, int columnIndex, int rowIndex, int labelWidth, int textFieldWidth, int fontSize) {
        Label label = new Label(labelText);
        label.setAlignment(Pos.CENTER_RIGHT);
        label.setPrefWidth(labelWidth);
        label.setStyle("-fx-font-size: " + fontSize + "px;");

        TextField textField = new TextField(initialValue);
        textField.setEditable(false);
        textField.setAlignment(Pos.CENTER_LEFT);
        textField.setPrefWidth(textFieldWidth);
        textField.setStyle("-fx-font-size: " + fontSize + "px;");

        gridPane.add(label, columnIndex * 2, rowIndex);
        gridPane.add(textField, columnIndex * 2 + 1, rowIndex);
    }
}