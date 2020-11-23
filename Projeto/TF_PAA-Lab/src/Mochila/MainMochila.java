package Mochila;

import java.util.ArrayList;
import java.util.List;

public class MainMochila {
    public static void main(String[] args){

        AlgMochila alg = new AlgMochila();
        app.App admDados = new app.App();

        List<app.Produto> produtos = admDados.geraProduto(10);
        int capacidade = admDados.criarCapacidade(produtos,8 );
        List<Object> combNova = new ArrayList<>();
        List<Object> bag = new ArrayList<>();

        List<Object> mochila  = alg.forcaBruta(capacidade, produtos, combNova, 0, bag);

        for(int i=0; i< mochila.size()-1; i++){
            app.Produto produto = (app.Produto) mochila.get(i);
            produto.toString();
        }
        System.out.println((float) mochila.get(mochila.size()-1));

    }
}
