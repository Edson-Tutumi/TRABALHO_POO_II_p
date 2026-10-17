package DAO.Attack;

import model.Attack;
import java.sql.SQLException;
import java.util.List;

public interface AttackDAO {
    void insere(Attack attack) throws SQLException;
    Attack consultarPorNome(String ataque) throws SQLException;
    Attack consultarPorId(int ataqueId) throws SQLException;
    List<Attack> consultarPorTipo(String tipo) throws SQLException;
    void atualizar(Attack attack) throws SQLException;
    void excluir(Attack attack) throws SQLException;

}