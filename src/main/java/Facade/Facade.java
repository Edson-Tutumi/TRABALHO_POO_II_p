package Facade;

import Service.PokemonService;
import Service.AttackService;
import Service.PokemonAttackService;
import model.Attack;
import model.Pokemon;

import java.sql.SQLException;
import java.util.List;

public class Facade {
    private PokemonService pokemonService;
    private AttackService attackService;
    private PokemonAttackService pokemonAttackService;

    public Facade() {
        this.pokemonService = new PokemonService();
        this.attackService = new AttackService();
        this.pokemonAttackService = new PokemonAttackService();
    }

    public void cadastrarPokemonComAtaque(Pokemon pokemon, int attackId) throws SQLException {
        pokemonService.inserirPokemon(pokemon);

        if (pokemon.getPokemonId() > 0) {
            pokemonAttackService.vincularAtaqueAoPokemon(pokemon.getPokemonId(), attackId);
        }
    }

    //--------------
    public void inserirPokemon(Pokemon pokemon) throws SQLException {
        pokemonService.inserirPokemon(pokemon);
    }

    public void atualizarPokemon(Pokemon pokemon) throws SQLException {
        pokemonService.atualizarPokemon(pokemon);
    }

    public void excluirPokemon(Pokemon pokemon) throws SQLException {
        pokemonService.excluirPokemon(pokemon);
    }

    public Pokemon buscarPokemonPorId(int id) throws SQLException {
        return pokemonService.buscarPorId(id);
    }

    public Pokemon buscarPokemonPorNome(String nome) throws SQLException {
        return pokemonService.buscarPorNome(nome);
    }

    public List<Pokemon> buscarPokemonPorTipo(String tipo) throws SQLException {
        return pokemonService.buscarPokemonPorTipo(tipo);
    }

    public List<Pokemon> listarTodosPokemons() throws SQLException {
        return pokemonService.listarTodosPokemons();
    }

    //--------------
    public void inserirAtaque(Attack attack) throws SQLException {
        attackService.inserirAtaque(attack);
    }

    public void atualizarAtaque(Attack attack) throws SQLException {
        attackService.atualizarAtaque(attack);
    }

    public void excluirAtaque(Attack attack) throws SQLException {
        attackService.excluirAtaque(attack);
    }

    public Attack buscarAtaquePorId(int id) throws SQLException {
        return attackService.buscarPorId(id);
    }

    public Attack buscarAtaquePorNome(String nome) throws SQLException {
        return attackService.buscarPorNome(nome);
    }

    public List<Attack> buscarAtaquePorTipo(String tipo) throws SQLException {
        return attackService.buscarPorTipo(tipo);
    }

    public List<Attack> listarTodosAtaques() throws SQLException {
        return attackService.listarTodosAtaques();
    }

    //--------------
    public void vincularAtaqueAoPokemon(int pokemonId, int attackId) throws SQLException {
        pokemonAttackService.vincularAtaqueAoPokemon(pokemonId, attackId);
    }

    public void desvincularAtaqueDoPokemon(int pokemonId, int attackId) throws SQLException {
        pokemonAttackService.desvincularAtaqueDoPokemon(pokemonId, attackId);
    }

    public List<Attack> listarAtaquesPorPokemon(int pokemonId) throws SQLException {
        return pokemonAttackService.listarAtaquesPorPokemon(pokemonId);
    }

    public List<Pokemon> listarPokemonsPorAtaque(int attackId) throws SQLException {
        return pokemonAttackService.listarPokemonsPorAtaque(attackId);
    }
}