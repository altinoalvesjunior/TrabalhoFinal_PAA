import java.util.Random;

public class Produto {
    static Random sorteio = new Random(42);                  //--> fixo
    //static Random sorteio = new Random(System.nanoTime());   --> aleatório
    static final int PESOMAX = 50;
    static final float VALMAX = 40f;
    int peso;
    float valor;

    public Produto(){
        this.peso = 1+sorteio.nextInt(PESOMAX);
        //Retorna um numero entre 0 e 1;
        this.valor = (float)(peso*2+sorteio.nextDouble()*VALMAX) ;
    }

    @Override
    public String toString(){
        return "P: "+this.peso+" | V: "+this.valor;
    }
}
