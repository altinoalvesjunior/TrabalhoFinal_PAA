package Mochila;

import java.util.ArrayList;
import java.util.List;

public class AlgMochila {

    AlgMochila(){

    }

    public List forcaBruta(int capacidade, List <app.Produto> produtos, List <Object> combNova, int posicaoLista, List <Object> mochila){
        app.Produto aux = new app.Produto();

        //A lista combNova serve para comparar a nova combinacao com os itens da mochila
        //A ultima posicao das listas combNova e mochila equivale ao valor das combinacoes

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
        // do objeto referencia utilizado para ralizar as combinacoes
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
                combNova.add(somaValor);
            }else{
                //Adiciona na lista comparativa o objeto incluido na mochila.
                combNova.add(produtos.get(pos));

                //Incrementa o valor total da mochila.
                somaValor += produtos.get(pos).getValor();
            }

        }

        //Mudanca do objeto referencia
        if(produtos.get(pos)==null){
            posicaoLista++;
        }

        float valorMochila = (float) mochila.get(mochila.size()-1);
        float valorCombNova = (float) combNova.get(combNova.size()-1);

        //Caso a nova combinacao tenha valha mais que a combinacao na mochila, a mochila recebe combnoVA
        if( valorMochila < valorCombNova ){
            mochila = combNova;
            combNova.clear();
        }

        if(posicaoLista==produtos.size()-1){
            //Caso a lista de produtos já tenha sido toda analisada, retorna a combinacao formda.
            return mochila;
        }else{

            return forcaBruta(capacidade, produtos, combNova, posicaoLista, mochila);
        }
    }
}
