package Subsequencia;

import java.util.Scanner;

public class Programa {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Metodo sub = new Metodo();

        int op;

        do {
            System.out.println("\n\t------------ P6 - Subsequênia ------------");
            System.out.print("\nDigite a primeira palavra: ");
            String palavra1 = sc.nextLine().toUpperCase();
            System.out.print("Digite a primeira palavra: ");
            String palavra2 = sc.nextLine().toUpperCase();

            System.out.print("\nA subsequência é: ");
            System.out.print("\nO tamanho da subsequência é: " + sub.encontraSubsequencia(palavra1, palavra2) + "\n");

            System.out.println("---------------------------------------------------");
            System.out.print("Deseja executar novamente? \n 1 - Sim \n 2 - Não \n\nEscolha: ");
            op = sc.nextInt();
            sc.nextLine();
        } while (op != 2);

        System.out.println("Saindo do programa!");

        sc.close();
    }
}