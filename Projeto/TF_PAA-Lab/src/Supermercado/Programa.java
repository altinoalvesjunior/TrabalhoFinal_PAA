package Supermercado;

import ProduzirDados.AdmProcessos;
import ProduzirDados.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Metodo mercado = new Metodo();
        AdmProcessos admDados = new AdmProcessos();

        int op;

        do {
            System.out.println("\n\t------------ P3 - Lista Supermercado ------------");
            System.out.print("Digite o orçamento: ");
            double orcamento = sc.nextInt();

            List<Produto> produtos = admDados.geraProduto(10);
            int peso = admDados.criarCapacidade(produtos, 8);

            System.out.println("Produtos Disponíveis: ");
            for (int i = 0; i < produtos.size() - 1; i++) {
                Produto produto = (Produto) produtos.get(i);
                System.out.println(produto.toString());
            }

            System.out.println();
            System.out.println("Peso: " + peso);
            System.out.println("Orçamento inicial: " + orcamento);

            List<Produto> supermercado = mercado.gulosoSupermercado(peso, produtos, orcamento);

            System.out.println("Produtos adicionados na Lista: ");
            for (int i = 0; i < supermercado.size() - 1; i++) {
                Produto produto = (Produto) supermercado.get(i);
                System.out.println(produto.toString());
            }

            System.out.println("Valor total da compra: " + supermercado.get(supermercado.size() - 1));

            System.out.println("---------------------------------------------------");
            System.out.print("Deseja executar novamente? \n 1 - Sim \n 2 - Não \n\nEscolha: ");
            op = sc.nextInt();
            sc.nextLine();
        } while(op != 2);

        System.out.println("Saindo do programa!");

        sc.close();
    }
}
