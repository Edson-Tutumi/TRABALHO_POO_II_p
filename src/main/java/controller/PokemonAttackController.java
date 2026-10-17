package controller;

import Main.GerenciadorTelas;
import DAO.Pokemon.PokemonDBDAO;
import DAO.Attack.AttackDBDAO;
import DAO.PokemonAttack.PokemonAttackDBDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Pokemon;
import model.Attack;

import java.sql.SQLException;
import java.util.List;

public class PokemonAttackController {

    @FXML private TextField txtIdPokemon;
    @FXML private TextField txtIdAtaque;
    @FXML private TextArea txtAreaResultados;

    private final PokemonDBDAO pokemonDAO = new PokemonDBDAO();
    private final AttackDBDAO attackDAO = new AttackDBDAO();
    private final PokemonAttackDBDAO pokemonAttackDAO = new PokemonAttackDBDAO();

    @FXML
    public void btnVincularOnAction() {
        try {
            int pokemonId = Integer.parseInt(txtIdPokemon.getText().trim());
            int attackId = Integer.parseInt(txtIdAtaque.getText().trim());

            Pokemon p = pokemonDAO.consultarPorId(pokemonId);
            Attack a = attackDAO.consultarPorId(attackId);

            if (p == null || a == null) {
                new Alert(Alert.AlertType.WARNING, "Pokemon ou Ataque não encontrado com os IDs informados", ButtonType.OK).showAndWait();
                return;
            }

            pokemonAttackDAO.insere(pokemonId, attackId);
            new Alert(Alert.AlertType.INFORMATION, "Ataque vinculado ao pokemon com sucesso", ButtonType.OK).showAndWait();
            btnListarAtaquesOnAction();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Os IDs devem ser numéricos inteiros", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao vincular: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    public void btnDesvincularOnAction() {
        try {
            int pokemonId = Integer.parseInt(txtIdPokemon.getText().trim());
            int attackId = Integer.parseInt(txtIdAtaque.getText().trim());

            pokemonAttackDAO.excluir(pokemonId, attackId);
            new Alert(Alert.AlertType.INFORMATION, "Vinculo removido com sucesso", ButtonType.OK).showAndWait();
            btnListarAtaquesOnAction();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Os IDs devem ser numéricos inteiros", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao remover vínculo: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    public void btnListarAtaquesOnAction() {
        try {
            String idStr = txtIdPokemon.getText().trim();
            if (idStr.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Informe o ID do pokemon para listar os ataques", ButtonType.OK).showAndWait();
                return;
            }

            int pokemonId = Integer.parseInt(idStr);
            Pokemon p = pokemonDAO.consultarPorId(pokemonId);
            if (p == null) {
                new Alert(Alert.AlertType.WARNING, "Pokemon não encontrado.", ButtonType.OK).showAndWait();
                return;
            }

            List<Attack> ataques = pokemonAttackDAO.buscaAtaquesPorPokemon(pokemonId);
            StringBuilder sb = new StringBuilder();
            sb.append("Ataques de ").append(p.getPokemonName()).append(":\n\n");

            for (Attack att : ataques) {
                sb.append("- [ID: ").append(att.getAttackId()).append("] ").append(att.getAttackName()).append(" (Tipo: ").append(att.getAttackType()).append(", Poder: ").append(att.getAttackPower()).append(")\n");
            }
            txtAreaResultados.setText(sb.toString());

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "ID do pokemon inválido", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao listar ataques: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    public void btnBuscarPokemonPorAtaqueOnAction() {
        try {
            String idStr = txtIdAtaque.getText().trim();
            if (idStr.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Informe o ID do ataque para buscar os pokemon", ButtonType.OK).showAndWait();
                return;
            }

            int attackId = Integer.parseInt(idStr);
            Attack a = attackDAO.consultarPorId(attackId);
            if (a == null) {
                new Alert(Alert.AlertType.WARNING, "Ataque não encontrado.", ButtonType.OK).showAndWait();
                return;
            }

            List<Pokemon> pokemons = pokemonAttackDAO.buscaPokemonPorAtaques(attackId);
            StringBuilder sb = new StringBuilder();
            sb.append("Pokémon que possuem o ataque '").append(a.getAttackName()).append("':\n\n");

            if (pokemons.isEmpty()) {
                sb.append("Nenhum pokemon encontrado com este ataque.");
            } else {
                for (Pokemon p : pokemons) {
                    sb.append("- [ID: ").append(p.getPokemonId()).append("] ").append(p.getPokemonName()).append(" (Tipos: ").append(p.getPokemonType1()).append("/").append(p.getPokemonType2()).append(")\n");
                }
            }
            txtAreaResultados.setText(sb.toString());

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "ID do ataque inválido", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao buscar Pokémon por ataque: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    public void btnVoltarOnAction() {
        GerenciadorTelas.voltarMenu();
    }
}