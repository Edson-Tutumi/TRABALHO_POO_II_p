package controller.Insert;

import Main.GerenciadorTelas;
import DAO.Pokemon.PokemonDAO;
import DAO.Pokemon.PokemonDBDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Pokemon;

import java.sql.SQLException;

public class InsertPokemonController {

    @FXML private TextField txtNomePokemon;
    @FXML private SplitMenuButton SMBTipoPokemonUm;
    @FXML private SplitMenuButton SMBTipoPokemonDois;

    private final PokemonDAO pokemonDAO;

    public InsertPokemonController() {
        this.pokemonDAO = new PokemonDBDAO();
    }

    @FXML
    public void initialize() {
        for (MenuItem item : SMBTipoPokemonUm.getItems()) {
            item.setOnAction(e -> SMBTipoPokemonUm.setText(item.getText()));
        }
        for (MenuItem item : SMBTipoPokemonDois.getItems()) {
            item.setOnAction(e -> SMBTipoPokemonDois.setText(item.getText()));
        }
    }

    @FXML
    public void btnInserirPokemonOnAction() {
        String nome = txtNomePokemon.getText().trim();
        String tipo1 = SMBTipoPokemonUm.getText();
        String tipo2 = SMBTipoPokemonDois.getText();

        if (nome.isEmpty() || tipo1.equals("Tipo 1")) {
            new Alert(Alert.AlertType.WARNING, "Informe pelo menos o nome e o tipo 1", ButtonType.OK).showAndWait();
            return;
        }

        if (tipo2.equals("Tipo 2")) {
            tipo2 = "-";
        }

        try {
            Pokemon pokemon = new Pokemon();
            pokemon.setPokemonName(nome);
            pokemon.setPokemonType1(tipo1);
            pokemon.setPokemonType2(tipo2);

            pokemonDAO.insere(pokemon);

            new Alert(Alert.AlertType.INFORMATION, "Pokemon inserido com sucesso", ButtonType.OK).showAndWait();
            limparCampos();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao salvar pokemon: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    private void limparCampos() {
        txtNomePokemon.clear();
        SMBTipoPokemonUm.setText("Tipo 1");
        SMBTipoPokemonDois.setText("Tipo 2");
    }

    @FXML
    public void btnVoltarOnAction() {
        GerenciadorTelas.voltarMenu();
    }
}