package controller.Update;

import Main.GerenciadorTelas;
import DAO.Pokemon.PokemonDAO;
import DAO.Pokemon.PokemonDBDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Pokemon;

import java.sql.SQLException;

public class UpdatePokemonController {

    @FXML private TextField txtIdBuscar;
    @FXML private TextField txtNomePokemon;
    @FXML private SplitMenuButton SMBTipoPokemonUm;
    @FXML private SplitMenuButton SMBTipoPokemonDois;

    private final PokemonDAO pokemonDAO;
    private Pokemon pokemonAtual;

    public UpdatePokemonController() {
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
    public void btnBuscarOnAction() {
        String idStr = txtIdBuscar.getText().trim();
        if (idStr.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Informe o ID do pokemon", ButtonType.OK).showAndWait();
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            pokemonAtual = pokemonDAO.consultarPorId(id);

            if (pokemonAtual != null) {
                txtNomePokemon.setText(pokemonAtual.getPokemonName());
                SMBTipoPokemonUm.setText(pokemonAtual.getPokemonType1() != null ? pokemonAtual.getPokemonType1() : "Tipo 1");
                SMBTipoPokemonDois.setText(pokemonAtual.getPokemonType2() != null ? pokemonAtual.getPokemonType2() : "Tipo 2");
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Nenhum pokemon encontrado com o ID " + id, ButtonType.OK).showAndWait();
                limparCampos();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "ID deve ser numérico", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao buscar pokemon: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    public void btnAtualizarOnAction() {
        if (pokemonAtual == null) {
            new Alert(Alert.AlertType.WARNING, "Busque um pokemon válido antes de atualizar", ButtonType.OK).showAndWait();
            return;
        }

        String nome = txtNomePokemon.getText().trim();
        String tipo1 = SMBTipoPokemonUm.getText();
        String tipo2 = SMBTipoPokemonDois.getText();

        if (nome.isEmpty() || tipo1.equals("Tipo 1")) {
            new Alert(Alert.AlertType.WARNING, "Preencha o nome e selecione pelo menos o Tipo 1!", ButtonType.OK).showAndWait();
            return;
        }

        if (tipo2.equals("Tipo 2")) {
            tipo2 = "-";
        }

        try {
            pokemonAtual.setPokemonName(nome);
            pokemonAtual.setPokemonType1(tipo1);
            pokemonAtual.setPokemonType2(tipo2);

            pokemonDAO.atualizar(pokemonAtual);

            new Alert(Alert.AlertType.INFORMATION, "Pokemon atualizado com sucesso", ButtonType.OK).showAndWait();
            limparCampos();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao atualizar pokemon: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    private void limparCampos() {
        txtIdBuscar.clear();
        txtNomePokemon.clear();
        SMBTipoPokemonUm.setText("Tipo 1");
        SMBTipoPokemonDois.setText("Tipo 2");
        pokemonAtual = null;
    }

    @FXML
    public void btnVoltarOnAction() {
        GerenciadorTelas.voltarMenu();
    }
}