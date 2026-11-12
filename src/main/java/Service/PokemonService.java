package Service;

import DAO.Pokemon.PokemonDAO;
import DAO.Pokemon.PokemonDBDAO;
import model.Pokemon;
import model.PokemonBuilder;
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
        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser um valor maior que zero.");
        }
        return pokemonDAO.consultarPorId(id);
    }

    public Pokemon buscarPorNome(String nome) throws SQLException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome para busca não pode ser vazio.");
        }
        return pokemonDAO.consultarPorNome(nome.trim());
    }

    public Pokemon buscarPokemonPorNome(String nome) throws SQLException {
        return buscarPorNome(nome);
    }

    public List<Pokemon> buscarPorTipos(String tipo1, String tipo2) throws SQLException {
        return pokemonDAO.consultarPorTipos(tipo1, tipo2);
    }

    public List<Pokemon> buscarPokemonPorTipo(String tipo) throws SQLException {
        return pokemonDAO.consultarPorTipos(tipo, "");
    }

    public void inserirPokemon(Pokemon pokemon) throws SQLException {
        if (pokemon.getPokemonName() == null || pokemon.getPokemonName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do pokemon não pode ser vazio.");
        }

        Pokemon existente = pokemonDAO.consultarPorNome(pokemon.getPokemonName());
        if (existente != null) {
            throw new SQLException("Já existe um Pokémon cadastrado com o nome: " + pokemon.getPokemonName());
        }

        pokemonDAO.insere(pokemon);
    }

    public void atualizarPokemon(Pokemon pokemon) throws SQLException {
        if (pokemon.getPokemonId() <= 0) {
            throw new IllegalArgumentException("ID do pokemon inválido para atualização.");
        }

        if (pokemon.getPokemonName() == null || pokemon.getPokemonName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do pokemon não pode ser vazio.");
        }

        Pokemon existente = pokemonDAO.consultarPorId(pokemon.getPokemonId());
        if (existente == null) {
            throw new SQLException("Não foi possível atualizar: pokemon com ID " + pokemon.getPokemonId() + " não foi encontrado.");
        }

        pokemonDAO.atualizar(pokemon);
    }

    public void excluirPokemon(Pokemon pokemon) throws SQLException {
        pokemonDAO.excluir(pokemon);
    }
}