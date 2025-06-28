package ProductController;


import Model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ProductController {


    Scanner scanner = new Scanner(System.in);
    public void adicionarProduto(){

while(true) {


            ProductStorage store = new ProductStorage();

            System.out.print("Digite o nome do produto: ");
            String nome = scanner.nextLine();


            System.out.print("Digite o preço do produto: ");
            double preco = scanner.nextDouble();


            System.out.print("Digite a quantidade em estoque: ");
            int quantidade = scanner.nextInt();
    Product produto = new Product(nome,preco,quantidade);




            ProductStorage.salvar(produto);

            // Aqui você pode salvar o produto em uma lista, ou chamar ProductStorage.salvar(lista)

            System.out.println("Produto cadastrado com sucesso: " + produto);
            System.out.println("Deseja Continuar? [S/N]");
            scanner.nextLine();
            String keep = scanner.nextLine();

    if ((keep.charAt(0) == 'N' || keep.charAt(0) == 'n')) {
        break;
    }


        }
    }
    public void listarProdutos() {
        Gson gson = new Gson();
        JsonObject root = ProductStorage.carregar();
        JsonArray produtos = root.getAsJsonArray("produtos");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for ( JsonElement element : produtos) {
            Product produto = gson.fromJson(element, Product.class);
            System.out.println("----------------------------");
            System.out.println("ID         : " + produto.getId());
            System.out.println("Nome        : " + produto.getNome());
            System.out.printf("Preço      : R$ %.2f%n", produto.getPreco());
            System.out.println("Quantidade : " + produto.getQuantidade());
        }

        System.out.println("----------------------------");
    }
public void buscarProdutos(){
        int productId;
    try{

        System.out.print("Digite o id: ");
        productId = scanner.nextInt();
        ProductStorage.procuraProduto(productId);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
public void atualizarProduto(){
    int productId;
    try{

        System.out.print("Digite o id: ");
        productId = scanner.nextInt();
        ProductStorage.updateProduto(productId);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
public void removerProduto(){
    int productId;
    try{

        System.out.print("Digite o id: ");
        productId = scanner.nextInt();
        ProductStorage.deleteProduto(productId);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

}
}
