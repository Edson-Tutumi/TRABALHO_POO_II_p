package DAO.Pokemon;

import DAO.Conexao;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDBDAO implements PokemonDAO {

    @Override
    public void insere(Pokemon pokemon) throws SQLException {
        String sql = "INSERT INTO pokemon (pokemonName, pokemonType1, pokemonType2) VALUES (?, ?, ?) RETURNING pokemonId";

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {

            statement.setString(1, pokemon.getPokemonName());
            statement.setString(2, pokemon.getPokemonType1());
            statement.setString(3, pokemon.getPokemonType2());

            if (pokemon.getPokemonType2() == null) {
                statement.setString(3, "-");
            } else {
                statement.setString(3, pokemon.getPokemonType2());
            }

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    pokemon.setPokemonId(rs.getInt("pokemonId"));
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.OK).showAndWait();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Erro de conexão com o banco, NÃO foi possível inserir o pokemon: " + e.getMessage(), ButtonType.OK).showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Pokemon pokemon) throws SQLException {
        String sql = "UPDATE pokemon SET pokemonName = ?, pokemonType1 = ?, pokemonType2 = ? WHERE pokemonId = ?";
        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setString(1, pokemon.getPokemonName());
            statement.setString(2, pokemon.getPokemonType1());

            if (pokemon.getPokemonType2() == null) {
                statement.setString(3, "-");
            } else {
                statement.setString(3, pokemon.getPokemonType2());
            }

            statement.setInt(4, pokemon.getPokemonId());
            statement.executeUpdate();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Erro ao atualizar o pokemon: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @Override
    public void excluir(Pokemon pokemon) throws SQLException {
        String sql = "DELETE FROM pokemon WHERE pokemonId = ?";

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setInt(1, pokemon.getPokemonId());
            statement.executeUpdate();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Erro ao excluir o pokemon: " + e.getMessage(), ButtonType.OK).showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public Pokemon consultarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM pokemon WHERE pokemonName = ?";

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setString(1, nome);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapearPokemon(rs);
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Erro ao consultar pokemon por nome: " + e.getMessage(), ButtonType.OK).showAndWait();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pokemon> consultarPorTipos(String tipo1, String tipo2) throws SQLException {
        List<Pokemon> lista = new ArrayList<>();

        String sql = "SELECT * FROM pokemon WHERE pokemonType1 ILIKE ? OR pokemonType2 ILIKE ? ORDER BY pokemonName";

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            String buscaTipo = "%" + tipo1 + "%";
            statement.setString(1, buscaTipo);
            statement.setString(2, buscaTipo);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPokemon(rs));
                }
            }
        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Erro ao consultar pokemon por tipo: " + e.getMessage(), ButtonType.OK).showAndWait();
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Pokemon consultarPorId(int pokemonId) throws SQLException {
        String sql = "SELECT * FROM pokemon WHERE pokemonId = ?";

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setInt(1, pokemonId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapearPokemon(rs);
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Erro ao consultar pokemon por ID: " + e.getMessage(), ButtonType.OK).showAndWait();
            e.printStackTrace();
        }
        return null;
    }

    private Pokemon mapearPokemon(ResultSet rs) throws SQLException {
        Pokemon p = new Pokemon();
        p.setPokemonId(rs.getInt("pokemonId"));
        p.setPokemonName(rs.getString("pokemonName"));
        p.setPokemonType1(rs.getString("pokemonType1"));
        p.setPokemonType2(rs.getString("pokemonType2"));
        return p;
    }
}