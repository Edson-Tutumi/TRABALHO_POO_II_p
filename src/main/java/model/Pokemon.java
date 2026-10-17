package model;

public class Pokemon {
    private int pokemonId;
    private String pokemonName;
    private String pokemonType1;
    private String pokemonType2;

    public Pokemon() {
    }

    public Pokemon(int pokemonId, String pokemonName, String pokemonType1, String pokemonType2) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.pokemonType1 = pokemonType1;
        this.pokemonType2 = pokemonType2;
    }

    public int getPokemonId() {
        return pokemonId;
    }
    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getPokemonName() {
        return pokemonName;
    }
    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPokemonType1() {
        return pokemonType1;
    }
    public void setPokemonType1(String pokemonType1) {
        this.pokemonType1 = pokemonType1;
    }

    public String getPokemonType2() {
        return pokemonType2;
    }
    public void setPokemonType2(String pokemonType2) {
        this.pokemonType2 = pokemonType2;
    }

    @Override
    public String toString(){
        return "Id: " + pokemonId + " Name: " + pokemonName + " Type1: " + pokemonType1 + " Type2: " + pokemonType2;
    }
}
