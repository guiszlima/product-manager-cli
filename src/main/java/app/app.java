package app;

import ProductController.ProductController;
import java.util.Scanner;
import java.util.InputMismatchException;

public class app {

    private int opcao;
    private int escolha;
    private    String[] opcoesMenu = {
            "1️⃣  Adicionar produto",
            "2️⃣  Listar todos os produtos",
            "3️⃣  Buscar produto por ID",
            "4️⃣  Atualizar produto",
            "5️⃣  Remover produto",
            "0️⃣  Sair"
    };



    public int mostrar() {



        System.out.println("=========================================");
        System.out.println("        🛒 Cadastro de Produtos         ");
        System.out.println("=========================================");

        for (String opcao : this.opcoesMenu){
            System.out.println(opcao);
        }


        System.out.println("=========================================");
        Scanner scanner = new Scanner(System.in); // cria o scanner
        System.out.print("Escolha uma opção: "); // mostra mensagem

        this.opcao = scanner.nextInt();
        return this.opcao;

    }
    public void iniciar() {


        while (true) {
            try{

            this.escolha = this.mostrar();

            ProductController manager = new ProductController();



            switch (this.escolha) {
                case 1:
                    System.out.println("Opção Escolhida: Adicionar produto selecionado.");
                    manager.adicionarProduto();
                    break;
                case 2:
                    System.out.println("Opção Escolhida: Listar todos os produtos selecionado.");
                    manager.listarProdutos();
                    break;
                case 3:
                    System.out.println("Opção Escolhida: Buscar produto por ID selecionado.");
                    manager.listarProdutos();

                    manager.buscarProdutos();

                    break;
                case 4:
                    System.out.println("Opção Escolhida: Atualizar produto selecionado.");
                    manager.listarProdutos();
                    manager.atualizarProduto();
                    break;
                case 5:
                    System.out.println("Opção Escolhida: Remover produto selecionado.");
                    manager.listarProdutos();
                    manager.removerProduto();
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
