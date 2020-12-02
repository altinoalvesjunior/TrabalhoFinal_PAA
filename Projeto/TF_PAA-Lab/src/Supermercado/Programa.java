package Supermercado;

import ProduzirDados.AdmProcessos;
import ProduzirDados.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AdmProcessos admDados = new AdmProcessos();

        System.out.println("Digite o orçamento: ");
        double orcamento = sc.nextInt();

        List<Produto> produtos = admDados.geraProduto(10);
        int peso = admDados.criarCapacidade(produtos,8);

        System.out.println("Produtos: " + produtos);
        System.out.println("Peso: " + peso);
        System.out.println("Orçamento inicial: " + orcamento);

        List<Produto> supermercado = gulosoSupermercado(peso, produtos, orcamento);
        //System.out.println(supermercado);

        for(int i=0; i< supermercado.size()-1; i++){
            Produto produto = (Produto) supermercado.get(i);
            System.out.println(produto.toString());
        }

        System.out.println("Valor total da compra: " + supermercado.get(supermercado.size()-1));

        sc.close();

    }

    public static List gulosoSupermercado(int pesoMaximo, List<Produto> produtos, double orcamento) {

        // mochila final
        List<Object> mochila = new ArrayList<>(); // Só irá receber os produtos definitivos
        List<Produto> aux = new ArrayList<>();
        aux.addAll(produtos);// criando uma lista auxilar (cópia da lista oficial de produtos) para poder sofrer alterações

        Produto menorValor; // armazena o produto que armazena o menor valor no momento
        Produto temp;
        int pesoMochila = 0;
        float valorMochila = 0;
        double contadorOrcamento = 0;

        while (pesoMochila < pesoMaximo && aux.size() >= 0 && contadorOrcamento < orcamento) {
            menorValor = aux.get(0);

            for (int i = 0; i < aux.size(); i++) {
                temp = aux.get(i);

                if (temp.getValor() < menorValor.getValor()) {
                    menorValor = temp;
                }
            }

            pesoMochila += menorValor.getPeso();
            contadorOrcamento += menorValor.getValor();
            aux.remove(menorValor);

            if (pesoMochila < pesoMaximo && contadorOrcamento < orcamento) {
                mochila.add(menorValor);
                valorMochila += menorValor.getValor();
                //contadorOrcamento += menorValor.getValor();
            }
        }

        mochila.add(valorMochila);
        return mochila;
    }
}
