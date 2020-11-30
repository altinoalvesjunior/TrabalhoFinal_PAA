package ProduzirDados;

import java.util.Random;

public class Produto {
//    static Random sorteio = new Random(42);                  //--> fixo
    static Random sorteio = new Random(System.nanoTime());   //--> aleatório
    static final int PESOMAX = 50;
    static final float VALMAX = 40f;
    private int peso;
    private float valor;

    public Produto(int peso){
        this.peso = peso;
        this.valor = (float)(peso*2+sorteio.nextDouble()*VALMAX) ;
    }

    @Override
    public String toString(){
        return "P: "+this.peso+" | V: "+this.valor;
    }

    public int getPeso() {
        return peso;
    }

    public float getValor() {
        return valor;
    }

}
