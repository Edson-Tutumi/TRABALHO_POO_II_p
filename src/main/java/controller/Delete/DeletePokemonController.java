package controller.Delete;

import Main.GerenciadorTelas;
import DAO.Pokemon.PokemonDAO;
import DAO.Pokemon.PokemonDBDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Pokemon;

import java.sql.SQLException;

public class DeletePokemonController {

    @FXML private TextField txtIdPokemon;

    private final PokemonDAO pokemonDAO;

    public DeletePokemonController() {
        this.pokemonDAO = new PokemonDBDAO();
    }

    @FXML
    public void btnDeletarOnAction() {
        String idStr = txtIdPokemon.getText().trim();
        if (idStr.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Informe o ID do pokemon a ser excluído", ButtonType.OK).showAndWait();
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Pokemon pokemon = pokemonDAO.consultarPorId(id);

            if (pokemon == null) {
                new Alert(Alert.AlertType.WARNING, "Nenhum pokemon encontrado com o ID informado.", ButtonType.OK).showAndWait();
                limparCampos();
                return;
            }

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Tem certeza que deseja excluir o pokemon '" + pokemon.getPokemonName() + "'?", ButtonType.YES, ButtonType.NO);
            confirmAlert.showAndWait();

            if (confirmAlert.getResult() == ButtonType.YES) {
                pokemonDAO.excluir(pokemon);
                new Alert(Alert.AlertType.INFORMATION, "Pokemon excluído com sucesso", ButtonType.OK).showAndWait();
                limparCampos();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "O ID deve ser um número inteiro", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao excluir pokemon: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    private void limparCampos() {
        txtIdPokemon.clear();
    }

    @FXML
    public void btnVoltarOnAction() {
        GerenciadorTelas.voltarMenu();
    }
}