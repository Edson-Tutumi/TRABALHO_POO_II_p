package DAO.PokemonAttack;

import DAO.Conexao;
import model.Attack;
import model.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonAttackDBDAO implements PokemonAttackDAO {

    @Override
    public void insere(int pokemonId, int ataqueId) throws SQLException {
        String sql = "INSERT INTO pokemon_attack (pokemonId, attackId) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pokemonId);
            stmt.setInt(2, ataqueId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int pokemonId, int ataqueId) throws SQLException {
        String sql = "DELETE FROM pokemon_attack WHERE pokemonId = ? AND attackId = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pokemonId);
            stmt.setInt(2, ataqueId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Pokemon> buscaPokemonPorAtaques(int ataqueId) throws SQLException {
        List<Pokemon> lista = new ArrayList<>();
        String sql = "SELECT p.* FROM pokemon p JOIN pokemon_attack pa ON p.pokemonId = pa.pokemonId WHERE pa.attackId = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ataqueId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pokemon p = new Pokemon();
                    p.setPokemonId(rs.getInt("pokemonId"));
                    p.setPokemonName(rs.getString("pokemonName"));
                    p.setPokemonType1(rs.getString("pokemonType1"));
                    p.setPokemonType2(rs.getString("pokemonType2"));
                    lista.add(p);
                }
            }
        }
        return lista;
    }

    @Override
    public List<Attack> buscaAtaquesPorPokemon(int pokemonId) throws SQLException {
        List<Attack> lista = new ArrayList<>();
        String sql = "SELECT a.* FROM attack a JOIN pokemon_attack pa ON a.attackId = pa.attackId WHERE pa.pokemonId = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pokemonId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Attack att = new Attack();
                    att.setAttackId(rs.getInt("attackId"));
                    att.setAttackName(rs.getString("attackName"));
                    att.setAttackType(rs.getString("attackType"));
                    att.setAttackPower(rs.getInt("attackPower"));
                    lista.add(att);
                }
            }
        }
        return lista;
    }
}