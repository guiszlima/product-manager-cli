package ProductController;


import Model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ProductController {


    Scanner scanner = new Scanner(System.in);
    public void adicionarProduto(){

while(true) {

            Product produto = new Product();
            ProductStorage store = new ProductStorage();

            System.out.print("Digite o nome do produto: ");
            String nome = scanner.nextLine();
            produto.setNome(nome);

            System.out.print("Digite o preço do produto: ");
            double preco = scanner.nextDouble();
            produto.setPreco(preco);

            System.out.print("Digite a quantidade em estoque: ");
            int quantidade = scanner.nextInt();
            produto.setQuantidade(quantidade);


    HashMap<String,Object> produtoGerado = produto.geraProduto();
    System.out.println(produto);
            ProductStorage.salvar(produtoGerado);

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
        List<Product> produtos = ProductStorage.carregar();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Product produto : produtos) {
            System.out.println("----------------------------");
            System.out.println("ID         : " + produto.getId());
            System.out.println("Nome        : " + produto.getNome());
            System.out.printf("Preço      : R$ %.2f%n", produto.getPreco());
            System.out.println("Quantidade : " + produto.getQuantidade());
        }

        System.out.println("----------------------------");
    }

}
