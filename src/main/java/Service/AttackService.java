package Service;

import DAO.Attack.AttackDAO;
import DAO.Attack.AttackDBDAO;
import model.Attack;
import java.sql.SQLException;
import java.util.List;

public class AttackService {
    private AttackDAO attackDAO;

    public AttackService() {
        this.attackDAO = new AttackDBDAO();
    }

    public List<Attack> listarTodosAtaques() throws SQLException {
        return attackDAO.consultarPorTipo("");
    }

    public Attack buscarPorId(int id) throws SQLException {
        return attackDAO.consultarPorId(id);
    }

    public Attack buscarPorNome(String nome) throws SQLException {
        return attackDAO.consultarPorNome(nome);
    }

    public List<Attack> buscarPorTipo(String tipo) throws SQLException {
        return attackDAO.consultarPorTipo(tipo);
    }

    public void inserirAtaque(Attack attack) throws SQLException {
        if (attack.getAttackName() == null || attack.getAttackName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do ataque não pode ser vazio.");
        }
        attackDAO.insere(attack);
    }

    public void atualizarAtaque(Attack attack) throws SQLException {
        attackDAO.atualizar(attack);
    }

    public void excluirAtaque(Attack attack) throws SQLException {
        attackDAO.excluir(attack);
    }
}