package controller.Search;

import Main.GerenciadorTelas;
import DAO.Pokemon.PokemonDAO;
import DAO.Pokemon.PokemonDBDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Pokemon;

import java.sql.SQLException;
import java.util.List;

public class SearchPokemonController {

    @FXML private TextField txtfNamePokemon;
    @FXML private TextField txtfIdPokemon;
    @FXML private TextField txtfTipoPokemon;

    private final PokemonDAO pokemonDAO;

    public SearchPokemonController() {
        this.pokemonDAO = new PokemonDBDAO();
    }

    @FXML
    public void btnBuscarPokemonAction() {
        String nome = txtfNamePokemon.getText().trim();
        if (nome.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Informe o Nome do Pokemon", ButtonType.OK).showAndWait();
            return;
        }

        try {
            Pokemon p = pokemonDAO.consultarPorNome(nome);
            if (p != null) {
                new Alert(Alert.AlertType.INFORMATION, p.toString(), ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Pokemon não encontrado.", ButtonType.OK).showAndWait();
            }
            limparCampos();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro na consulta: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    public void btnBuscarIdPokemonAction() {
        String idStr = txtfIdPokemon.getText().trim();
        if (idStr.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Informe o ID do pokemon", ButtonType.OK).showAndWait();
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Pokemon p = pokemonDAO.consultarPorId(id);
            if (p != null) {
                new Alert(Alert.AlertType.INFORMATION, p.toString(), ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Pokemon não encontrado.", ButtonType.OK).showAndWait();
            }
            limparCampos();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "ID deve ser numérico", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro na consulta: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    public void btnBuscarTipoPokemonUmAction() {
        String tipo = txtfTipoPokemon.getText().trim();
        if (tipo.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Informe o tipo do pokemon", ButtonType.OK).showAndWait();
            return;
        }

        try {
            List<Pokemon> pokemons = pokemonDAO.consultarPorTipos(tipo, null);
            if (!pokemons.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Pokemons encontrados (").append(pokemons.size()).append("):\n\n");
                for (Pokemon p : pokemons) {
                    sb.append(p.getPokemonName()).append(" - [").append(p.getPokemonType1()).append("/").append(p.getPokemonType2()).append("]\n");
                }
                new Alert(Alert.AlertType.INFORMATION, sb.toString(), ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Nenhum pokemon encontrado com o tipo '" + tipo + "'", ButtonType.OK).showAndWait();
            }
            limparCampos();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro na consulta ao banco: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    private void limparCampos() {
        txtfNamePokemon.clear();
        txtfIdPokemon.clear();
        txtfTipoPokemon.clear();
    }

    @FXML
    public void btnVoltarOnAction() {
        GerenciadorTelas.voltarMenu();
    }
}