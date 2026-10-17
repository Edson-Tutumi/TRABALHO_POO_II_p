package Main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
            public void start(Stage primaryStage) {
            GerenciadorTelas.setStage(primaryStage);
            GerenciadorTelas.changeScreen("/org/example/trabalho_poo/views/Menu.fxml", "Pokemon Team Builder");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
