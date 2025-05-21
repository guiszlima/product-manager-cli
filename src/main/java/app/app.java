package app;

import ProductManager.ProductManager;
import java.util.Scanner;
import java.util.InputMismatchException;

public class app {

    private int opcao;

    private    String[] opcoesMenu = {
            "1️⃣  Adicionar produto",
            "2️⃣  Listar todos os produtos",
            "3️⃣  Buscar produto por ID",
            "4️⃣  Atualizar produto",
            "5️⃣  Remover produto",
            "0️⃣  Sair"
    };



    public void mostrar() {



        System.out.println("=========================================");
        System.out.println("        🛒 Cadastro de Produtos         ");
        System.out.println("=========================================");

        for (String opcao : this.opcoesMenu){
            System.out.println(opcao);
        }


        System.out.println("=========================================");
        Scanner scanner = new Scanner(System.in); // cria o scanner
        System.out.print("Escolha uma opção: "); // mostra mensagem



    }
    public void iniciar() {


        while (true) {
            try{
            this.mostrar();
            Scanner scanner = new Scanner(System.in);
            ProductManager manager = new ProductManager();

            this.opcao = scanner.nextInt();

            switch (this.opcao) {
                case 1:
                    System.out.println("Adicionar produto selecionado.");
                    manager.adicionarProduto();
                    break;
                case 2:
                    System.out.println("Listar todos os produtos selecionado.");
                    // Chamar método listarProdutos();
                    break;
                case 3:
                    System.out.println("Buscar produto por ID selecionado.");
                    // Chamar método buscarProduto();
                    break;
                case 4:
                    System.out.println("Atualizar produto selecionado.");
                    // Chamar método atualizarProduto();
                    break;
                case 5:
                    System.out.println("Remover produto selecionado.");
                    // Chamar método removerProduto();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    return; // sai do método iniciar, encerrando o programa
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            } catch (InputMismatchException e) {
                System.out.println("Por favor selecione uma opção valida");
            }
        }

    }



}
