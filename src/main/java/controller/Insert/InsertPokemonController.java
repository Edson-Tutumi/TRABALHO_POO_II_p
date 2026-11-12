package controller.Insert;

import Main.GerenciadorTelas;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Pokemon;
import model.PokemonBuilder;
import Facade.Facade;

import java.sql.SQLException;

public class InsertPokemonController {

    @FXML private TextField txtNomePokemon;
    @FXML private TextField txtIdAtaque;
    @FXML private SplitMenuButton SMBTipoPokemonUm;
    @FXML private SplitMenuButton SMBTipoPokemonDois;

    private Facade facade = new Facade();

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

        if (nome.isEmpty() || tipo1.equals("Tipo 1") || tipo1.equals("Type 1")) {
            new Alert(Alert.AlertType.WARNING, "Informe pelo menos o nome e o tipo 1", ButtonType.OK).showAndWait();
            return;
        }

        if (tipo2.equals("Tipo 2") || tipo2.equals("Type 2")) {
            tipo2 = "-";
        }

        try {
            Pokemon pokemon = new PokemonBuilder()
                    .setNome(nome)
                    .setTipo1(tipo1)
                    .setTipo2(tipo2)
                    .build();

            facade.inserirPokemon(pokemon);

            new Alert(Alert.AlertType.INFORMATION, "Pokemon inserido com sucesso", ButtonType.OK).showAndWait();
            limparCampos();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao salvar pokemon: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    //========== INSERE COM ATAQUE PARA JUSTIFICAR O

    @FXML
    public void btnInserirComAtaque() {
        String nome = txtNomePokemon.getText().trim();
        String tipo1 = SMBTipoPokemonUm.getText();
        String tipo2 = SMBTipoPokemonDois.getText();
        String idAtaqueStr = txtIdAtaque != null ? txtIdAtaque.getText().trim() : "";

        if (nome.isEmpty() || tipo1.equals("Tipo 1") || tipo1.equals("Type 1") || idAtaqueStr.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Preencha o nome, o tipo 1 e o ID do ataque!", ButtonType.OK).showAndWait();
            return;
        }

        if (tipo2.equals("Tipo 2") || tipo2.equals("Type 2")) {
            tipo2 = "-";
        }

        try {
            int attackId = Integer.parseInt(idAtaqueStr);

            Pokemon novoPokemon = new PokemonBuilder()
                    .setNome(nome)
                    .setTipo1(tipo1)
                    .setTipo2(tipo2)
                    .build();

            facade.cadastrarPokemonComAtaque(novoPokemon, attackId);

            new Alert(Alert.AlertType.INFORMATION, "Pokémon cadastrado e vinculado ao ataque com sucesso!", ButtonType.OK).showAndWait();
            limparCampos();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "O ID do ataque deve ser um número inteiro.", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao cadastrar: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    private void limparCampos() {
        txtNomePokemon.clear();
        if (txtIdAtaque != null) {
            txtIdAtaque.clear();
        }
        SMBTipoPokemonUm.setText("Type 1");
        SMBTipoPokemonDois.setText("Type 2");
    }

    @FXML
    public void btnVoltarOnAction() {
        GerenciadorTelas.voltarMenu();
    }
}