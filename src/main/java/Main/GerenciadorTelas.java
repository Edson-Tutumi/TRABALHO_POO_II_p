package Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GerenciadorTelas {

    private static Stage stage;

    public static void setStage(Stage stagePrincipal) {
        stage = stagePrincipal;
    }

    public static void changeScreen(String arquivoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(GerenciadorTelas.class.getResource(arquivoFXML));
            Scene scene = new Scene(loader.load(), 600, 500);
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela: " + arquivoFXML);
            e.printStackTrace();
        }
    }

    public static void voltarMenu() {
        changeScreen("/org/example/trabalho_poo/views/Menu.fxml", "Pokemon Team Builder - Menu");
    }
}