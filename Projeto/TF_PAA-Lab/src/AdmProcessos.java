import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdmProcessos {

    //#region Par de pontos

    static final int TOTALPONTOS = 50000;
    static final int MINPONTOS = 2;

    static Random sorteio = new Random(42);

    static java.util.List<Point> geraPontos(int quantPontos){
        java.util.List<Point> pontos = new ArrayList<Point>(quantPontos);
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
    static java.util.List<Produto> geraProduto(int tam){
        ArrayList<Produto> prod = new ArrayList<Produto>(tam);
        for(int i=0; i<tam; i++){
            Produto novo = new Produto();
            prod.add(novo);
        }
        return prod;
    }
    //#endregion

    //#region Mochila
    //calcula o peso médio dos produtos
    //gera a capacidade como proporção do peso médio
    static final float PROPORCAOCAPACIDADE = 6.5f;
    static int criarCapacidade(java.util.List<Produto> lista, float proporcao){
        int pesoTotal = lista.stream().mapToInt(p -> p.peso).sum();
        int quantTotal = lista.size();
        float media = (float)pesoTotal/quantTotal;

        return (int)Math.ceil(media * proporcao);
    }
    //#endregion

    //#region Supermercado
    //calcula o valor médio dos produtos
    //gera o orçamento como proporção do valor médio
    static final float PROPORCAOORCAMENTO = 13.5f;
    static double criarOrcamento(java.util.List<Produto> lista, float proporcao){
        double valorTotal = lista.stream().mapToDouble(p -> p.valor).sum();
        int quantTotal = lista.size();
        double media = valorTotal/quantTotal;

        return (int)Math.ceil(media * proporcao);
    }
    //#endregion

    //#region Auditório
    static final int PROPORCAOEVENTOS = 50;
    static List<Evento> geraEventos(int proporcao){
        int quantEventos = proporcao * Evento.HORAMAX/Evento.DURACAOMAX;
        ArrayList<Evento> eventos = new ArrayList<Evento>(quantEventos);
        for(int i=0; i<quantEventos; i++){
            Evento novo = new Evento();
            eventos.add(novo);
        }
        return eventos;
    }
    //#endregion

    public static void main(String[] args) throws Exception {
        Mapa teste = new Mapa(10);

        System.out.println(teste);
        System.out.println();
    }
}
