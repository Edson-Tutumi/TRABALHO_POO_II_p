package controller;

import Main.GerenciadorTelas;
import javafx.fxml.FXML;

public class MenuController {

    @FXML
    public void btnAdicionarPokemonOnAction() {
        GerenciadorTelas.changeScreen("/org/example/trabalho_poo/views/InsertViews/AdicionarPokemon.fxml", "Inserir Pokemon");
    }

    @FXML
    public void btnPesquisarPokemonOnAction() {
        GerenciadorTelas.changeScreen("/org/example/trabalho_poo/views/SearchViews/PesquisarPokemon.fxml", "Pesquisar Pokemon");
    }

    @FXML
    public void btnAtualizarPokemonOnAction() {
        GerenciadorTelas.changeScreen("/org/example/trabalho_poo/views/UpdateViews/AtualizarPokemon.fxml", "Atualizar Pokemon");
    }

    @FXML
    public void btnDeletarPokemonOnAction() {
        GerenciadorTelas.changeScreen("/org/example/trabalho_poo/views/DeleteViews/DeletarPokemon.fxml", "Deletar Pokemon");
    }

    @FXML
    public void btnAdicionarAtaquesOnAction() {
        GerenciadorTelas.changeScreen("/org/example/trabalho_poo/views/InsertViews/AdicionarAtaques.fxml", "Inserir Ataques");
    }

    @FXML
    public void btnPesquisarAtaquesOnAction() {
        GerenciadorTelas.changeScreen("/org/example/trabalho_poo/views/SearchViews/PesquisarAtaques.fxml", "Pesquisar Ataques");
    }

    @FXML
    public void btnAtualizarAtaquesOnAction() {
        GerenciadorTelas.changeScreen("/org/example/trabalho_poo/views/UpdateViews/AtualizarAtaque.fxml", "Atualizar Ataque");
    }

    @FXML
    public void btnDeletarAtaquesOnAction() {
        GerenciadorTelas.changeScreen("/org/example/trabalho_poo/views/DeleteViews/DeletarAtaques.fxml", "Deletar Ataque");
    }

    @FXML
    public void btnTypeChartOnAction() {
        GerenciadorTelas.changeScreen("/org/example/trabalho_poo/views/TypeChart.fxml", "Tabela de Tipos");
    }

    @FXML
    public void btnPokemonAttackOnAction() {
        GerenciadorTelas.changeScreen("/org/example/trabalho_poo/views/PokemonAttack.fxml", "Vincular Ataques ao Pokemon");
    }
}