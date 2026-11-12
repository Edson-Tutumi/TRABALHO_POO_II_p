package controller.Delete;

import Main.GerenciadorTelas;
import Facade.Facade; //import do FACADE
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Attack;

import java.sql.SQLException;

public class DeleteAttackController {

    @FXML private TextField txtIdAtaque;
    @FXML private Label lblResultado;

    private final Facade attackFacade;

    public DeleteAttackController() {
        this.attackFacade = new Facade();
    }

    @FXML
    public void btnDeletarOnAction() {
        String idStr = txtIdAtaque.getText().trim();
        if (idStr.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Informe o ID do ataque a ser excluído", ButtonType.OK).showAndWait();
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Attack attack = attackFacade.buscarAtaquePorId(id);

            if (attack == null) {
                new Alert(Alert.AlertType.WARNING, "Nenhum ataque encontrado com o ID.", ButtonType.OK).showAndWait();
                limparCampos();
                return;
            }

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Tem certeza que deseja excluir o ataque '" + attack.getAttackName() + "'?",
                    ButtonType.YES, ButtonType.NO);
            confirmAlert.showAndWait();

            if (confirmAlert.getResult() == ButtonType.YES) {
                attackFacade.excluirAtaque(attack);
                new Alert(Alert.AlertType.INFORMATION, "Ataque excluído com sucesso", ButtonType.OK).showAndWait();
                limparCampos();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "O ID deve ser um número inteiro", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao excluir ataque: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    private void limparCampos() {
        txtIdAtaque.clear();
        if (lblResultado != null) {
            lblResultado.setText("");
        }
    }

    @FXML
    public void btnVoltarOnAction() {
        GerenciadorTelas.voltarMenu();
    }
}