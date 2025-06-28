package Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;




public class Product {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private static final String FILE_PATH = "produtos.json";
    // Construtor

    public Product(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setId() {
        this.id = 0; 
    }





    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {

        this.quantidade = quantidade;
    }

    public HashMap<String,Object> geraProduto() {

        HashMap<String,Object> atributos = new HashMap<>();
        this.setId();
        atributos.put("id",this.id);
        atributos.put("nome",this.nome);
        atributos.put("preco",this.preco);
        atributos.put("quantidade",this.quantidade);
        return atributos;
    }
    // Método para exibir o produto
    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Preço: R$" + preco + " | Estoque: " + quantidade;
    }
}