package DAO.PokemonAttack;

import model.Attack;
import model.Pokemon;

import java.sql.SQLException;
import java.util.List;

public interface PokemonAttackDAO {
    public void insere(int pokemonId, int ataqueId) throws SQLException;
    List<Pokemon> buscaPokemonPorAtaques(int ataqueId) throws SQLException;
    List<Attack> buscaAtaquesPorPokemon(int pokemonId) throws SQLException;
    public void excluir(int pokemonId, int ataqueId) throws SQLException;

}
