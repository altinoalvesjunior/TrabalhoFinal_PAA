package Supermercado;

import ProduzirDados.Produto;

import java.util.ArrayList;
import java.util.List;

public class Metodo {

    public Metodo() {}
    public List gulosoSupermercado(int pesoMaximo, List<Produto> produtos, double orcamento) {

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
