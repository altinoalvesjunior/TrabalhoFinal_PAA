package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.*;

/** 
 * MIT License
 *
 * Copyright(c) 2020 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {

    //#region Par de pontos

    static final int TOTALPONTOS = 50000;
    static final int MINPONTOS = 2;

    static Random sorteio = new Random(42);

    static List<Point> geraPontos(int quantPontos){
        List<Point> pontos = new ArrayList<Point>(quantPontos);
        for(int i=0; i<quantPontos; i++){
            pontos.add(new Point(sorteio.nextInt(TOTALPONTOS), sorteio.nextInt(TOTALPONTOS)));
        }
       
        Point x = new Point(1000,1000);
        Point y = new Point(1001,1001);
        pontos.set(TOTALPONTOS/2, x);
        pontos.set(TOTALPONTOS/2 + 1, y);

        return pontos;
    }
    //#endregion

    //#region Produtos (mochila + supermercado)
    static final int QUANTPROD = 50;
    static List<app.Produto> geraProduto(int tam){
        ArrayList<app.Produto> prod = new ArrayList<app.Produto>(tam);
        for(int i=0; i<tam; i++){
            app.Produto novo = new app.Produto();
            prod.add(novo);
        }
        return prod;
    }
    //#endregion

    //#region Mochila
    //calcula o peso médio dos produtos 
    //gera a capacidade como proporção do peso médio
    static final float PROPORCAOCAPACIDADE = 6.5f;
    static int criarCapacidade(List<app.Produto> lista, float proporcao){
        int pesoTotal = lista.stream().mapToInt(p -> p.getPeso()).sum();
        int quantTotal = lista.size();
        float media = (float)pesoTotal/quantTotal;

        return (int)Math.ceil(media * proporcao);
    }
    //#endregion

    //#region Supermercado
    //calcula o valor médio dos produtos 
    //gera o orçamento como proporção do valor médio
    static final float PROPORCAOORCAMENTO = 13.5f;
    static double criarOrcamento(List<app.Produto> lista, float proporcao){
        double valorTotal = lista.stream().mapToDouble(p -> p.getValor()).sum();
        int quantTotal = lista.size();
        double media = valorTotal/quantTotal;

        return (int)Math.ceil(media * proporcao);
    }
    //#endregion

    //#region Auditório
    static final int PROPORCAOEVENTOS = 50;
    static List<app.Evento> geraEventos(int proporcao){
        int quantEventos = proporcao * app.Evento.HORAMAX/ app.Evento.DURACAOMAX;
        ArrayList<app.Evento> eventos = new ArrayList<app.Evento>(quantEventos);
        for(int i=0; i<quantEventos; i++){
            app.Evento novo = new app.Evento();
            eventos.add(novo);
        }
        return eventos;
    }
    //#endregion

    public static void main(String[] args) throws Exception {
        app.Mapa teste = new app.Mapa(10);

        System.out.println(teste);
        System.out.println();
    }
}