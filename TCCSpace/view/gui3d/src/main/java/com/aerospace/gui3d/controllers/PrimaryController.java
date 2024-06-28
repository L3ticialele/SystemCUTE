package com.aerospace.gui3d.controllers;

import com.aerospace.gui3d.App;
import com.aerospace.gui3d.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("Data3DViewer");
    }
}
