package Facade;

import Service.AttackService;
import model.Attack;
import java.util.List;
import java.sql.SQLException;

public class AttackFacade {
    private Service.AttackService attackService;

    public AttackFacade() {
        this.attackService = new AttackService();
    }

    public List<Attack> listarTodosAtaques() {
        try {
            return attackService.listarTodosAtaques();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Attack buscarPorId(int id) throws SQLException {
        return attackService.buscarPorId(id);
    }

    public Attack buscarPorNome(String nome) throws SQLException {
        return attackService.buscarPorNome(nome);
    }

    public List<Attack> buscarPorTipo(String tipo) throws SQLException {
        return attackService.buscarPorTipo(tipo);
    }

    public void inserirAtaque(Attack attack) throws SQLException {
        attackService.inserirAtaque(attack);
    }

    public void atualizarAtaque(Attack attack) throws SQLException {
        attackService.atualizarAtaque(attack);
    }

    public void excluirAtaque(Attack attack) throws SQLException {
        attackService.excluirAtaque(attack);
    }
}