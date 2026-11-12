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
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do ataque deve ser um valor maior que zero.");
        }
        return attackDAO.consultarPorId(id);
    }

    public Attack buscarPorNome(String nome) throws SQLException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do ataque para busca não pode ser vazio.");
        }
        return attackDAO.consultarPorNome(nome.trim());
    }

    public List<Attack> buscarPorTipo(String tipo) throws SQLException {
        if (tipo == null) {
            tipo = "";
        }
        return attackDAO.consultarPorTipo(tipo.trim());
    }

    public void inserirAtaque(Attack attack) throws SQLException {
        if (attack.getAttackName() == null || attack.getAttackName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do ataque não pode ser vazio.");
        }

        Attack existente = attackDAO.consultarPorNome(attack.getAttackName());
        if (existente != null) {
            throw new SQLException("Já existe um ataque cadastrado com o nome: " + attack.getAttackName());
        }

        attackDAO.insere(attack);
    }

    public void atualizarAtaque(Attack attack) throws SQLException {
        if (attack.getAttackId() <= 0) {
            throw new IllegalArgumentException("ID do ataque inválido para atualização.");
        }

        if (attack.getAttackName() == null || attack.getAttackName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do ataque não pode ser vazio.");
        }

        Attack existente = attackDAO.consultarPorId(attack.getAttackId());
        if (existente == null) {
            throw new SQLException("Não foi possível atualizar: ataque com ID " + attack.getAttackId() + " não foi encontrado.");
        }

        attackDAO.atualizar(attack);
    }

    public void excluirAtaque(Attack attack) throws SQLException {
        attackDAO.excluir(attack);
    }
}