package Mochila;

import ProduzirDados.Produto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AlgMochila {

    AlgMochila(){

    }

    public List forcaBruta(int capacidade, List <Produto> produtos, List <Object> combNova, int posReferencia, int posNavegacao, List <Object> mochila){
        Produto aux = produtos.get(posReferencia);

        //A lista combNova serve para comparar a nova combinacao com os itens da mochila
        //A ultima posicao das listas combNova e mochila equivale ao valor das combinacoes

        //Verifica se o peso do produto nao é maior que a cpacidade
        while(aux.getPeso()>capacidade){
            //Variavel c/ a funcao de marcar a posicao do objeto referencia utilizado para realizar as comparacoes.
            posReferencia++;
        }


        int somaPeso = aux.getPeso();
        float somaValor = aux.getValor();

        //Aqui o objeto referencia é adicionado para que sempre seja incluido em todas as combinacoes
        combNova.add(aux);

        //Variavel utilizada para sinalizar quando a combinacao passa do peso maximo
        int controlaPeso = 0;

        while(controlaPeso!=1 && posNavegacao<produtos.size()){
            somaPeso += produtos.get(posNavegacao).getPeso();

            if(somaPeso>capacidade){
                //Variavel passa a ter valor 1 caso a adicao de um novo objeto faca o peso da combinacao ultrapassar
                //o peso maximo
                controlaPeso = 1;

                //Como nao ocorrerá mais comparacoes, o valor final da comparacao é gravado no final da lista.
                combNova.add(somaValor);
            }else{
                if(posNavegacao==produtos.size()-1){
                    //Adiciona na lista comparativa o objeto.
                    combNova.add(produtos.get(posNavegacao));
                    somaValor += produtos.get(posNavegacao).getValor();
                    combNova.add(somaValor);
                }else {
                    //Adiciona na lista comparativa o objeto.
                    combNova.add(produtos.get(posNavegacao));

                    //Incrementa o valor total da mochila.
                    somaValor += produtos.get(posNavegacao).getValor();
                }
            }
            posNavegacao++;

        }
        if(posReferencia==produtos.size()-1){
            combNova.add(somaValor);
        }

        //Mudanca do objeto referencia caso todo o vetor já tenha sido percorrido e comparado com o objeto referencia.
        if(posNavegacao==produtos.size()){
            posReferencia++;
            posNavegacao = posReferencia+1;
        }

        float valorMochila;
        //Verifica se a mochila
        if(!mochilaVazia(mochila)){

            valorMochila = (float) mochila.get(mochila.size()-1);

        }else{
            valorMochila = 0;
        }

        float valorCombNova = (float) combNova.get(combNova.size()-1);

        //Caso a nova combinacao valha mais que a combinacao na mochila, a mochila recebe combNova
        if( valorMochila < valorCombNova ){
            mochila.clear();
            mochila.addAll(0, combNova);
            combNova.clear();
        }else{
            combNova.clear();
        }

        if(posReferencia==produtos.size()){
            //Caso a lista de produtos já tenha sido toda analisada, retorna a combinacao formda.
            return mochila;
        }else{

            return forcaBruta(capacidade, produtos, combNova, posReferencia, posNavegacao, mochila);
        }
    }

    public boolean mochilaVazia(List <Object> mochila){
        return mochila.isEmpty();
    }

    public List guloso(int capacidade, List<Produto> produtos) {

        //Recebe uma lista ordenada de todos os pesos da lista de produtos. Não há repeticoes de valoress
        List<Integer> pesosOrdenados = pesosExistentes(produtos, capacidade);

        int linha = produtos.size()+1;
        int coluna = pesosOrdenados.size();

        // tabela que será preenchida
        float[][] matriz = new float[linha][coluna];

        // inicializando a primeira linha e primeira coluna com 0
        for (int j = 0; j < coluna; j++) {
            matriz[0][j] = 0;
        }

        for (int i = 1; i < linha; i++) {
            matriz[i][0] = 0;
        }

        Produto aux;
        int getProd;

        for (int i = 1; i < linha; i++) {
            getProd = i-1;
            aux = produtos.get(getProd);
            for (int j = 1; j < coluna; j++) {

                if (aux.getPeso() <= pesosOrdenados.get(j)) {
                    if (aux.getPeso() == pesosOrdenados.get(j)) {

                        if (aux.getValor() > matriz[i-1][j]) {
                            matriz[i][j] = aux.getValor();
                        } else {
                            matriz[i][j] = matriz[i-1][j];
                        }
                    } else {
                        //Descobre a coluna da mochila a que se deve voltar
                        int descontoPeso = pesosOrdenados.get(j) - aux.getPeso();

                        //Descobre o index da mochila (coluna)
                        int colunaMochilaPassada = pesosOrdenados.indexOf(descontoPeso);

                        if(colunaMochilaPassada>0){
                            //Realiza-se uma soma do valor do item atual com o valor contido na mochila na matriz[i-1][colunaMochilaPassada]
                            float soma = aux.getValor()+matriz[i-1][colunaMochilaPassada];

                            if(soma > matriz[i-1][j]){
                                matriz[i][j] = soma;
                            }

                        }else{
                            matriz[i][j] = aux.getValor();
                        }
                    }
                } else {
                    matriz[i][j] = matriz[i-1][j];
                }
            }
        }

        int auxColuna = pesosOrdenados.size()-1;
        int auxLinha = produtos.size()-1;

        List<Object> mochila = new ArrayList<>();
        //Pego o produto que será adicionado
        Produto prod = produtos.get(auxLinha);

        while(auxLinha>0 && auxColuna>0){

            if(matriz[auxLinha][auxColuna]!= matriz[auxLinha-1][auxColuna]){
                mochila.add(prod);
            }
            //Proxima coluna a ser "visitada"
            auxColuna = pesosOrdenados.get(auxColuna) - prod.getPeso();
        }

        //Adiciona o valor total que a mochila carrega
        mochila.add(matriz[produtos.size()-1][pesosOrdenados.size()-1]);

        // retorna o valor máximo colocado na mochila
        return mochila;

    }

    private List pesosExistentes (List<Produto> produtos, int capacidade){
        List<Integer> lista = new ArrayList<>();
        Produto aux;

        for (int i = 0; i < produtos.size(); i++) {
            aux = produtos.get(i);

            if (!(lista.contains(aux.getPeso()))) {
                lista.add(aux.getPeso());
            }
        }

        lista.add(capacidade);
        Collections.sort(lista);
        return lista;
    }

}
