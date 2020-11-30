package Mochila;

import ProduzirDados.AdmProcessos;
import ProduzirDados.Produto;

import java.util.ArrayList;
import java.util.List;

public class MainMochila {
    public static void main(String[] args){

        AlgMochila alg = new AlgMochila();
        AdmProcessos admDados = new AdmProcessos();

        List<Produto> produtos = admDados.geraProduto(10);
        int capacidade = admDados.criarCapacidade(produtos,8);

        List<Object> combNova = new ArrayList<>();
        List<Object> bag = new ArrayList<>();

        long tempoInicialFB = System.nanoTime();
        List<Object> forcaBruta  = alg.forcaBruta(capacidade, produtos, combNova, 0, 1, bag);
        double tempoExecucaoFB = (((double) (System.nanoTime() - tempoInicialFB))/ 1_000_000_000);

        long tempoInicialGuloso = System.nanoTime();
        List<Object> guloso = alg.guloso(capacidade, produtos);
        double tempoExecucaoGuloso = (((double) (System.nanoTime() - tempoInicialGuloso))/ 1_000_000_000);

        long tempoInicialPD = System.nanoTime();
        float PD = alg.ProgramacaoDinamica(produtos, capacidade);
        double tempoExecucaoPD = (((double) (System.nanoTime() - tempoInicialPD))/ 1_000_000_000);

        System.out.println("P1 - MOCHILA");
        System.out.println("------------------------------------------");
        System.out.println("Capacidade maxima da mochila: " + capacidade);

        System.out.println();
        System.out.println("------------------------------------------");
        System.out.println("Programacao Dinamica: ");
        System.out.println("Valor máximo: " + PD);
        System.out.println("Tempo de Execução: " + tempoExecucaoPD);
        System.out.println("------------------------------------------");

        System.out.println();
        System.out.println("Forca Bruta: ");

        for(int i=0; i< forcaBruta.size()-1; i++){
            Produto produto = (Produto) forcaBruta.get(i);
            System.out.println(produto.toString());
        }

        System.out.println("Valor máximo:" + forcaBruta.get(forcaBruta.size()-1));
        System.out.println("Tempo de Execução: " + tempoExecucaoFB);

        System.out.println("------------------------------------------");
        System.out.println();
        System.out.println("Guloso: ");
        for(int i=0; i< guloso.size()-1; i++){
            Produto produto = (Produto) guloso.get(i);
            System.out.println(produto.toString());
        }
        System.out.println("Valor máximo:" + guloso.get(guloso.size()-1));
        System.out.println("Tempo de Execução: " + tempoExecucaoGuloso);
        System.out.println("------------------------------------------");
    }
}
