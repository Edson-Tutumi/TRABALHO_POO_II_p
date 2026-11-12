package controller.Insert;

import Main.GerenciadorTelas;
import Facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Attack;
import model.AttackBuilder;

import java.sql.SQLException;

public class InsertAttackController {

    @FXML private TextField txtNomeAtaque;
    @FXML private TextField txtPoderAtaque;
    @FXML private TextField txtEfeitoAtaque;
    @FXML private TextField txtAcuraciaAtaque;
    @FXML private SplitMenuButton SMBTipoAtaque;

    private final Facade attackFacade;

    public InsertAttackController() {
        this.attackFacade = new Facade();
    }

    @FXML
    public void initialize() {
        for (MenuItem item : SMBTipoAtaque.getItems()) {
            item.setOnAction(e -> SMBTipoAtaque.setText(item.getText()));
        }
    }

    @FXML
    public void btnInserirAtaqueOnAction() {
        String nome = txtNomeAtaque.getText().trim();
        String tipo = SMBTipoAtaque.getText();
        String poderStr = txtPoderAtaque.getText().trim();
        String efeito = txtEfeitoAtaque.getText().trim();
        String acuraciaStr = txtAcuraciaAtaque.getText().trim();

        if (nome.isEmpty() || tipo.equals("Selecione o Tipo") || poderStr.isEmpty() || acuraciaStr.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Preencha todos os campos e selecione o tipo do ataque", ButtonType.OK).showAndWait();
            return;
        }

        try {
            int poder = Integer.parseInt(poderStr);
            int acuracia = Integer.parseInt(acuraciaStr);
            String efeitoFinal = efeito.isEmpty() ? null : efeito;

            // Utilizando o AttackBuilder para construir o objeto de forma limpa e fluida
            Attack attack = new AttackBuilder()
                    .setAttackName(nome)
                    .setAttackPower(poder)
                    .setAttackAccuracy(acuracia)
                    .setAttackType(tipo)
                    .setAttackEffect(efeitoFinal)
                    .build();

            attackFacade.inserirAtaque(attack);

            new Alert(Alert.AlertType.INFORMATION, "Ataque inserido com sucesso", ButtonType.OK).showAndWait();
            limparCampos();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Os campos de poder e acurácia devem ser números válidos", ButtonType.OK).showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro no banco: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    private void limparCampos() {
        txtNomeAtaque.clear();
        txtPoderAtaque.clear();
        txtEfeitoAtaque.clear();
        txtAcuraciaAtaque.clear();
        SMBTipoAtaque.setText("Selecione o Tipo");
    }

    @FXML
    public void btnVoltarOnAction() {
        GerenciadorTelas.voltarMenu();
    }
}