package ProductController;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import Model.Product;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductStorage {

    private static final String FILE_PATH = "produtos.json";
    private static final Gson gson = new Gson();

    public static void salvar(HashMap<String, Object> novoProduto) {
        File file = new File(FILE_PATH);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject root;
        JsonArray produtosArray;

        try {
            // Se o arquivo existir, lemos o conteúdo
            if (file.exists()) {
                JsonElement element = JsonParser.parseReader(new FileReader(file));
                root = element.getAsJsonObject();
                produtosArray = root.getAsJsonArray("produtos");
            } else {
                // Se não existe, criamos um novo JSON
                root = new JsonObject();
                produtosArray = new JsonArray();
                root.add("produtos", produtosArray);
            }

            // Converter o HashMap novoProduto em JsonObject
            JsonObject produtoJson = new JsonObject();
            for (Map.Entry<String, Object> entry : novoProduto.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value instanceof String) {
                    produtoJson.addProperty(key, (String) value);
                } else if (value instanceof Number) {
                    produtoJson.addProperty(key, (Number) value);
                } else if (value instanceof Boolean) {
                    produtoJson.addProperty(key, (Boolean) value);
                } else {
                    // Caso o valor seja nulo ou outro tipo, pode adaptar aqui
                    produtoJson.add(key, JsonNull.INSTANCE);
                }
            }

            produtosArray.add(produtoJson);

            // Escreve de volta no arquivo
            try (Writer writer = new FileWriter(FILE_PATH)) {
                gson.toJson(root, writer);
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
        }
    }




    public static List<Product> carregar() {
        List<Product> produtos = new ArrayList<>();

        try (Reader reader = new FileReader(FILE_PATH)) {
            JsonElement element = JsonParser.parseReader(reader);
            JsonObject root = element.getAsJsonObject();

            JsonArray produtosArray = root.getAsJsonArray("produtos");

            Gson gson = new Gson();
            for (JsonElement produtoElement : produtosArray) {
                Product produto = gson.fromJson(produtoElement, Product.class);
                produtos.add(produto);
            }

        } catch (FileNotFoundException e) {
            produtos = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }

        return produtos;
    }

}
