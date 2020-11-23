package Mochila;

import java.util.ArrayList;
import java.util.List;

public class AlgMochila {

    public void forcaBruta(int capacidade, List <app.Produto> produtos, int posicaoLista, List <Object> mochila){
        app.Produto aux = new app.Produto();

        //A lista comparativa serve para comparar a nova combinacao com os itens da mochila
        //A ultima posicao das listas comparativa e mochila equivale ao valor das combinacoes
        List <Object> comparativa = new ArrayList<Object>();

        //Recebe primeiro produto para realizar combinacoes
        if(posicaoLista<capacidade){
            aux = produtos.get(posicaoLista);
        }else{
            posicaoLista++;
        }
        //Primeiro objeto da lista adicionado
        //As composicoes da lista serao feitas com base no primeiro objeto
        mochila.add(produtos.get(posicaoLista));

        //Variavel utilizada para percorrer a lista durante a formacao de combinacoes e nao perder a posicao
        // do objeto utilizado para ealizar as comparacoes
        int pos = posicaoLista+1;

        int somaPeso = aux.getPeso();
        float somaValor = aux.getValor();

        //Variavel utilizada para sinalizar quando a combinacao passa do peso maximo
        int controlaPeso = 0;

        while(controlaPeso!=1 && produtos.get(pos)!=null){
            somaPeso += produtos.get(pos).getPeso();

            if(somaPeso>capacidade){
                controlaPeso = 1;

                //Como nao ocorrera mais comparacoes, o valor final da comparacao é gravado no final da lista.
                comparativa.add(somaValor);
            }else{
                //Adiciona na lista comparativa o objeto incluido na mochila.
                comparativa.add(produtos.get(pos));
                //Incrementa o valor total da mochila.
                somaValor += produtos.get(pos).getValor();
            }

        }

        if(produtos.get(pos)==null){
            posicaoLista++;
        }

    }
}
