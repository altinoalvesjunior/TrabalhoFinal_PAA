import java.util.Random;

public class Evento {
    static Random sorteio = new Random(42);                  //--> fixo para testes
    //static Random sorteio = new Random(System.nanoTime());   --> aleatório

    static final int HORAMAX = 600;
    static final int DURACAOMAX = 120;
    int horaInicio; //em minutos relativos ao início
    int duracao;    //em minutos

    public Evento(){
        this.horaInicio = 10*sorteio.nextInt(HORAMAX/10);
        this.duracao = 10*sorteio.nextInt(DURACAOMAX/10);
    }

    @Override
    public String toString(){
        return "Inicio: "+this.horaInicio+" | Duracao: "+this.duracao;
    }

}
