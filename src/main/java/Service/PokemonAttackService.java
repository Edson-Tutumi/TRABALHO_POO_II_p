package Service;

import DAO.PokemonAttack.PokemonAttackDBDAO;
import model.Pokemon;
import model.Attack;

import java.sql.SQLException;
import java.util.List;

public class PokemonAttackService {
    private PokemonAttackDBDAO pokemonAttackDAO;

    public PokemonAttackService() {
        this.pokemonAttackDAO = new PokemonAttackDBDAO();
    }

    public void vincularAtaqueAoPokemon(int pokemonId, int attackId) throws SQLException {
        pokemonAttackDAO.insere(pokemonId, attackId);
    }

    public void desvincularAtaqueDoPokemon(int pokemonId, int attackId) throws SQLException {
        pokemonAttackDAO.excluir(pokemonId, attackId);
    }

    public List<Attack> listarAtaquesPorPokemon(int pokemonId) throws SQLException {
        return pokemonAttackDAO.buscaAtaquesPorPokemon(pokemonId);
    }

    public List<Pokemon> listarPokemonsPorAtaque(int attackId) throws SQLException {
        return pokemonAttackDAO.buscaPokemonPorAtaques(attackId);
    }
}