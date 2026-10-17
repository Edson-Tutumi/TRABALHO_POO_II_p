package controller.Update;

import Main.GerenciadorTelas;
import DAO.Attack.AttackDAO;
import DAO.Attack.AttackDBDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Attack;

import java.sql.SQLException;

public class UpdateAttackController {

    @FXML private TextField txtIdBuscar;
    @FXML private TextField txtNomeAtaque;
    @FXML private SplitMenuButton SMBTipoAtaque;
    @FXML private TextField txtPoderAtaque;
    @FXML private TextField txtEfeitoAtaque;
    @FXML private TextField txtAcuraciaAtaque;

    private final AttackDAO attackDAO;
    private Attack ataqueAtual;

    public UpdateAttackController() {
        this.attackDAO = new AttackDBDAO();
    }

    @FXML
    public void initialize() {
        for (MenuItem item : SMBTipoAtaque.getItems()) {
            item.setOnAction(e -> SMBTipoAtaque.setText(item.getText()));
        }
    }

    @FXML
    public void btnBuscarOnAction() {
        String idStr = txtIdBuscar.getText().trim();
        if (idStr.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Informe o ID do ataque", ButtonType.OK).showAndWait();
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            ataqueAtual = attackDAO.consultarPorId(id);

            if (ataqueAtual != null) {
                txtNomeAtaque.setText(ataqueAtual.getAttackName());
                SMBTipoAtaque.setText(ataqueAtual.getAttackType() != null ? ataqueAtual.getAttackType() : "Selecione o tipo");
                txtPoderAtaque.setText(String.valueOf(ataqueAtual.getAttackPower()));
                txtEfeitoAtaque.setText(ataqueAtual.getAttackEffect() != null ? ataqueAtual.getAttackEffect() : "");
                txtAcuraciaAtaque.setText(String.valueOf(ataqueAtual.getAttackAccuracy()));
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Nenhum ataque encontrado com o ID " + id, ButtonType.OK).showAndWait();
                limparCampos();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "ID deve ser numérico", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao buscar ataque: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    public void btnAtualizarOnAction() {
        if (ataqueAtual == null) {
            new Alert(Alert.AlertType.WARNING, "Busque um ataque válido antes de atualizar", ButtonType.OK).showAndWait();
            return;
        }

        String nome = txtNomeAtaque.getText().trim();
        String tipo = SMBTipoAtaque.getText();
        String poderStr = txtPoderAtaque.getText().trim();
        String efeito = txtEfeitoAtaque.getText().trim();
        String acuraciaStr = txtAcuraciaAtaque.getText().trim();

        if (nome.isEmpty() || tipo.equalsIgnoreCase("Selecione o tipo") || poderStr.isEmpty() || acuraciaStr.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Preencha os campos obrigatórios (Nome, Tipo, Poder e Acurácia)", ButtonType.OK).showAndWait();
            return;
        }

        try {
            int poder = Integer.parseInt(poderStr);
            int acuracia = Integer.parseInt(acuraciaStr);

            ataqueAtual.setAttackName(nome);
            ataqueAtual.setAttackType(tipo);
            ataqueAtual.setAttackPower(poder);
            ataqueAtual.setAttackAccuracy(acuracia);

            if (efeito.isEmpty()) {
                ataqueAtual.setAttackEffect(null);
            } else {
                ataqueAtual.setAttackEffect(efeito);
            }

            attackDAO.atualizar(ataqueAtual);

            new Alert(Alert.AlertType.INFORMATION, "Ataque atualizado com sucesso", ButtonType.OK).showAndWait();
            limparCampos();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Poder e Acurácia devem ser números inteiros válidos", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao atualizar ataque: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    private void limparCampos() {
        txtIdBuscar.clear();
        txtNomeAtaque.clear();
        SMBTipoAtaque.setText("Selecione o tipo");
        txtPoderAtaque.clear();
        txtEfeitoAtaque.clear();
        txtAcuraciaAtaque.clear();
        ataqueAtual = null;
    }

    @FXML
    public void btnVoltarOnAction() {
        GerenciadorTelas.voltarMenu();
    }
}