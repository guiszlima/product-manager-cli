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


    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            this.id = 0;
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            JsonElement element = JsonParser.parseReader(reader);
            JsonObject root = element.getAsJsonObject();
            JsonArray produtosArray = root.getAsJsonArray("produtos");

            if (produtosArray.isEmpty()) {
                this.id = 0;
                return;
            }

            JsonObject ultimoProduto = produtosArray
                    .get(produtosArray.size() - 1)
                    .getAsJsonObject();

            if (ultimoProduto.has("id")) {
                this.id = ultimoProduto.get("id").getAsInt() + 1;
            } else {
                this.id = 0; // Fallback se não houver ID no último produto
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler JSON: " + e.getMessage());
            this.id = 0;
        } catch (Exception e) {
            System.out.println("Erro ao definir ID: " + e.getMessage());
            this.id = 0;
        }
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