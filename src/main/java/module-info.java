module org.example.trabalho_poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens controller to javafx.fxml;
    opens Main to javafx.graphics, javafx.fxml;
    opens model to javafx.base;

    exports Main;
    exports controller;
    exports model;
    exports DAO;
    exports controller.Insert;
    opens controller.Insert to javafx.fxml;
    exports controller.Update;
    opens controller.Update to javafx.fxml;
    exports controller.Delete;
    opens controller.Delete to javafx.fxml;
    exports controller.Search;
    opens controller.Search to javafx.fxml;
    exports DAO.Attack;
    exports DAO.PokemonAttack;
    exports DAO.Pokemon;
}