package Service;

import DAO.Pokemon.PokemonDAO;
import DAO.Pokemon.PokemonDBDAO;
import model.Pokemon;
import java.sql.SQLException;
import java.util.List;

public class PokemonService {
    private PokemonDAO pokemonDAO;

    public PokemonService() {
        this.pokemonDAO = new PokemonDBDAO();
    }

    public List<Pokemon> listarTodosPokemons() throws SQLException {
        return pokemonDAO.consultarPorTipos("", "");
    }

    public Pokemon buscarPorId(int id) throws SQLException {
        return pokemonDAO.consultarPorId(id);
    }

    public Pokemon buscarPorNome(String nome) throws SQLException {
        return pokemonDAO.consultarPorNome(nome);
    }

    public List<Pokemon> buscarPorTipos(String tipo1, String tipo2) throws SQLException {
        return pokemonDAO.consultarPorTipos(tipo1, tipo2);
    }

    public void inserirPokemon(Pokemon pokemon) throws SQLException {
        if (pokemon.getPokemonName() == null || pokemon.getPokemonName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do Pokémon não pode ser vazio.");
        }
        pokemonDAO.insere(pokemon);
    }

    public void atualizarPokemon(Pokemon pokemon) throws SQLException {
        pokemonDAO.atualizar(pokemon);
    }

    public void excluirPokemon(Pokemon pokemon) throws SQLException {
        pokemonDAO.excluir(pokemon);
    }
}