package Facade;

import Service.PokemonService;
import DAO.Attack.AttackDAO;
import DAO.Attack.AttackDBDAO;
import DAO.PokemonAttack.PokemonAttackDBDAO;
import model.Pokemon;
import model.Attack;
import java.util.List;
import java.sql.SQLException;

public class PokemonFacade {
    private PokemonService pokemonService;
    private AttackDAO attackDAO;
    private PokemonAttackDBDAO pokemonAttackDAO;

    public PokemonFacade() {
        this.pokemonService = new PokemonService();
        this.attackDAO = new AttackDBDAO();
        this.pokemonAttackDAO = new PokemonAttackDBDAO();
    }

    public List<Pokemon> listarTodosPokemons() {
        try {
            return pokemonService.listarTodosPokemons();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Attack> listarTodosAtaques() {
        try {
            return attackDAO.consultarPorTipo("");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Pokemon buscarPokemonPorId(int id) throws SQLException {
        return pokemonService.buscarPorId(id);
    }

    public Attack buscarAtaquePorId(int id) throws SQLException {
        return attackDAO.consultarPorId(id);
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

    public void inserirPokemon(Pokemon pokemon) throws SQLException {
        pokemonService.inserirPokemon(pokemon);
    }

    public void atualizarPokemon(Pokemon pokemon) throws SQLException {
        pokemonService.atualizarPokemon(pokemon);
    }

    public void excluirPokemon(Pokemon pokemon) throws SQLException {
        pokemonService.excluirPokemon(pokemon);
    }
}