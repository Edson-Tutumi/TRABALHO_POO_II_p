package DAO.Pokemon;

import model.Pokemon;

import java.sql.SQLException;
import java.util.List;

public interface PokemonDAO {
    public void insere(Pokemon pokemon) throws SQLException;
    public Pokemon consultarPorNome (String nome) throws SQLException;
    public Pokemon consultarPorId (int pokemonId) throws SQLException;
    public List<Pokemon> consultarPorTipos(String tipo1, String tipo2) throws SQLException;
    public void atualizar (Pokemon pokemon) throws SQLException;
    public void excluir (Pokemon pokemon) throws SQLException;
}
