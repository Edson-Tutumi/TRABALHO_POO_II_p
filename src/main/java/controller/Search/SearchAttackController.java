package controller.Search;

import Main.GerenciadorTelas;
import DAO.Attack.AttackDAO;
import DAO.Attack.AttackDBDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Attack;

import java.sql.SQLException;
import java.util.List;

public class SearchAttackController {

    @FXML private TextField txtNomeAtaque;
    @FXML private TextField txtIdAtaque;
    @FXML private TextField txtTipoAtaque;

    private final AttackDAO attackDAO;

    public SearchAttackController() {
        this.attackDAO = new AttackDBDAO();
    }

    @FXML
    public void btnBuscarAtaqueAction() {
        String nome = txtNomeAtaque.getText().trim();
        if (nome.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Informe o nome do ataque", ButtonType.OK).showAndWait();
            return;
        }

        try {
            Attack attack = attackDAO.consultarPorNome(nome);
            if (attack != null) {
                new Alert(Alert.AlertType.INFORMATION, attack.toString(), ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Nenhum ataque encontrado com esse nome.", ButtonType.OK).showAndWait();
            }
            limparCampos();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao consultar: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    public void btnBuscarIdAtaqueAction() {
        String idStr = txtIdAtaque.getText().trim();
        if (idStr.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Informe o ID do ataque", ButtonType.OK).showAndWait();
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Attack attack = attackDAO.consultarPorId(id);
            if (attack != null) {
                new Alert(Alert.AlertType.INFORMATION, attack.toString(), ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Nenhum ataque encontrado com o ID.", ButtonType.OK).showAndWait();
            }
            limparCampos();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "O ID deve ser um número inteiro", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao consultar: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    public void btnBuscarTipoAtaqueAction() {
        String tipo = txtTipoAtaque.getText().trim();
        if (tipo.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Informe o tipo do ataque!", ButtonType.OK).showAndWait();
            return;
        }

        try {
            List<Attack> ataques = attackDAO.consultarPorTipo(tipo);
            if (!ataques.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Ataques encontrados ").append(ataques.size()).append(":\n\n");
                for (Attack a : ataques) {
                    sb.append("- ").append(a.getAttackName()).append(" Tipo: ").append(a.getAttackType()).append(", Poder: ").append(a.getAttackPower()).append("\n");
                }
                new Alert(Alert.AlertType.INFORMATION, sb.toString(), ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Nenhum ataque encontrado para o tipo '" + tipo + "'", ButtonType.OK).showAndWait();
            }
            limparCampos();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao consultar por tipo: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    private void limparCampos() {
        txtNomeAtaque.clear();
        txtIdAtaque.clear();
        txtTipoAtaque.clear();
    }

    @FXML
    public void btnVoltarOnAction() {
        GerenciadorTelas.voltarMenu();
    }
}