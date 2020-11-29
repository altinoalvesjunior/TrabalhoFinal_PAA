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

        //List<Object> mochila  = alg.forcaBruta(capacidade, produtos, combNova, 0, 1, bag);
        List<Object> mochila  = alg.guloso(capacidade, produtos);

        for(int i=0; i< mochila.size()-1; i++){
            Produto produto = (Produto) mochila.get(i);
            System.out.println(produto.toString());

        }
        System.out.println( "Maior valor possível: " + (float) mochila.get(mochila.size()-1));

    }
}
