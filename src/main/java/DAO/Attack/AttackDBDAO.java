package DAO.Attack;

import DAO.Conexao;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Attack;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttackDBDAO implements AttackDAO {

    @Override
    public void insere(Attack attack) throws SQLException {
        String sql = "INSERT INTO attack (attackname, attackpower, attackeffect, attackaccuracy, attacktype) VALUES (?, ?, ?, ?, ?) RETURNING attackid";

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setString(1, attack.getAttackName());
            statement.setInt(2, attack.getAttackPower());
            statement.setString(3, attack.getAttackEffect());
            statement.setInt(4, attack.getAttackAccuracy());
            statement.setString(5, attack.getAttackType());

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    attack.setAttackId(rs.getInt("attackid"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void atualizar(Attack attack) throws SQLException {
        String sql = "UPDATE attack SET attackname = ?, attackpower = ?, attackeffect = ?, attackaccuracy = ?, attacktype = ? WHERE attackid = ?";

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setString(1, attack.getAttackName());
            statement.setInt(2, attack.getAttackPower());
            statement.setString(3, attack.getAttackEffect());
            statement.setInt(4, attack.getAttackAccuracy());
            statement.setString(5, attack.getAttackType());
            statement.setInt(6, attack.getAttackId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void excluir(Attack attack) throws SQLException {
        String sql = "DELETE FROM attack WHERE attackid = ?";

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setInt(1, attack.getAttackId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Attack consultarPorNome(String ataque) throws SQLException {
        String sql = "SELECT * FROM attack WHERE attackname = ?";

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setString(1, ataque);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapearAttack(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Attack consultarPorId(int ataqueId) throws SQLException {
        String sql = "SELECT * FROM attack WHERE attackid = ?";

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setInt(1, ataqueId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapearAttack(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Attack> consultarPorTipo(String tipo) throws SQLException {
        List<Attack> lista = new ArrayList<>();
        String sql = "SELECT * FROM attack WHERE attacktype ILIKE ? ORDER BY attackname";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, "%" + tipo + "%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearAttack(rs));
                }
            }
        }
        return lista;
    }

    private Attack mapearAttack(ResultSet rs) throws SQLException {
        Attack a = new Attack();
        a.setAttackId(rs.getInt("attackid"));
        a.setAttackName(rs.getString("attackname"));
        a.setAttackPower(rs.getInt("attackpower"));
        a.setAttackType(rs.getString("attacktype"));
        a.setAttackAccuracy(rs.getInt("attackaccuracy"));
        a.setAttackEffect(rs.getString("attackeffect"));
        return a;
    }
}