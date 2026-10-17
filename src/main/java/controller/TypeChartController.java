package controller;

import Main.GerenciadorTelas;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class TypeChartController {

    @FXML
    private ImageView imgTypeChart;

    @FXML
    public void btnVoltarOnAction() {
        GerenciadorTelas.voltarMenu();
    }
}