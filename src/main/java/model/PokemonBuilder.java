package model;

public class PokemonBuilder {
    private String pokemonName;
    private String pokemonType1;
    private String pokemonType2;

    public PokemonBuilder setNome(String nome) {
        this.pokemonName = nome;
        return this;
    }

    public PokemonBuilder setTipo1(String tipo1) {
        this.pokemonType1 = tipo1;
        return this;
    }

    public PokemonBuilder setTipo2(String tipo2) {
        this.pokemonType2 = tipo2 != null ? tipo2 : "-";
        return this;
    }

    public Pokemon build() {
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonName(this.pokemonName);
        pokemon.setPokemonType1(this.pokemonType1);
        pokemon.setPokemonType2(this.pokemonType2);
        return pokemon;
    }
}