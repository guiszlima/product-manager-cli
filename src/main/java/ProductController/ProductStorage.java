package ProductController;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import Model.Product;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class ProductStorage {

    private static final String FILE_PATH = "produtos.json";
    private static final Gson gson = new Gson();

    public static void salvar(Product produto) {
        File file = new File(FILE_PATH);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject root;
        JsonArray produtosArray;


        try {
            // Se o arquivo existe, ler o conteúdo
            if (file.exists()) {
                try (Reader reader = new FileReader(file)) {
                    root = JsonParser.parseReader(reader).getAsJsonObject();
                }

                produtosArray = root.getAsJsonArray("produtos");
                // pega Id do json
                int maiorId = -1;
                for(JsonElement  produtos : produtosArray){

                    if (produtos.isJsonObject()) {
                        JsonObject obj = produtos.getAsJsonObject();
                        if (obj.has("id")) {
                            int id = obj.get("id").getAsInt();
                            if (id > maiorId) {
                                maiorId = id;
                            }
                        }
                    }
                }
                produto.setId(maiorId + 1);
            } else {
                root = new JsonObject();
                produtosArray = new JsonArray();
                root.add("produtos", produtosArray);
            }

            // Converte o objeto Product em JSON
            JsonElement produtoJson = gson.toJsonTree(produto);
            produtosArray.add(produtoJson);

            // Escreve o novo JSON no arquivo
            try (Writer writer = new FileWriter(FILE_PATH)) {
                gson.toJson(root, writer);
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
        }
    }




    public static JsonObject   carregar() {
        JsonObject root = new JsonObject();


        File file = new File(FILE_PATH);
        try(Reader reader = new FileReader(file)){
            root = JsonParser.parseReader(reader).getAsJsonObject();
        }
        catch (FileNotFoundException e) {
            // Arquivo não encontrado, retorna objeto vazio
            System.out.println("Arquivo não encontrado. Criando JSON vazio.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }

        return root;
    }
    public static void procuraProduto(int id){
        boolean encontrado = false;
       JsonObject root = carregar();
       JsonArray produtos = root.getAsJsonArray("produtos");

       for(JsonElement element : produtos){
           Product produto = gson.fromJson(element, Product.class);
           if(produto.getId() == id){
            encontrado = true;
               System.out.println(produto);
           }
       }
       if (!encontrado){
           System.out.println("❌ Produto com ID " + id + " não foi encontrado.");
       }
    }

    public static void updateProduto(int id) {
        Scanner scanner = new Scanner(System.in);
        boolean encontrado = false;
        JsonObject root = carregar();
        JsonArray produtos = root.getAsJsonArray("produtos");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Precisamos do índice para substituir depois
        int index = 0;

        for (JsonElement element : produtos) {
            Product produto = gson.fromJson(element, Product.class);

            if (produto.getId() == id) {
                encontrado = true;

                System.out.println("🔎 Produto encontrado:");
                System.out.println(produto);
                System.out.println("\nO que deseja atualizar?");
                System.out.println("1 - Nome");
                System.out.println("2 - Preço");
                System.out.println("3 - Quantidade em estoque");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // consumir quebra de linha

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o novo nome: ");
                        String novoNome = scanner.nextLine();
                        produto.setNome(novoNome);
                        break;
                    case 2:
                        System.out.print("Digite o novo preço: ");
                        double novoPreco = scanner.nextDouble();
                        produto.setPreco(novoPreco);
                        break;
                    case 3:
                        System.out.print("Digite a nova quantidade: ");
                        int novaQuantidade = scanner.nextInt();
                        produto.setQuantidade(novaQuantidade);
                        break;
                    default:
                        System.out.println("❌ Opção inválida.");
                        return;
                }

                // Substitui o produto atualizado no JsonArray
                JsonElement produtoAtualizado = gson.toJsonTree(produto);
                produtos.set(index, produtoAtualizado);

                // Salva no arquivo JSON
                try (Writer writer = new FileWriter(FILE_PATH)) {
                    gson.toJson(root, writer);
                    System.out.println("✅ Produto atualizado com sucesso!");
                } catch (IOException e) {
                    System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
                }

                break; // já encontrou e atualizou, pode sair do loop
            }

            index++; // incrementa o índice para manter posição no JsonArray
        }

        if (!encontrado) {
            System.out.println("❌ Produto com ID " + id + " não foi encontrado.");
        }
    }
    public static void deleteProduto(int id) {
        JsonObject root = carregar();
        JsonArray produtos = root.getAsJsonArray("produtos");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        boolean encontrado = false;

        for (int i = 0; i < produtos.size(); i++) {
            JsonElement element = produtos.get(i);
            Product produto = gson.fromJson(element, Product.class);

            if (produto.getId() == id) {
                produtos.remove(i);
                encontrado = true;
                break; // já removeu, para o loop
            }
        }

        if (encontrado) {
            try (Writer writer = new FileWriter(FILE_PATH)) {
                gson.toJson(root, writer);
                System.out.println("✅ Produto com ID " + id + " removido com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao salvar após remoção: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Produto com ID " + id + " não encontrado.");
        }
    }

}



