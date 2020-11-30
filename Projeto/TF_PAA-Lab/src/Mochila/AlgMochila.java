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
        // mochila final
        List<Object> mochila = new ArrayList<>(); // Só irá receber os produtos definitivos
        List<Produto> aux = produtos; // criando uma lista auxilar (cópia da lista oficial de produtos) para poder sofrer alterações

        Produto maiorValor; // armazena o produto que armazena o maior valor no momento
        Produto temp = new Produto();
        int pesoMochila = 0;
        float valorMochila = 0;

        while (pesoMochila < capacidade && aux.size() >= 0) {
            maiorValor = aux.get(0);

            for (int i = 0; i < aux.size(); i++) {
                temp = aux.get(i);

                if (temp.getValor() > maiorValor.getValor()) {
                    maiorValor = temp;
                }
            }

            pesoMochila += maiorValor.getPeso();
            aux.remove(maiorValor);

            if (pesoMochila < capacidade) {
                mochila.add(maiorValor);
                valorMochila += maiorValor.getValor();
            }
        }

        mochila.add(valorMochila);
        return mochila;
    }

    public List programacaoDinamica(int capacidade, List<Produto> produtos) {
        List<Integer> pesosOrdenados = pesosExistentes(produtos, capacidade);

        int linha = produtos.size();
        int coluna = pesosOrdenados.size();

        // tabela que será preenchida
        float[][] matriz = new float[linha + 1][coluna];

        // inicializando a primeira linha e primeira coluna com 0
        for (int j = 0; j < coluna; j++) {
            matriz[0][j] = 0;
        }

        for (int i = 1; i <= linha; i++) {
            matriz[i][0] = 0;
        }

        Produto aux;

        for (int i = 1; i <= linha; i++) {
            for (int j = 1; j <= coluna; j++) {
                aux = produtos.get(i);

                if (aux.getPeso() <= pesosOrdenados.get(i)) {
                    if (aux.getPeso() == pesosOrdenados.get(i)) {

                        if (aux.getValor() > matriz[i-1][j]) {
                            matriz[i][j] = aux.getValor();
                        } else {
                            matriz[i][j] = matriz[i-1][j];
                        }
                    } else {
                        int descontoPeso = pesosOrdenados.get(i) - aux.getPeso();
                        int colunaMochilaPassada = pesosOrdenados.indexOf(descontoPeso);
                        //Realiza-se uma soma do valor do item atual com o valor contido na mochila contida em matriz[i-1][colunaMochilaPassada]
                        float soma = aux.getValor()+matriz[i-1][colunaMochilaPassada];
                        if(soma > matriz[i-1][j]){
                            matriz[i][j] = soma;
                        }

                    }
                } else if (aux.getValor() > pesosOrdenados.get(i)) {
                    matriz[i][j] = matriz[i-1][j];
                }
            }
        }
        // retorna o valor máximo colocado na mochila
        return pesosOrdenados;

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
